package com.ppm.designpattern.flyweight;

import java.util.HashMap;

/**
 * 享元工厂
 */
class TicketFactory {

    private static HashMap<String, Ticket> sTicketMap = new HashMap<>();

    static Ticket getTicket(String from, String to) {
        String key = from + "-" + to;
        if (sTicketMap.containsKey(key)) {
            return sTicketMap.get(key);
        }

        Ticket ticket = new TrainTicket(from, to);
        sTicketMap.put(key, ticket);
        return ticket;
    }
}
