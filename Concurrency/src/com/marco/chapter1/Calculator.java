package com.marco.chapter1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Created by marco on 15/10/26.
 */
public class Calculator implements Runnable {

    public  int  number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i=1; i<=10; i++) {
            System.out.printf("%s: %d * %d = %d\n",
                    Thread.currentThread().getName(), number, i, i * number);
        }
    }

    public static void main(String[] args) throws Exception{
        // write your code here

        Thread[]  threads = new Thread[10];

        for (int i=0; i<10; i++){
            threads[i]= new Thread(new Calculator(i));
            if ((i == 2)){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread "+i);
        }

        URL url =  Calculator.class.getResource("data/log.txt");

        FileWriter file = new FileWriter(new File(url.getPath()));
        PrintWriter pw = new PrintWriter(file);
        Thread.State  status[] = new Thread.State[10];
        for (int i=0; i<10; i++){
            pw.println("Main : Status of Thread "+i+" : "  +
                    threads[i].getState());
            status[i] = threads[i].getState();
        }

        for (int i=0; i<10; i++){
            threads[i].start();
        }

        Thread.sleep(3000);
        // 中断不会立马停止
        threads[2].interrupt();
        System.out.println("interrupted");

        boolean finish=false;
        while (!finish) {
            for (int i=0; i<10; i++){
                if (threads[i].getState()!=status[i]) {
                    writeThreadInfo(pw, threads[i],status[i]);
                    status[i]=threads[i].getState();
                }
            }
            finish=true;
            for (int i=0; i<10; i++){
                finish &= (threads[i].getState()== Thread.State.TERMINATED);
            }
        }

        pw.flush();
        pw.close();
    }

    private static void writeThreadInfo(PrintWriter pw, Thread
            thread, Thread.State state) {
        pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
        pw.printf("Main : Priority: %d\n",thread.getPriority());
        pw.printf("Main : Old State: %s\n",state);
        pw.printf("Main : New State: %s\n",thread.getState());
        pw.printf("Main : ************************************\n");
    }
}
