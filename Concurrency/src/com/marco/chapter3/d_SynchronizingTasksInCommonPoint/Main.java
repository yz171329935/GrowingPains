package com.marco.chapter3.d_SynchronizingTasksInCommonPoint;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by marco on 2017/2/3.
 * CyclicBarrier 情景:
 * 等所有的操作都执行完毕以后 最后再执行某个实现了Runnable的操作
 */
public class Main {

    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANT = 2000;

        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        Results results = new Results(ROWS);

        Grouper grouper = new Grouper(results);

        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock, results, 5, barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }

        System.out.printf("Main: The main thread has finished.\n");
    }
}
