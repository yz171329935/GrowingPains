package com.marco.chapter2.a_synchronizeMethod;

/**
 * Created by marco on 15/12/24.
 */
public class Main {
    public static void main(String[] args) {
        Account account=new Account();
        account.setBalance(2000);

        Company company=new Company(account);
        Thread companyThread=new Thread(company);

        Bank bank=new Bank(account);
        Thread bankThread=new Thread(bank);

        System.out.printf("Account : Initial Balance: %f\n",account.getBalance());
//        Start the threads.
        companyThread.start();
        bankThread.start();

        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %f\n",account.
                    getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
