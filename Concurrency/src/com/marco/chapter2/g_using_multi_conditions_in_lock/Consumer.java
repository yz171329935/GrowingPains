package com.marco.chapter2.g_using_multi_conditions_in_lock;

import java.util.Random;

/**
 * Created by marco on 16/1/12.
 */
public class Consumer implements Runnable {

    private Buffer buffer;

    public Consumer (Buffer buffer) {
        this.buffer=buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line=buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            Random random=new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
