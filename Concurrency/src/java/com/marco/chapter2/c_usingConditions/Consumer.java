package com.marco.chapter2.c_usingConditions;

/**
 * Created by marco on 15/12/31.
 */
public class Consumer implements Runnable{

    private EventStorage storage;

    public Consumer(EventStorage storage){
        this.storage=storage;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            storage.get();
        }
    }
}
