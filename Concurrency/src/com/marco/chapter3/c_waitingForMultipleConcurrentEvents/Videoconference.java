package com.marco.chapter3.c_waitingForMultipleConcurrentEvents;

import java.util.concurrent.CountDownLatch;

/**
 * Created by marco on 2017/2/3.
 *
 * Video conference 视频会议
 */
public class VideoConference implements Runnable {

    private final CountDownLatch controller;

    public VideoConference(int number) {
        controller = new CountDownLatch(number);
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());

        try {
            controller.await();
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived.", name);
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }

}
