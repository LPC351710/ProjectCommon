package com.ppm.designpattern.flyweight;

public class TrainTicket implements Ticket {
    private String from;
    private String to;
    private String bunk;
    private int price;

    public TrainTicket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void showTicketInfo(String bunk) {
        System.out.println("this is Train Ticket info");
    }
}
