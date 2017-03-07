package com.marco.chapter2.a_synchronizeMethod;

/**
 * Created by marco on 15/12/24.
 */
public class Bank implements Runnable{

    private Account account;

    public Bank(Account account) {
        this.account=account;
    }

    @Override
    public void run() {
        for (int i=0; i<100; i++){
            account.subtractAmount(1000);
        }
    }
}
