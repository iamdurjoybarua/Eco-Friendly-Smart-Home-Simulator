// Class representing a smart light, inheriting from EnergyConsumer
public class SmartLight extends EnergyConsumer {
    private int brightness; // 0-100
    private boolean occupancySensor;

    // Constructor for the SmartLight
    public SmartLight(String name, double powerConsumption, boolean occupancySensor) {
        super(name, powerConsumption);
        this.brightness = 100; // Default brightness
        this.occupancySensor = occupancySensor;
    }

    // Getter for the brightness level
    public int getBrightness() {
        return brightness;
    }

    // Method to dim the light to a specific level
    public void dim(int level) {
        if (level >= 0 && level <= 100) {
            this.brightness = level;
            // Adjust power consumption based on brightness (simple linear model)
            super.setPowerConsumption(super.getPowerConsumption() * (level / 100.0));
            System.out.println(getName() + " is dimmed to " + level + "%.");
        } else {
            System.out.println("Invalid brightness level.");
        }
    }

    // Override the turnOn method to include occupancy sensor behavior
    @Override
    public void turnOn() {
        super.turnOn(); // Call the turnOn method of the superclass
        if (occupancySensor) {
            System.out.println(getName() + " turned on due to occupancy.");
        }
    }

    // Override the displayStatus method to show light-specific information
    @Override
    public void displayStatus() {
        System.out.println(getName() + ": Status=" + (isStatus() ? "ON" : "OFF") + ", Brightness=" + brightness + ", Occupancy Sensor=" + (occupancySensor ? "ON" : "OFF"));
    }
}