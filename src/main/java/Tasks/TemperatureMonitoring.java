package Tasks;

import Detectors.Types.AnalogTypeSensor;
import Detectors.Types.SwitchTypeSensor;
import Deviсes.AirСonditionerDeviсe;
import Deviсes.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemperatureMonitoring extends Thread{
    private AnalogTypeSensor temperatureAnalogSensor;
    private SwitchTypeSensor windowOpenCloseDigitalSensor;
    private AirСonditionerDeviсe airConditioner;

    public TemperatureMonitoring(AnalogTypeSensor temperatureAnalogSensor, SwitchTypeSensor windowOpenCloseDigitalSensor, AirСonditionerDeviсe airConditioner) {
        super();
        this.temperatureAnalogSensor = temperatureAnalogSensor;
        this.windowOpenCloseDigitalSensor = windowOpenCloseDigitalSensor;
        this.airConditioner = airConditioner;
    }

    @Override
    public void run() {
        while (true){
            /**
             * Если температура в комнате больше 30 и окно закрыто
             */
            double temp = temperatureAnalogSensor.getValue();
            boolean windowOpenClose = windowOpenCloseDigitalSensor.getValue();
            log.info("Температура = " + temp);
            log.info("Окно " + (windowOpenClose?"Открыто":"Закрыто"));
            if( (temp > 30) && !windowOpenClose){
                log.info("Температура в комнате больше 30 и окно закрыто");
                if(airConditioner.getCurrentStatus() != Status.IN_WORK){
                    airConditioner.on();
                }
                break;
            } else {
                log.info("Нормальное состояние");
            }

        }
    }
}
