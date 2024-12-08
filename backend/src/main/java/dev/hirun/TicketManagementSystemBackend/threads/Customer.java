package dev.hirun.TicketManagementSystemBackend.threads;

import org.springframework.stereotype.Component;

import dev.hirun.TicketManagementSystemBackend.core.AbstractTicketHandler;
import dev.hirun.TicketManagementSystemBackend.core.Ticket;
import dev.hirun.TicketManagementSystemBackend.core.TicketPool;
import dev.hirun.TicketManagementSystemBackend.logging.Logger;

@Component
public class Customer extends AbstractTicketHandler implements Runnable {
    public void run() {
        while (true) {
            Ticket ticket = ticketPool.retrieveTicket();
            if (ticket != null) {
                Logger.log("Customer retrieved: " + ticket);
            } else {
                Logger.log("Customer found no tickets available.");
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("Customer interrupted.");
            }
        }
    }

    @Override
    public void start(TicketPool ticketPool){
        super.start(ticketPool);
        run();
    }
}