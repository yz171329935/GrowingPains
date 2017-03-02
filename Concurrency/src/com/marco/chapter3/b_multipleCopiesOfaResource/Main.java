package com.marco.chapter3.b_multipleCopiesOfaResource;


/**
 * Created by marco on 16/10/27.
 *
 * 情景还原:
 * 有三台打印机~ 但是有10个不同的线程同时提交了打印任务,
 * 这时候 等待时间相比之前 减少了三倍!!! 快多了~~~有木有~~~
 */
public class Main {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }

    }
}
