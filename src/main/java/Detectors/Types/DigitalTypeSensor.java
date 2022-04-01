package Detectors.Types;

import Detectors.SensorImpl;

public class DigitalTypeSensor extends AbstractSensor{

    public DigitalTypeSensor(SensorImpl implementor) {
        super(implementor);
    }

    public int getValue(){
        return (int)implementor.getValue();
    }
}
