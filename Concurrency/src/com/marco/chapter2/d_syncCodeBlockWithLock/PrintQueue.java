package com.marco.chapter2.d_syncCodeBlockWithLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by marco on 16/1/11.
 */
public class PrintQueue {

    private final Lock queueLock=new ReentrantLock();

    public void printJob(Object document){
        queueLock.lock();
        try {
            Long duration=(long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+ ":" +
                    " PrintQueue: Printing a Job during "+(duration/1000)+
                    " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }

    }
}
