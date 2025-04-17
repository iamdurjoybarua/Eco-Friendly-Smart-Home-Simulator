// Class representing a smart HVAC system
class SmartHVAC extends EnergyConsumer {
    private double targetTemperature; // in Celsius
    private double currentTemperature; // in Celsius
    private int fanSpeed; // e.g., 0 (off), 1 (low), 2 (medium), 3 (high)

    // Constructor for the SmartHVAC
    public SmartHVAC(String name, double powerConsumption) {
        super(name, powerConsumption);
        this.targetTemperature = 22.0; // Default target temperature
        this.fanSpeed = 0;
        this.currentTemperature = 25.0; // Initial current temperature
    }

    // Getter for the target temperature
    public double getTargetTemperature() {
        return targetTemperature;
    }

    // Setter for the target temperature
    public void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
        System.out.println(getName() + " target temperature set to " + targetTemperature + "°C.");
    }

    // Getter for the fan speed
    public int getFanSpeed() {
        return fanSpeed;
    }

    // Setter for the fan speed and adjusts power consumption accordingly
    public void setFanSpeed(int fanSpeed) {
        if (fanSpeed >= 0 && fanSpeed <= 3) {
            this.fanSpeed = fanSpeed;
            // Adjust power consumption based on fan speed (example)
            switch (fanSpeed) {
                case 0:
                    super.setPowerConsumption(0); // Off
                    break;
                case 1:
                    super.setPowerConsumption(100); // Low
                    break;
                case 2:
                    super.setPowerConsumption(300); // Medium
                    break;
                case 3:
                    super.setPowerConsumption(500); // High
                    break;
            }
            System.out.println(getName() + " fan speed set to " + fanSpeed + ".");
        } else {
            System.out.println("Invalid fan speed.");
        }
    }

    // Setter for the current temperature
    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    // Getter for the current temperature
    public double getCurrentTemperature() {
        return currentTemperature;
    }

    // Override the displayStatus method to show HVAC-specific information
    @Override
    public void displayStatus() {
        System.out.println(getName() + ": Status=" + (isStatus() ? "ON" : "OFF") + ", Target Temperature=" + targetTemperature + "°C, Fan Speed=" + fanSpeed + ", Current Temperature = " + currentTemperature + "C");
    }
}
