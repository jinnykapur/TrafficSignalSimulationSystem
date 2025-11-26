package com.traffic.main;


import com.traffic.model.Signal;
import com.traffic.service.TrafficService;
import java.util.Scanner;

public class TrafficSimulation {
    public static void main(String[] args) {
        System.out.println("🚦 Traffic Signal Simulation System 🚦");
        System.out.println("======================================");
        
        // Create traffic signal
        Signal signal = new Signal("SIGNAL-001");
        
        // Create and start traffic service
        TrafficService trafficService = new TrafficService(signal);
        trafficService.startTrafficSignal();
        
        // Add shutdown hook for graceful termination
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down simulation...");
            trafficService.stopSimulation();
        }));
        
        // User interface for manual control
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCommands:");
        System.out.println("  'stop' - Stop simulation");
        System.out.println("  'exit' - Exit program");
        System.out.println("Press Enter to stop...");
        
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("stop") || input.equals("exit")) {
                trafficService.stopSimulation();
                break;
            }
        }
        
        scanner.close();
        System.out.println("Simulation ended. Check logs/log.csv for details.");
    }
}