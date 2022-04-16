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

    public AbstractDevice createDevice(String deviceType){
        AbstractDevice device = null;
        switch (deviceType){
            case DeviceTypes.TV -> device = new TVDevice(-1, Status.OFF);
            case DeviceTypes.AIR_CONDITIONER ->device = new AirСonditionerDeviсe(-1, Status.OFF);
        }
        return device;
    }
}
