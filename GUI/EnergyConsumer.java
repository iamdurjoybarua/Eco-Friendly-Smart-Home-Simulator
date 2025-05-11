// Abstract class representing a generic energy-consuming device
public abstract class EnergyConsumer {
    private String name; // Name of the energy consumer
    private double powerConsumption; // in Watts
    private boolean status; // true = on, false = off

    // Constructor to initialize the EnergyConsumer
    public EnergyConsumer(String name, double powerConsumption) {
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.status = false; // Initially off
    }

    // Getter for the name of the device
    public String getName() {
        return name;
    }

    // Getter for the power consumption of the device
    public double getPowerConsumption() {
        return powerConsumption;
    }

    // Getter for the current status of the device (on/off)
    public boolean isStatus() {
        return status;
    }

    // Method to turn the device on
    public void turnOn() {
        this.status = true;
        System.out.println(name + " is turned ON.");
    }

    // Method to turn the device off
    public void turnOff() {
        this.status = false;
        System.out.println(name + " is turned OFF.");
    }

    // Method to calculate the energy consumption over a given duration
    public double getEnergyConsumption(double duration) {
        // Calculate energy consumption in kWh (kilowatt-hours)
        if (status) {
            return (powerConsumption * duration) / 1000.0;
        } else {
            return 0.0;
        }
    }

    // Setter for the power consumption of the device (added for flexibility)
    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    // Abstract method to display the specific status of the device
    public abstract void displayStatus();
}