package Detectors.Types;

import Detectors.SensorImpl;

/**
 * Двупозиционный датчик (открыт, закрыт)
 */
public class SwitchTypeSensor extends AbstractSensor{
    public SwitchTypeSensor(SensorImpl implementor) {
        super(implementor);
    }

    public boolean getValue(){
        return implementor.getValue() >= 0.5;
    }
}
