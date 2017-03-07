package com.marco.chapter3.d_SynchronizingTasksInCommonPoint;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by marco on 2017/2/3.
 * CyclicBarrier 情景:
 * 等所有的操作都执行完毕以后 最后再执行某个实现了Runnable的操作
 * 因为使用CyclicBarrier的线程都会阻塞在await方法上，所以在线程池中使用时要特别小心，如果线程池过小，那么久会发生死锁了
 */
public class Main {

    private static ExecutorService es = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception{
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
            es.submit(searchers[i]);
        }
        Thread.sleep(2000000);
        System.out.printf("Main: The main thread has finished.\n");
    }
}
