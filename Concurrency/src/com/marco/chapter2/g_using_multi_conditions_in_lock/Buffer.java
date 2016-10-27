package com.marco.chapter2.g_using_multi_conditions_in_lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by marco on 16/1/12.
 */
public class Buffer {

    private LinkedList<String> buffer;
    private int maxSize;

    private ReentrantLock lock;

    private Condition lines; //有数据啦~~~
    private Condition space; //有空位置啦~~~

    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize=maxSize;
        buffer=new LinkedList<String>();
        lock=new ReentrantLock();
        lines=lock.newCondition();
        space=lock.newCondition();
        pendingLines=true;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                System.out.println("buffer满了...慢慢等 空位置吧~~~");
                space.await();
            }

            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.
                    currentThread().getName(), buffer.size());

            lines.signalAll();
            System.out.println("告知有数据啦~~~");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String line=null;
        lock.lock();
        try {
            while ((buffer.size() == 0) &&(hasPendingLines())) {
                System.out.println("buffer没数据了...慢慢等 数据进来吧~~~~~~");
                lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n",Thread.
                        currentThread().getName(),buffer.size());

                space.signalAll();
                System.out.println("拿走了一个，告知有 空位置 啦~~~~~");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines=pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size()>0;
    }


}
