package com.marco.chapter2.b_indepententAttribute;

/**
 * Created by marco on 15/12/24.
 */
public class TicketOffice1 implements Runnable{

    private Cinema cinema;
    public TicketOffice1 (Cinema cinema) {
        this.cinema=cinema;
    }

    @Override
    public void run() {
        cinema.sellTickets1(3);
        cinema.sellTickets1(2);
        cinema.sellTickets2(2);
        cinema.returnTickets1(3);
        cinema.sellTickets1(5);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
    }
}
