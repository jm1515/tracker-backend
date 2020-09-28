package com.ppd.GPSTrackerBackend.controller;

import com.ppd.GPSTrackerBackend.model.Device;
import com.ppd.GPSTrackerBackend.model.User;
import com.ppd.GPSTrackerBackend.model.Vehicle;
import com.ppd.GPSTrackerBackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * This means that this class is a Controller
 * The URL will start with /veh (after Application path)
 * This endpoint will allow to do CRUD operations for a Vehicle entity.
 * We can also refresh Vehicle informations by using specified operations from this endpoint.
 */
@Controller
@RequestMapping(path="/veh")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    public VehicleController() {
    }

    @PostMapping(path="/add")
    @ResponseBody
    public ResponseEntity addNewVehicle (@RequestBody Vehicle vehicle) {
        Vehicle res = vehicleService.addVehicle(vehicle);
        if(res == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }
        return new ResponseEntity<>(vehicle, HttpStatus.ACCEPTED);
    }

    @PostMapping(path="/add/full")
    @ResponseBody
    public ResponseEntity addNewVehicleAndDevice (@RequestBody Vehicle vehicle) {
        Vehicle res = vehicleService.addFullVehicle(vehicle);
        if(res == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }
        return new ResponseEntity<>(vehicle, HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/all")
    @ResponseBody
    public ResponseEntity getAllVehicle() {
        Iterable<Vehicle> vehicles = vehicleService.getAllVehicle();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping(path="/{plateNumber}")
    @ResponseBody
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String plateNumber) {
        Vehicle vehicle = vehicleService.getVehicleByPlateNumber(plateNumber);
        if (vehicle == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }



    @PutMapping(path="/edit")
    @ResponseBody
    public ResponseEntity updateVehicle (@RequestBody Vehicle vehicle) {

        Vehicle res = vehicleService.updateVehicle(vehicle);

        if(res == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }

        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @DeleteMapping(path="{plateNumber}/del")
    @ResponseBody
    public ResponseEntity deleteVehicle(@PathVariable String plateNumber) {
        if (vehicleService.deleteVehicle(plateNumber)){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/{plateNumber}/dev")
    public ResponseEntity<Vehicle> setDevice(@PathVariable String plateNumber, @RequestBody Device device){
        Vehicle vehicle = vehicleService.getVehicleByPlateNumber(plateNumber);
        if (vehicle == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicleService.setDevice(vehicle, device), HttpStatus.OK);
    }

    @PutMapping(path = "/{plateNumber}/user")
    public ResponseEntity<Vehicle> setUser(@PathVariable String plateNumber, @RequestBody User user){
        Vehicle vehicle = vehicleService.getVehicleByPlateNumber(plateNumber);
        if (vehicle == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicleService.setUser(vehicle, user), HttpStatus.OK);
    }

    @GetMapping(path="/refresh")
    @ResponseBody
    public ResponseEntity<Iterable<Vehicle>> refreshVehicles() {
        return new ResponseEntity<>(vehicleService.refreshAll(), HttpStatus.OK);
    }
}
