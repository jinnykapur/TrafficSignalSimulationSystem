package com.traffic.model;

public class Vehicle {
    private final String vehicleId;
    private final VehicleType type;
    
    public enum VehicleType {
        CAR, BUS, TRUCK, MOTORCYCLE
    }
    
    public Vehicle(String vehicleId, VehicleType type) {
        this.vehicleId = vehicleId;
        this.type = type;
    }
    
    public String getVehicleId() {
        return vehicleId;
    }
    
    public VehicleType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", type=" + type +
                '}';
    }
}