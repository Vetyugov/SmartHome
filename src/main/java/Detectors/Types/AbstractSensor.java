package Detectors.Types;

import Detectors.SensorImpl;

public abstract class AbstractSensor {
    SensorImpl implementor;
    public AbstractSensor(SensorImpl implementor) {
        this.implementor = implementor;
    }

}
