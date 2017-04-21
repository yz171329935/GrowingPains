package com.marco.chapter2.h_mutliple_sycn_conditions_in_lock;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by marco on 2017/4/21.
 *
 * 轮训打印数组
 */
public class Main {

    private static final List<Integer> arrayList = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);


    public static void main(String[] args) throws Exception{

        final ReentrantLock lock = new ReentrantLock();
        final Condition con1 = lock.newCondition();
        final Condition con2 = lock.newCondition();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < arrayList.size(); i = i +2 ) {
                    lock.lock();
                    System.out.printf("%d,",arrayList.get(i));
                    con1.signal();

                    try {
                        con2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        });
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i < arrayList.size(); i = i +2 ) {
                    try {
                        lock.lock();
                        con1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%d,",arrayList.get(i));
                    con2.signal();
                    lock.unlock();
                }
            }
        });
        th2.start();

        th1.start();


        Thread.sleep(Integer.MAX_VALUE);
    }
}
