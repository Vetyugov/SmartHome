package Detectors;

public class TemperatureSensor implements SensorImpl {
    private int range = 100;
    @Override
    public double getValue() {
        return Math.random()*range;
    }
}
