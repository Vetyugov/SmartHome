package Commands;

import Deviсes.AbstractDevice;
import Deviсes.DeviceHandler;

public class CmAddToList implements Command{
    private final DeviceHandler receiver;
    private final AbstractDevice newDevice;

    public CmAddToList(DeviceHandler receiver, AbstractDevice newDevice) {
        this.receiver = receiver;
        this.newDevice = newDevice;
    }

    @Override
    public void execute() {
        receiver.addDevice(newDevice);
        CommandsHandler.getInstance().setCommand(this);
    }

    @Override
    public void undo() {
        receiver.removeDevice(newDevice);
    }
}
