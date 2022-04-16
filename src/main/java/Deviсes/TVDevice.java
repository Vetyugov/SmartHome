package Deviсes;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class TVDevice extends AbstractDevice implements OnOffTypeDevices{

    private float volume;

    public TVDevice(int id, String status) {
        this.id = id;
        this.currentStatus = status;
    }

    @Override
    public String getCurrentStatus() {
        return currentStatus;
    }

    @Override
    public void on() {
        log.info("Телевизор включен");
        currentStatus = Status.IN_WORK;
    }

    @Override
    public void off() {
        log.info("Телевизор выключен");
        currentStatus = Status.OFF;
    }
}
