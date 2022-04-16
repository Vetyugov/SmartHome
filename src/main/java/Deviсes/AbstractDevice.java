package Deviсes;

import lombok.Getter;

@Getter
public abstract class AbstractDevice {
    protected int id;
    protected String currentStatus = Status.OFF;;
    public AbstractDevice() {
    }
    public abstract String getCurrentStatus();
}
