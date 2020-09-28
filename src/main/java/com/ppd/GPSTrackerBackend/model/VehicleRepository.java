package com.ppd.GPSTrackerBackend.model;

import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    Vehicle findByPlateNumber(String plateNumber);
}
