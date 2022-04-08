package Deviсes;

import java.util.ArrayList;

public class DeviceHandler {
    private ArrayList<AbstractDevice> devices;

    public DeviceHandler(ArrayList<AbstractDevice> devices) {
        this.devices = devices;
    }
    public DeviceHandler() {
        this.devices = new ArrayList<>();
    }

    public void addDevice(AbstractDevice device){
        if(devices == null){
            devices = new ArrayList<>();
        }
        devices.add(device);
    }

    public void removeDevice(AbstractDevice deviceToRemove){
        devices.remove(deviceToRemove);
    }

    public String toString(){
        return "Устройств в памяти: " + devices.size();
    }
}
