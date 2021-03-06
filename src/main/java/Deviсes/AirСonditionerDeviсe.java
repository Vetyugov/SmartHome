package Deviсes;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AirСonditionerDeviсe extends AbstractDevice implements OnOffTypeDevices {
    private Status currentStatus = Status.OFF;

    protected AirСonditionerDeviсe() {
    }

    /**
     * В текущей реализации кондиционер при в ключении сразу начинает охлаждать воздух
     */
    @Override
    public void on() {
        log.info("Кондиционер включен");
        currentStatus = Status.IN_WORK;
    }

    @Override
    public void off() {
        log.info("Кондиционер выключен");
        currentStatus = Status.OFF;
    }

    @Override
    public Status getCurrentStatus() {
        return currentStatus;
    }
}
