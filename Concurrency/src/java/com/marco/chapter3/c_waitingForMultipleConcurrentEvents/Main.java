package com.marco.chapter3.c_waitingForMultipleConcurrentEvents;

/**
 * Created by marco on 2017/2/3.
 *
 *  CountDownLatch 情景:
 * 一个视频会议要等所有的参与者都到达之后才能正式开启
 */
public class Main {

    public static void main(String[] args) {
        VideoConference conference = new VideoConference(10);

        Thread threadConference = new Thread(conference);
        threadConference.start();


        for (int i = 0; i < 10; i++) {
            Participant p = new Participant(conference, "Participant " + i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}
