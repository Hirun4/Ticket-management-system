package dev.hirun.TicketManagementSystemBackend.core;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool implements TicketOperation {
    private final Queue<Ticket> tickets = new LinkedList<>();
    private final int maxCapacity;

    // Constructor that sets the maximum capacity for the ticket pool
    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Implementing addTicket(Ticket) from TicketOperation
    @Override
    public synchronized void addTicket(Ticket ticket) {
        // Check if there's space in the pool, if full, wait
        while (tickets.size() >= maxCapacity) {
            try {
                wait(); // Wait if the pool is full
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Add the ticket to the pool
        tickets.add(ticket);
        System.out.println("Added ticket: " + ticket);
        notifyAll(); // Notify any waiting threads
    }

    // Implementing retrieveTicket() from TicketOperation
    @Override
    public synchronized Ticket retrieveTicket() {
        // Wait if the pool is empty
        while (tickets.isEmpty()) {
            try {
                wait(); // Wait if the pool is empty
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Retrieve and remove the ticket from the pool
        Ticket ticket = tickets.poll();
        System.out.println("Retrieved ticket: " + ticket);
        notifyAll(); // Notify any waiting threads
        return ticket;
    }

    // Implementing getAvailableTickets() to return the current number of tickets in the pool
    @Override
    public synchronized int getAvailableTickets() {
        return tickets.size();
    }
}
