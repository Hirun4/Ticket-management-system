package dev.hirun.TicketManagementSystemBackend.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static final String LOG_FILE = "resources/logs.txt";

    static {
        // Ensure the log file and its parent directory exist
        File logFile = new File(LOG_FILE);
        try {
            if (!logFile.exists()) {
                logFile.getParentFile().mkdirs(); // Create parent directories if they don't exist
                logFile.createNewFile();         // Create the log file
            }
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        String timeStampedMessage = LocalDateTime.now() + ": " + message;
        System.out.println(timeStampedMessage);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(timeStampedMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
