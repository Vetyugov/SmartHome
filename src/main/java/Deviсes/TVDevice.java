package Deviсes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TVDevice extends AbstractDevice implements OnOffTypeDevices{
    private Status currentStatus = Status.OFF;

    @Override
    public Status getCurrentStatus() {
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
