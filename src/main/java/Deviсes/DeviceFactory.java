package Deviсes;

import publicServices.ConnectionService;

public class DeviceFactory {
    private static DeviceFactory instance;

    public static DeviceFactory getInstance(){
        DeviceFactory localInstance = instance;
        if(localInstance == null){
            synchronized (DeviceFactory.class){
                localInstance = instance;
                if (localInstance == null){
                    localInstance = instance = new DeviceFactory();
                }
            }
        }
        return localInstance;
    }

    private DeviceFactory() {
    }

    public AbstractDevice createDevice(DeviceTypes deviceType){
        AbstractDevice device = null;
        switch (deviceType){
            case TV -> device = new TVDevice();
            case AIR_CONDITIONER ->device = new AirСonditionerDeviсe();
        }
        return device;
    }
}
