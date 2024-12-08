package dev.theshawa.TicketManagementSystemBackend.server;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.theshawa.TicketManagementSystemBackend.core.Ticket;
import dev.theshawa.TicketManagementSystemBackend.core.TicketPool;
import dev.theshawa.TicketManagementSystemBackend.logging.Logger;
import dev.theshawa.TicketManagementSystemBackend.threads.Customer;
import dev.theshawa.TicketManagementSystemBackend.threads.Vendor;

@RestController
public class Server {

    @Autowired
    private Customer customer;

    @Autowired
    private Vendor vendor;

    private Thread customerThread;
    private Thread vendorThread;

    // Start the vendor and customer threads with the maxCapacity passed from the client
    @PostMapping("/start")
    public void start(@RequestParam(name = "maxCapacity") int maxCapacity) throws BadRequestException {
        if ((customerThread != null && customerThread.isAlive()) || 
            (vendorThread != null && vendorThread.isAlive())) {
            throw new BadRequestException("Threads are already running.");
        }

        TicketPool ticketPool = new TicketPool(maxCapacity);
        
        // Create and start the threads for Vendor and Customer
        customerThread = new Thread(customer);
        vendorThread = new Thread(vendor);

        customerThread.start();  // Start the customer thread
        vendorThread.start();    // Start the vendor thread
    }

    // Stop the vendor and customer threads
    @PostMapping("/stop")
    public void stop() throws BadRequestException {
        if ((customerThread == null || !customerThread.isAlive()) && 
            (vendorThread == null || !vendorThread.isAlive())) {
            throw new BadRequestException("No threads are running.");
        }

        customerThread.interrupt();  // Interrupt the customer thread
        vendorThread.interrupt();    // Interrupt the vendor thread
    }

    // Get the current tickets available in the pool
    @GetMapping("/tickets")
    public List<Ticket> tickets() {
        // TODO: Implement this
        return null;
    }

    // Get the logs (you'll need to implement this functionality)
    @GetMapping("/logs")
    public List<Logger> logs() {
        // TODO: Implement this
        return null;
    }
}
