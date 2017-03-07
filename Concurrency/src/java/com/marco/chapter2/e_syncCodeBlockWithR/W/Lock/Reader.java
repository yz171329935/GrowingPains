package com.marco.chapter2.e_syncCodeBlockWithR.W.Lock;

/**
 * Created by marco on 16/1/11.
 */
public class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader (PricesInfo pricesInfo){
        this.pricesInfo=pricesInfo;
    }

    @Override
    public void run() {
        System.out.printf("%s: Price 1: %f\n", Thread.
                currentThread().getName(),pricesInfo.getPrice1());
        System.out.printf("%s: Price 2: %f\n", Thread.
                currentThread().getName(),pricesInfo.getPrice2());
    }

}
