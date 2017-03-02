package com.marco.chapter3.d_SynchronizingTasksInCommonPoint;

/**
 * Created by marco on 2017/2/3.
 */
public class Results {

    private int data[];

    public Results(int size) {

        data = new int[size];
    }

    public void setData(int position, int value) {

        data[position] = value;
    }

    public int[] getData() {

        return data;
    }
}
