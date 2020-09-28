package com.ppd.GPSTrackerBackend.controller;

import com.ppd.GPSTrackerBackend.model.Device;
import com.ppd.GPSTrackerBackend.model.DeviceResponse;
import com.ppd.GPSTrackerBackend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * This means that this class is a Controller
 * The URL will start with /dev (after Application path)
 * This endpoint will allow to do CRUD operations for a Device entity
 */
@Controller
@RequestMapping(path="/dev")
@CrossOrigin
public class DeviceControler {

    @Autowired
    private DeviceService deviceService;

    public DeviceControler() {
    }

    @PostMapping(path="/add")
    @ResponseBody
    public ResponseEntity<Device> addNewVehicle (@RequestBody Device device) {
        Device res = deviceService.saveDevice(device);
        if(res == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }
        return new ResponseEntity<>(device, HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/all")
    @ResponseBody
    public ResponseEntity<Iterable<Device>> getAllVehicle() {
        Iterable<Device> devices = deviceService.getAll();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @PutMapping(path="/edit")
    @ResponseBody
    public ResponseEntity<Device> updateVehicle (@RequestBody Device vehicle) {

        Device res = deviceService.saveDevice(vehicle);

        if(res == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }

        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @DeleteMapping(path="/del/{deviceID}")
    @ResponseBody
    public ResponseEntity<Device> deleteVehicle(@PathVariable String deviceID) {
        Device device = deviceService.getDeviceByDeviceID(deviceID);
        if (device == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        deviceService.deleteDevice(device.getId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(path = "infos/{deviceID}")
    public ResponseEntity<DeviceResponse> getDeviceInfos(@PathVariable String deviceID){
        Device device = deviceService.getDeviceByDeviceID(deviceID);
        if (device == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        DeviceResponse deviceResponse = new DeviceResponse();
        deviceResponse.setResponse(deviceService.getDeviceInfos(deviceID));
        return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
    }
}
