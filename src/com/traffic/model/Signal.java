package com.traffic.model;

public class Signal {
    public enum SignalState {
        RED, YELLOW, GREEN
    }
    
    private SignalState currentState;
    private final String signalId;
    
    public Signal(String signalId) {
        this.signalId = signalId;
        this.currentState = SignalState.RED; 
    }
    
    public synchronized void changeState(SignalState newState) {
        this.currentState = newState;
        System.out.println("Signal " + signalId + " changed to: " + newState);
    }
    
    public synchronized SignalState getCurrentState() {
        return currentState;
    }
    
    public String getSignalId() {
        return signalId;
    }
    
    @Override
    public String toString() {
        return "Signal{" +
                "signalId='" + signalId + '\'' +
                ", currentState=" + currentState +
                '}';
    }
}