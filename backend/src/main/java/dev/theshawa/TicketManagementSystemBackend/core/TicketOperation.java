package dev.theshawa.TicketManagementSystemBackend.core;

public interface TicketOperation {
    void addTicket(Ticket ticket);  
    Ticket retrieveTicket();       
    int getAvailableTickets();      
}
