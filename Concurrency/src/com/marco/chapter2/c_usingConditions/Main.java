package com.marco.chapter2.c_usingConditions;

/**
 * Created by marco on 15/12/31.
 */
public class Main {
    public static void main(String[] args) {
        EventStorage storage=new EventStorage();

        Producer producer=new Producer(storage);
        Thread thread1=new Thread(producer);

        Consumer consumer=new Consumer(storage);
        Thread thread2=new Thread(consumer);

        thread2.start();
        thread1.start();
    }
}
