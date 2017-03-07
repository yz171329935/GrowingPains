package com.marco.chapter1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by marco on 15/10/27.
 */
@SuppressWarnings("unchecked")
public class DataSourcesLoader implements Runnable {

    @Override
    public void run() {
        System.out.printf("%s sources loading: %s\n",Thread.currentThread().getName(),new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s loading has finished: %s\n",Thread.currentThread().getName(),new Date());
    }

    public static void main(String[] args) {

        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader,"DataSourceThread");

        DataSourcesLoader ncLoader = new
                DataSourcesLoader();
        Thread thread2 = new Thread(ncLoader,"NetworkConnectionLoader");

        thread1.start();
        thread2.start();

        try {
            // join 告之此线程需要等下面的线程执行完毕再往下执行
            thread1.join(4000);
            thread2.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Configuration has been loaded: %s\n",new Date());
    }
}
