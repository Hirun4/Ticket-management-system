package dev.theshawa.TicketManagementSystemBackend.core;

public abstract class AbstractTicketHandler {
    protected TicketPool ticketPool;

    public boolean isRunning() {
        return isRunning;
    }

    private volatile boolean isRunning;

    public void start(TicketPool ticketPool){
        this.ticketPool = ticketPool;
        this.isRunning = true;
    }

    public void stop(){
        this.ticketPool = null;
        this.isRunning = false;
    }

}