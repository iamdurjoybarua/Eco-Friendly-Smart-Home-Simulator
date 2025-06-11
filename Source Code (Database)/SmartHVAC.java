public class SmartHVAC extends EnergyConsumer {
    private double targetTemperature;
    private int fanSpeed; // e.g., 0 to 3

    public SmartHVAC(String name, double powerConsumption) {
        super(name, powerConsumption);
        this.targetTemperature = 22.0; // default 22°C
        this.fanSpeed = 1; // default fan speed
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
        System.out.println(getName() + " target temperature set to " + targetTemperature + "°C.");
    }

    public int getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(int fanSpeed) {
        this.fanSpeed = fanSpeed;
        System.out.println(getName() + " fan speed set to " + fanSpeed + ".");
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
        System.out.println(getName() + " is " + (isStatus() ? "ON" : "OFF") + ", target temperature: " + targetTemperature + "°C, fan speed: " + fanSpeed);
    }
}
