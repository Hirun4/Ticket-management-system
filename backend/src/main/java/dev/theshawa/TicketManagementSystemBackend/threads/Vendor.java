package dev.theshawa.TicketManagementSystemBackend.threads;

import java.math.BigDecimal;

import dev.theshawa.TicketManagementSystemBackend.core.Ticket;
import dev.theshawa.TicketManagementSystemBackend.core.TicketPool;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate; 

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        // In this example, we simulate adding tickets at the given rate
        int ticketId = 1;
        while (true) {
            try {
                // Simulate the delay in releasing tickets based on the rate
                Thread.sleep(1000 / ticketReleaseRate); 

                // Create a new Ticket (you could change event name and price logic)
                Ticket ticket = new Ticket(ticketId++, "Concert Event", new BigDecimal(50.00));

                // Add the ticket to the pool
                ticketPool.addTicket(ticket); // This calls the addTicket method
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
