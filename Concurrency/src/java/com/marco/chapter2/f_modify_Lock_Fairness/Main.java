package com.marco.chapter2.f_modify_Lock_Fairness;

/**
 * Created by marco on 16/1/11.
 */
public class Main {

    public static void main(String[] args) {
        PrintQueue printQueue=new PrintQueue();

        Thread thread[]=new Thread[10];
        for (int i=0; i<10; i++){
            thread[i]=new Thread(new Job(printQueue),"Thread "+ i);
        }

        for (int i=0; i<10; i++){
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
