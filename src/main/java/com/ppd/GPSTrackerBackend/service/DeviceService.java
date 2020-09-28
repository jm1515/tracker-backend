package com.ppd.GPSTrackerBackend.service;

import com.ppd.GPSTrackerBackend.model.Device;
import com.ppd.GPSTrackerBackend.model.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private static final String INFO_RESPONSE_1 = "+RESP:GTFRI,361301,864251020305471,,gv500,,10,1,0,48.3,,,4.820543,45.736649,,0208,0001,03FE,7742,00,785.7,,,,0,410000,,,96,20200409100145,1799$";
    private static final String INFO_RESPONSE_2 = "+RESP:GTFRI,361301,864251020305472,,gv500,,10,1,0,122.3,,,3.620533,48.733649,,0208,0001,03FE,7742,00,2345.9,,,,0,410000,,,78,20200409100145,1799$";
    private static final String INFO_RESPONSE_3 = "+RESP:GTCRA,361301,864251020305473,,gv500,,10,1,0,0.0,,,6.567899,46.456789,,0208,0001,03FE,7742,00,6789.3,,,,0,410000,,,56,20200409100145,1799$";
    private static final String INFO_RESPONSE_4 = "+RESP:GTSTP,361301,864251020305474,,gv500,,10,1,0,0.0,,,4.063543,44.789876,,0208,0001,03FE,7742,00,1456.3,,,,0,410000,,,9,20200409100145,1799$";
    private static final String INFO_RESPONSE_5 = "+RESP:GTIDN,361301,864251020305475,,gv500,,10,1,0,0.0,,,2.920543,47.126649,,0208,0001,03FE,7742,00,4577.9,,,,0,410000,,,34,20200409100145,1799$";
    private static final String INFO_RESPONSE_DEFAULT = "+RESP:GTFRI,361301,864251020305476,,gv500,,10,1,0,0.0,,,4.0,45.0,,0208,0001,03FE,7742,00,2000.0,,,,0,410000,,,100,20200409100145,1799$";

    private static final String DEVICE_1 = "864251020305471";
    private static final String DEVICE_2 = "864251020305472";
    private static final String DEVICE_3 = "864251020305473";
    private static final String DEVICE_4 = "864251020305474";
    private static final String DEVICE_5 = "864251020305475";

    public static final int EVENT_INDEX = 0;
    public static final int SPEED_INDEX = 9;
    public static final int LONGITUDE_INDEX = 12;
    public static final int LATITUDE_INDEX = 13;
    public static final int FUEL_INDEX = 28;
    public static final int MILEAGE_INDEX = 20;

    public final static String EV_ON = "+RESP:GTFRI";
    public final static String EV_CRASH = "+RESP:GTCRA";
    public final static String EV_STOP = "+RESP:GTSTP";
    public final static String EV_INACTIVE = "+RESP:GTIDN";

    public final static String EV_ON_NAME = "Allumé";
    public final static String EV_CRASH_NAME = "Accident";
    public final static String EV_STOP_NAME = "Eteint";
    public final static String EV_INACTIVE_NAME = "Inactif";
    public final static String EV_UNKNOWN_NAME = "Inconnu";

    @Autowired
    DeviceRepository deviceRepository;

    /**
     * @param device
     * @return Device
     * This method will save a device in database using crudRepository API features
     * and return the saved device.
     */
    public Device saveDevice(Device device){
        return deviceRepository.save(device);
    }

    /**
     * @return Iterable<Device> : List of all existing device.
     * This method will return all existing device in database using crudRepository API features.
     */
    public Iterable<Device> getAll(){
        return deviceRepository.findAll();
    }


    /**
     * @param id : Device unique identifier
     * This method will delete a device from his unique identifier using crudRepository API features.
     */
    public void deleteDevice(Integer id){
        deviceRepository.deleteById(id);
    }

    /**
     * @param deviceId : DeviceID field for a device
     * @return Device
     * This method will return an existing Device from his field DeviceId using crudRepository API features.
     */
    public Device getDeviceByDeviceID(String deviceId){
        return deviceRepository.findByDeviceID(deviceId);
    }

    /**
     * @param deviceID : DeviceID field for a device
     * @return Response from device.
     * This method will return result from device for a Device find by his field DeviceID
     */
    public String getDeviceInfos(String deviceID) {
        switch (deviceID) {
            case DEVICE_1:
                return INFO_RESPONSE_1;
            case DEVICE_2:
                return INFO_RESPONSE_2;
            case DEVICE_3:
                return INFO_RESPONSE_3;
            case DEVICE_4:
                return INFO_RESPONSE_4;
            case DEVICE_5:
                return INFO_RESPONSE_5;
            default:
                return INFO_RESPONSE_DEFAULT;
        }
    }
}
