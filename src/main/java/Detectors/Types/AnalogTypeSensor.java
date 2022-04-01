package Detectors.Types;

import Detectors.SensorImpl;

public class AnalogTypeSensor extends AbstractSensor{

    public AnalogTypeSensor(SensorImpl implementor) {
        super(implementor);
    }
    public double getValue() {
        return implementor.getValue();
    }

}
