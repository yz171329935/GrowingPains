package com.marco.chapter2.g_using_multi_conditions_in_lock;

/**
 * Created by marco on 16/1/12.
 */
public class Main {

    public static void main(String[] args) {
        FileMock mock=new FileMock(100, 10);
        Buffer buffer=new Buffer(20);

        Producer producer=new Producer(mock, buffer);
        Thread threadProducer=new Thread(producer,"Producer");

        Consumer consumers[]=new Consumer[3];
        Thread threadConsumers[]=new Thread[3];
        for (int i=0; i<3; i++){
            consumers[i]=new Consumer(buffer);
            threadConsumers[i]=new Thread(consumers[i],"Consumer "+i);
        }

        threadProducer.start();
        for (int i=0; i<3; i++){
            threadConsumers[i].start();
        }
    }
}
