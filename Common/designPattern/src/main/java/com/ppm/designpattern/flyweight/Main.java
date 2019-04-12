package com.ppm.designpattern.flyweight;

public class Main {

    public static void main(String[] args) {
        Ticket ticket = TicketFactory.getTicket("1", "2");
        ticket.showTicketInfo("1");

        Ticket ticket1 = TicketFactory.getTicket("1", "2");
        ticket1.showTicketInfo("2");
    }
}
