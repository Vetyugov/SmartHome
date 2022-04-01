package Detectors;

public class WindowOpenCloseSensor implements SensorImpl{
    private int range = 1;
    @Override
    public double getValue() {
        return Math.random()*range;
    }
}
