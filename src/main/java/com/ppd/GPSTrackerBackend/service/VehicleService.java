package com.ppd.GPSTrackerBackend.service;

import com.ppd.GPSTrackerBackend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    DeviceService deviceService;
    @Autowired
    UserService userService;

    /**
     * @param vehicle
     * @return Vehicle
     * This method will save a new vehicle in database using crudRepository API features
     */
    public Vehicle addVehicle(Vehicle vehicle) {
        Vehicle save = vehicleRepository.save(vehicle);

        if (save != null) {
            return save;
        }
        return null;
    }

    public Vehicle addFullVehicle(Vehicle vehicle) {
        Vehicle veh = vehicleRepository.findByPlateNumber(vehicle.getPlateNumber());
        if (veh != null){
            return vehicleRepository.save(vehicle);
        }else {
            Device tmpDevice = vehicle.getDevice();
            User tmpUser = vehicle.getUser();
            vehicle.setDevice(null);
            vehicle.setUser(null);
            Vehicle saveV = vehicleRepository.save(vehicle);
            if (tmpDevice != null) {
                this.setDevice(saveV, tmpDevice);
            }
            if (tmpUser != null) {
                this.setUser(saveV, tmpUser);
            }
            return saveV;
        }
    }

    /**
     * @param vehicle
     * @param device
     * @return Vehicle
     * This method will set a Device for a Vehicle and update it in database.
     * If the vehicle already have a device then it will update it
     * else it will set and save the device for the vehicle and return the updated vehicle.
     */
    public Vehicle setDevice(Vehicle vehicle, Device device) {
        Device actualDevice = vehicle.getDevice();
        if (actualDevice != null && device != null && !(actualDevice.getDeviceID().equals(device.getDeviceID()))) {
            actualDevice.setDeviceID(device.getDeviceID());
            actualDevice.setDeviceName(device.getDeviceName());
            deviceService.saveDevice(actualDevice);
        } else {
            if (actualDevice == null) {
                vehicle.setDevice(device);
            }
        }
        return vehicleRepository.save(vehicle);
    }

    /**
     * @param vehicle
     * @param user
     * @return Vehicle
     * This method will set an User for a Vehicle and update it in database.
     * If the vehicle already have an User then it will update it
     * else it will set and save the User for the vehicle and return the updated vehicle.
     */
    public Vehicle setUser(Vehicle vehicle, User user) {
        User actualUser = vehicle.getUser();
        if (actualUser != null && user != null && !(actualUser.getEmail().equals(user.getEmail()))) {
            actualUser.setEmail(user.getEmail());
            actualUser.setFirstname(user.getFirstname());
            actualUser.setLastname(user.getLastname());
            actualUser.setPhoneNumber(user.getPhoneNumber());
            userService.updateUser(actualUser);
        } else {
            if (actualUser == null) {
                vehicle.setUser(user);
            }
        }
        return vehicleRepository.save(vehicle);
    }

    /**
     * @param veh
     * @return Vehicle
     * This method will update an existing vehicle in database using crudRepository API features
     * and will return the updated vehicle
     */
    public Vehicle updateVehicle(Vehicle veh) {
        return vehicleRepository.save(veh);
    }

    /**
     * @return Iterable<Vehicle> : List of vehicles
     * This method will return a list of all existing Vehicles in database using crudRepository API features
     */
    public Iterable<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    /**
     * @param plateNumber : Vehicle unique identifier
     *                    This method will delete an existing Vehicle in database by his unique identifier
     *                    and using crudRepository API features
     */
    public boolean deleteVehicle(String plateNumber) {
        //TODO update doc
        Vehicle vehicleToDelete = getVehicleByPlateNumber(plateNumber);
        if (vehicleToDelete != null) {
            Device deviceToDelete = vehicleToDelete.getDevice();
            vehicleToDelete.setDevice(null);
            if (deviceToDelete != null) {
                deviceService.deleteDevice(deviceToDelete.getId());
            }
            vehicleRepository.deleteById(vehicleToDelete.getId());
            return true;
        }
        return false;
    }

    /**
     * @param plateNumber
     * @return Vehicle
     * This method will find an existing vehicle by his plateNumber field using crudRepository API features
     * and return the founded Vehicle.
     */
    public Vehicle getVehicleByPlateNumber(String plateNumber) {
        return vehicleRepository.findByPlateNumber(plateNumber);
    }


    /**
     * @return Iterable<Vehicle>: List of vehicles
     * This method will refresh all existing vehicles in database by adding last information from device
     * for each vehicule field and will return the list of all updated vehicle.
     */
    public Iterable<Vehicle> refreshAll() {
        Iterable<Vehicle> vehicles = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getDevice() != null) {
                refreshVehicle(vehicle, deviceService.getDeviceInfos(vehicle.getDevice().getDeviceID()));
            }
        }
        return vehicles;
    }

    /**
     * @param vehicle
     * @param deviceInfos This method will set all fields for a vehicle by using last device informations
     *                    and will save the vehicle.
     */
    public void refreshVehicle(Vehicle vehicle, String deviceInfos) {
        String[] infos = deviceInfos.split(",");
        /*
        int index = 0;
        for (String info : infos) {
            System.out.println("INDEX = " + index + " INFO = " + info);
            index++;
        }*/
        try {
            String event = infos[DeviceService.EVENT_INDEX];
            switch (event) {
                case DeviceService.EV_ON:
                    vehicle.setCarState(DeviceService.EV_ON_NAME);
                    break;
                case DeviceService.EV_STOP:
                    vehicle.setCarState(DeviceService.EV_STOP_NAME);
                    break;
                case DeviceService.EV_CRASH:
                    vehicle.setCarState(DeviceService.EV_CRASH_NAME);
                    break;
                case DeviceService.EV_INACTIVE:
                    vehicle.setCarState(DeviceService.EV_INACTIVE_NAME);
                    break;
                default:
                    vehicle.setCarState(DeviceService.EV_UNKNOWN_NAME);
            }
        } catch (NumberFormatException ignored) {
        }
        try {
            vehicle.setCarMileage(Float.parseFloat(infos[DeviceService.MILEAGE_INDEX]));
        } catch (NumberFormatException ignored) {
        }
        try {
            vehicle.setFuelState(Integer.parseInt(infos[DeviceService.FUEL_INDEX]));
        } catch (NumberFormatException ignored) {
        }
        try {
            vehicle.setSpeed(Float.parseFloat(infos[DeviceService.SPEED_INDEX]));
        } catch (NumberFormatException ignored) {
        }
        try {
            vehicle.setLatitude(Float.parseFloat(infos[DeviceService.LATITUDE_INDEX]));
        } catch (NumberFormatException ignored) {
        }
        try {
            vehicle.setLongitude(Float.parseFloat(infos[DeviceService.LONGITUDE_INDEX]));
        } catch (NumberFormatException ignored) {
        }
        vehicleRepository.save(vehicle);

    }
}
