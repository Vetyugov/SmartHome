package Deviсes;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Setter
@Getter
public class AirСonditionerDeviсe extends AbstractDevice implements OnOffTypeDevices {
    private float speed;

    public AirСonditionerDeviсe(int id, String status) {
        this.id = id;
        this.currentStatus = status;
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
    public String getCurrentStatus() {
        return currentStatus;
    }
}
