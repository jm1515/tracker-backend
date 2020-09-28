package com.ppd.GPSTrackerBackend.model;

import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
    Device findByDeviceID(String deviceID);
}
