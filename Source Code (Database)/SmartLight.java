public class SmartLight extends EnergyConsumer {
    private int brightness; // 0-100%
    private boolean occupancySensor;

    public SmartLight(String name, double powerConsumption, boolean occupancySensor) {
        super(name, powerConsumption);
        this.occupancySensor = occupancySensor;
        this.brightness = 100; // default full brightness
    }

    public int getBrightness() {
        return brightness;
    }

    public void dim(int brightness) {
        this.brightness = brightness;
        System.out.println(getName() + " brightness set to " + brightness + "%.");
    }

    public boolean hasOccupancySensor() {
        return occupancySensor;
    }

    public void setStatus(boolean status) {
        if (status) {
            turnOn();
        } else {
            turnOff();
        }
    }

    @Override
    public void displayStatus() {
        System.out.println(getName() + " is " + (isStatus() ? "ON" : "OFF") + " with brightness " + brightness + "%.");
    }
}
