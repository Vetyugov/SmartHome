import Commands.CmAddToList;
import Commands.CommandsHandler;
import Detectors.TemperatureSensor;
import Detectors.Types.AnalogTypeSensor;
import Detectors.Types.DigitalTypeSensor;
import Detectors.Types.SwitchTypeSensor;
import Detectors.WindowOpenCloseSensor;
import Deviсes.*;
import Tasks.TemperatureMonitoring;
import UserService.UserService;

import UserService.User;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Delayed;

@Slf4j
public class Starter {
    public static void main(String[] args) {

        UserService userService = UserService.getInstance();
        User user1 = userService.getUser("nikita", "123");
        if(user1 != null){
            log.info("Пользователь найден : "+user1.getName());
        } else {
            log.info("Пользователь не найден");
        }

        //Датчики
        AnalogTypeSensor temperatureAnalogSensor = new AnalogTypeSensor(new TemperatureSensor());
        SwitchTypeSensor windowOpenCloseDigitalSensor = new SwitchTypeSensor(new WindowOpenCloseSensor());

        //Управляемые устройства
        AirСonditionerDeviсe airConditioner = (AirСonditionerDeviсe) DeviceFactory.getInstance().createDevice(DeviceTypes.AIR_CONDITIONER);

        try {
            Thread temperatureMonitoring = new TemperatureMonitoring(temperatureAnalogSensor, windowOpenCloseDigitalSensor, airConditioner);
            temperatureMonitoring.start();
            temperatureMonitoring.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        DeviceHandler deviceHandler = new DeviceHandler();
        log.info(deviceHandler.toString());

        new CmAddToList(deviceHandler, airConditioner).execute();
        new CmAddToList(deviceHandler, new TVDevice()).execute();

        log.info(deviceHandler.toString());
        CommandsHandler.getInstance().undo();
        log.info(deviceHandler.toString());


    }
}

