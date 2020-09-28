package com.ppd.GPSTrackerBackend.model;

import javax.persistence.*;

/**
 * Vehicle entity
 */
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String plateNumber;
    private double location;
    private int fuelState;
    private float speed;
    private String carState;
    private float carMileage;
    private float longitude;
    private float latitude;
    private boolean isTurnedOn;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Device device;

    public Vehicle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public int getFuelState() {
        return fuelState;
    }

    public void setFuelState(int fuelState) {
        this.fuelState = fuelState;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public float getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(float carMileage) {
        this.carMileage = carMileage;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    public void setTurnedOn(boolean turnedOn) {
        isTurnedOn = turnedOn;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", location=" + location +
                ", fuelState='" + fuelState + '\'' +
                ", speed='" + speed + '\'' +
                ", carState='" + carState + '\'' +
                ", carMileage='" + carMileage + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", isTurnedOn=" + isTurnedOn +
                ", device=" + device +
                '}';
    }
}
