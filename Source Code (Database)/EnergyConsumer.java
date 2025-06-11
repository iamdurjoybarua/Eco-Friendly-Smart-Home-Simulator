abstract class EnergyConsumer {
    private String name; // Name of the energy consumer
    private double powerConsumption; // in Watts
    private boolean status; // true = on, false = off

    public EnergyConsumer(String name, double powerConsumption) {
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.status = false; // Initially off
    }

    public String getName() {
        return name;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public boolean isStatus() {
        return status;
    }

    public void turnOn() {
        this.status = true;
        System.out.println(name + " is turned ON.");
    }

    public void turnOff() {
        this.status = false;
        System.out.println(name + " is turned OFF.");
    }

    public double getEnergyConsumption(double duration) {
        if (status) {
            return (powerConsumption * duration) / 1000.0;
        } else {
            return 0.0;
        }
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    /**
     * Subclasses must implement this to display detailed device status.
     */
    public abstract void displayStatus();

    @Override
    public String toString() {
        return name + " [Power: " + powerConsumption + "W, Status: " + (status ? "ON" : "OFF") + "]";
    }
}
