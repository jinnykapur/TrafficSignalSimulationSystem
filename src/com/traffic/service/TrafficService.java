package com.traffic.service;

import com.traffic.model.Signal;
import com.traffic.model.Vehicle;

public class TrafficService {
    private final Signal signal;
    private boolean isRunning;
    
    public TrafficService(Signal signal) {
        this.signal = signal;
        this.isRunning = true;
    }
    
    public void startTrafficSignal() {
        Thread signalThread = new Thread(this::runSignalCycle);
        signalThread.setName("Signal-Controller");
        signalThread.start();
        
        Thread vehicleThread = new Thread(this::simulateVehicleFlow);
        vehicleThread.setName("Vehicle-Simulator");
        vehicleThread.start();
    }
    
    private void runSignalCycle() {
        System.out.println("Traffic signal simulation started...");
        
        while (isRunning) {
            try {
                // RED light for 5 seconds
                signal.changeState(Signal.SignalState.RED);
                LogService.logSignalState(signal);
                Thread.sleep(5000);
                
                // GREEN light for 7 seconds
                signal.changeState(Signal.SignalState.GREEN);
                LogService.logSignalState(signal);
                Thread.sleep(7000);
                
                // YELLOW light for 2 seconds
                signal.changeState(Signal.SignalState.YELLOW);
                LogService.logSignalState(signal);
                Thread.sleep(2000);
                
            } catch (InterruptedException e) {
                System.err.println("Signal thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.err.println("Error in signal cycle: " + e.getMessage());
            }
        }
    }
    
    private void simulateVehicleFlow() {
        int vehicleId = 1;
        
        while (isRunning) {
            try {
                // Random vehicle generation
                Thread.sleep((long) (Math.random() * 2000) + 500); // 0.5-2.5 seconds
                
                Vehicle.VehicleType[] types = Vehicle.VehicleType.values();
                Vehicle.VehicleType randomType = types[(int) (Math.random() * types.length)];
                
                Vehicle vehicle = new Vehicle("V" + vehicleId++, randomType);
                LogService.incrementVehicleCount();
                
                System.out.println("New vehicle arrived: " + vehicle + " | Total vehicles: " + LogService.getVehicleCount());
                
                // Simulate vehicles leaving when signal is GREEN
                if (signal.getCurrentState() == Signal.SignalState.GREEN && LogService.getVehicleCount() > 0) {
                    Thread.sleep(1000); // Processing time
                    int vehiclesToRemove = Math.min(LogService.getVehicleCount(), (int) (Math.random() * 3) + 1);
                    
                    for (int i = 0; i < vehiclesToRemove; i++) {
                        LogService.decrementVehicleCount();
                    }
                    
                    System.out.println(vehiclesToRemove + " vehicle(s) departed. Remaining: " + LogService.getVehicleCount());
                }
                
            } catch (InterruptedException e) {
                System.err.println("Vehicle thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.err.println("Error in vehicle flow: " + e.getMessage());
            }
        }
    }
    
    public void stopSimulation() {
        isRunning = false;
        System.out.println("Stopping traffic simulation...");
    }
}