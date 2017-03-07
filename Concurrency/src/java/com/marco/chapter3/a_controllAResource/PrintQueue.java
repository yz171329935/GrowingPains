package com.marco.chapter3.a_controllAResource;

import java.util.concurrent.Semaphore;

/**
 * Created by marco on 16/10/27.
 * 利用信号量机制来控制并发
 */
public class PrintQueue {

    private final Semaphore semaphore;

    public PrintQueue() {
        semaphore = new Semaphore(1);
    }

    public void printJob (Object document){
        try {
            semaphore.acquire();

            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);

            Thread.sleep(duration);

        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
