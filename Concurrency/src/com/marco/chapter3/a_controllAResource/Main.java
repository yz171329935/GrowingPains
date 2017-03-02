package com.marco.chapter3.a_controllAResource;

/**
 * Created by marco on 16/10/27.
 *
 * 情景还原:
 * 只有一台打印机~ 但是有10个不同的线程同时提交了打印任务,来的晚的就需要等啦
 */
public class Main {

    public static void main(String[] args) {
        PrintQueue printQueue=new PrintQueue();

        Thread thread[]=new Thread[10];
        for (int i=0; i<10; i++){
            thread[i]=new Thread(new Job(printQueue),"Thread"+i);
        }

        for (int i=0; i<10; i++){
            thread[i].start();
        }

    }
}
