package com.marco.chapter2.b_indepententAttribute;

/**
 * Created by marco on 15/12/24.
 */
public class TicketOffice2 implements Runnable{

    private Cinema cinema;
    public TicketOffice2(Cinema cinema) {
        this.cinema=cinema;
    }

    @Override
    public void run() {
        cinema.sellTickets2(2);
        cinema.sellTickets2(4);
        cinema.sellTickets1(2);
        cinema.sellTickets1(1);
        cinema.returnTickets12(2);
        cinema.sellTickets1(3);
        cinema.sellTickets2(2);
        cinema.sellTickets1(2);
    }
}
