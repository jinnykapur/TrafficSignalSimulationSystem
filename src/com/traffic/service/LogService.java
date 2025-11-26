package com.traffic.service;

import com.traffic.model.Signal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class LogService {
    private static final String LOG_FILE = "logs/log.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final AtomicInteger vehicleCount = new AtomicInteger(0);
    
    static {
        // Initialize CSV header
        FileService.ensureLogDirectory();
        FileService.writeToFile(LOG_FILE, "Timestamp,SignalState,VehicleCount");
    }
    
    public static synchronized void logSignalState(Signal signal) {
        String timestamp = DATE_FORMAT.format(new Date());
        String state = signal.getCurrentState().toString();
        int count = vehicleCount.get();
        
        String logEntry = String.format("%s,%s,%d", timestamp, state, count);
        FileService.writeToFile(LOG_FILE, logEntry);
        
        System.out.println("Logged: " + logEntry);
    }
    
    public static void incrementVehicleCount() {
        vehicleCount.incrementAndGet();
    }
    
    public static void decrementVehicleCount() {
        vehicleCount.decrementAndGet();
    }
    
    public static int getVehicleCount() {
        return vehicleCount.get();
    }
}