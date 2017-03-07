package com.marco.chapter2.b_indepententAttribute;

/**
 * Created by marco on 15/12/24.
 */
public class Cinema {

    private final Object controlCinema1, controlCinema2;

    private long vacanciesCinema1;

    private long vacanciesCinema2;

    public Cinema() {
        controlCinema1=new Object();
        controlCinema2=new Object();
        vacanciesCinema1=20;
        vacanciesCinema2=20;
    }

    public boolean sellTickets1 (int number) {
        synchronized (controlCinema1) {
            if (number<vacanciesCinema1) {
                vacanciesCinema1-=number;
                return true;
            } else {
                return false;
            } }
    }
    public boolean returnTickets1 (int number) {
        synchronized (controlCinema1) {
            vacanciesCinema1+=number;
            return true;
        }
    }

    public boolean sellTickets2 (int number){
        synchronized (controlCinema2) {
            if (number<vacanciesCinema2) {
                vacanciesCinema2-=number;
                return true;
            } else {
                return false;
            } }
    }
    public boolean returnTickets12(int number) {
        synchronized (controlCinema2) {
            vacanciesCinema2+=number;
            return true;
        }
    }

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }
    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }

}
