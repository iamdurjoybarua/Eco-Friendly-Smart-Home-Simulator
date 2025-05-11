// Class representing a smart refrigerator
public class SmartRefrigerator extends EnergyConsumer {
    private double internalTemperature; // in Celsius

    // Constructor for the SmartRefrigerator
    public SmartRefrigerator(String name, double powerConsumption) {
        super(name, powerConsumption);
        this.internalTemperature = 4.0; // Default refrigerator temperature
    }

    // Getter for the internal temperature
    public double getInternalTemperature() {
        return internalTemperature;
    }

    // Setter for the internal temperature
    public void setInternalTemperature(double internalTemperature) {
        this.internalTemperature = internalTemperature;
        System.out.println(getName() + " internal temperature set to " + internalTemperature + "°C.");
    }

    // Override the displayStatus method to show refrigerator-specific information
    @Override
    public void displayStatus() {
        System.out.println(getName() + ": Status=" + (isStatus() ? "ON" : "OFF") + ", Internal Temperature=" + internalTemperature + "°C");
    }
}