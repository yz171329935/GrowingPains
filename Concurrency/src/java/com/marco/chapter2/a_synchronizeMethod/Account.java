package com.marco.chapter2.a_synchronizeMethod;

/**
 * Created by marco on 15/12/24.
 */
public class Account {

    private double balance;


    public synchronized void addAmount(double amount) {
        double tmp=balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp+=amount;
        balance=tmp;
    }

    public synchronized void subtractAmount(double amount){
        double tmp=balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp-=amount;
        balance=tmp;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
