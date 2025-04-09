import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Abstract class representing a generic energy-consuming device
abstract class EnergyConsumer {
    private String name;
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
        // Calculate energy consumption in kWh (kilowatt-hours)
        if (status) {
            return (powerConsumption * duration) / 1000.0;
        } else {
            return 0.0;
        }
    }

    public void setPowerConsumption(double powerConsumption) { // Added setPowerConsumption here
        this.powerConsumption = powerConsumption;
    }

    public abstract void displayStatus(); // Abstract method to be implemented by subclasses
}

// Interface for renewable energy sources
interface RenewableEnergySource {
    double generateEnergy(); // Returns energy generated in Watts
}

// Class representing a smart light, inheriting from EnergyConsumer
class SmartLight extends EnergyConsumer {
    private int brightness; // 0-100
    private boolean occupancySensor;

    public SmartLight(String name, double powerConsumption, boolean occupancySensor) {
        super(name, powerConsumption);
        this.brightness = 100;
        this.occupancySensor = occupancySensor;
    }

    public int getBrightness() {
        return brightness;
    }

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

     public void turnOn() {
        super.turnOn(); // Call the turnOn method of the superclass
        if (occupancySensor) {
            System.out.println(getName() + " turned on due to occupancy.");
        }
    }


    @Override
    public void displayStatus() {
        System.out.println(getName() + ": Status=" + (isStatus() ? "ON" : "OFF") + ", Brightness=" + brightness + ", Occupancy Sensor=" + (occupancySensor ? "ON" : "OFF"));
    }
}

// Class representing a smart HVAC system
class SmartHVAC extends EnergyConsumer {
    private double targetTemperature; // in Celsius
    private double currentTemperature; //in Celsius
    private int fanSpeed; // e.g., 0 (off), 1 (low), 2 (medium), 3 (high)

    public SmartHVAC(String name, double powerConsumption) {
        super(name, powerConsumption);
        this.targetTemperature = 22.0; // Default target temperature
        this.fanSpeed = 0;
        this.currentTemperature = 25.0; //initial
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
    public void setCurrentTemperature(double currentTemperature){
        this.currentTemperature = currentTemperature;
    }

     public double getCurrentTemperature() {
        return currentTemperature;
    }

    @Override
    public void displayStatus() {
        System.out.println(getName() + ": Status=" + (isStatus() ? "ON" : "OFF") + ", Target Temperature=" + targetTemperature + "°C, Fan Speed=" + fanSpeed + ", Current Temperature = " + currentTemperature + "C");
    }
}

// Class representing a smart refrigerator
class SmartRefrigerator extends EnergyConsumer {
    private double internalTemperature; // in Celsius

    public SmartRefrigerator(String name, double powerConsumption) {
        super(name, powerConsumption);
        this.internalTemperature = 4.0; // Default refrigerator temperature
    }

    public double getInternalTemperature() {
        return internalTemperature;
    }

    public void setInternalTemperature(double internalTemperature) {
        this.internalTemperature = internalTemperature;
        System.out.println(getName() + " internal temperature set to " + internalTemperature + "°C.");
    }

    @Override
    public void displayStatus() {
        System.out.println(getName() + ": Status=" + (isStatus() ? "ON" : "OFF") + ", Internal Temperature=" + internalTemperature + "°C");
    }
}

// Class representing solar panels, implementing RenewableEnergySource
class SolarPanel implements RenewableEnergySource {
    private double surfaceArea; // in square meters
    private double efficiency; // as a decimal (e.g., 0.2 for 20%)
    private double sunlightIntensity; // in Watts per square meter

    public SolarPanel(double surfaceArea, double efficiency) {
        this.surfaceArea = surfaceArea;
        this.efficiency = efficiency;
        this.sunlightIntensity = 0.0; // Initially no sunlight
    }

    public void setSunlightIntensity(double sunlightIntensity) {
        this.sunlightIntensity = sunlightIntensity;
    }

    @Override
    public double generateEnergy() {
        // Calculate energy generated: Area * Intensity * Efficiency
        return surfaceArea * sunlightIntensity * efficiency;
    }

     public double getSurfaceArea() {
        return surfaceArea;
    }
}

// Class representing a wind turbine
class WindTurbine implements RenewableEnergySource {
    private double bladeDiameter; // in meters
    private double windSpeed; // in meters per second
    private double efficiency;

    public WindTurbine(double bladeDiameter, double efficiency) {
        this.bladeDiameter = bladeDiameter;
        this.windSpeed = 0.0;
        this.efficiency = efficiency;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public double generateEnergy() {
        // Simplified power calculation:  Power = 0.5 * airDensity * sweptArea * windSpeed^3 * efficiency
        double airDensity = 1.225; // kg/m^3 (standard air density)
        double sweptArea = Math.PI * Math.pow(bladeDiameter / 2, 2);
        return 0.5 * airDensity * sweptArea * Math.pow(windSpeed, 3) * efficiency;
    }
     public double getBladeDiameter() {
        return bladeDiameter;
    }
}

// Class to simulate the smart home environment
public class SmartHomeSimulator {
    private List<EnergyConsumer> devices;
    private List<RenewableEnergySource> renewableSources;
    private double electricityPricePerKWh; // in dollars

    public SmartHomeSimulator(double electricityPricePerKWh) {
        this.devices = new ArrayList<>();
        this.renewableSources = new ArrayList<>();
        this.electricityPricePerKWh = electricityPricePerKWh;
    }

    public void addDevice(EnergyConsumer device) {
        this.devices.add(device);
    }

    public void addRenewableSource(RenewableEnergySource source) {
        this.renewableSources.add(source);
    }

    public void simulate(double duration) {
        double totalEnergyConsumption = 0.0;
        double totalRenewableEnergy = 0.0;

        // Simulate each device
        for (EnergyConsumer device : devices) {
            double deviceEnergy = device.getEnergyConsumption(duration);
            totalEnergyConsumption += deviceEnergy;
            System.out.println(device.getName() + " consumed " + deviceEnergy + " kWh.");
            device.displayStatus();
        }

        // Simulate renewable energy sources
        for (RenewableEnergySource source : renewableSources) {
            double energy = source.generateEnergy();
            totalRenewableEnergy += energy / 1000.0; // Convert Watts to kWh
        }

        // Calculate net energy consumption
        double netEnergyConsumption = totalEnergyConsumption - totalRenewableEnergy;
        if(netEnergyConsumption < 0){
            netEnergyConsumption = 0;
        }

        double cost = netEnergyConsumption * electricityPricePerKWh;

        System.out.println("\n--- Simulation Results ---");
        System.out.println("Total Energy Consumption: " + totalEnergyConsumption + " kWh");
        System.out.println("Total Renewable Energy Generated: " + totalRenewableEnergy + " kWh");
        System.out.println("Net Energy Consumption: " + netEnergyConsumption + " kWh");
        System.out.println("Total Cost: $" + cost);
        System.out.println("--- End Simulation ---");
    }

    public static void main(String[] args) {
        // Create a SmartHomeSimulator object
        SmartHomeSimulator simulator = new SmartHomeSimulator(0.30); // Electricity price: $0.30 per kWh

        // Create smart home devices
        SmartLight livingRoomLight = new SmartLight("Living Room Light", 15, true); // 15W LED with occupancy sensor
        SmartLight bedroomLight = new SmartLight("Bedroom Light", 10, false); // 10W LED
        SmartHVAC hvac = new SmartHVAC("HVAC System", 1000); // 1000W
        SmartRefrigerator fridge = new SmartRefrigerator("Refrigerator", 150); // 150W

        // Create renewable energy sources
        SolarPanel solarPanel = new SolarPanel(10, 0.2); // 10 m^2, 20% efficiency
        WindTurbine windTurbine = new WindTurbine(5, 0.3);  // 5m blade diameter, 30% efficiency

        // Add devices and sources to the simulator
        simulator.addDevice(livingRoomLight);
        simulator.addDevice(bedroomLight);
        simulator.addDevice(hvac);
        simulator.addDevice(fridge);
        simulator.addRenewableSource(solarPanel);
        simulator.addRenewableSource(windTurbine);

        //turn on
        livingRoomLight.turnOn();
        bedroomLight.turnOn();
        hvac.turnOn();
        fridge.turnOn();

        // Simulate for 24 hours (in hours)
        double simulationDuration = 24.0;

        //set initial temperatures
        hvac.setCurrentTemperature(28);

        // Simulate environmental conditions (example: vary sunlight and wind speed)
        Random random = new Random();
        for (int hour = 0; hour < simulationDuration; hour++) {
            // Simulate sunlight intensity (peaking at noon)
            double sunlightIntensity = 1000 * Math.max(0, 1 - Math.abs(hour - 12) / 12.0); // Example: sinusoidal
            solarPanel.setSunlightIntensity(sunlightIntensity);

            // Simulate wind speed
            double windSpeed = 0 + random.nextDouble() * 10; // Wind speed between 0 and 10 m/s
            windTurbine.setWindSpeed(windSpeed);
            hvac.setCurrentTemperature(hvac.getCurrentTemperature() - 0.5 + random.nextDouble() * 1);

            System.out.println("\n--- Hour " + hour + " ---");
            System.out.println("Sunlight Intensity: " + sunlightIntensity + " W/m^2");
            System.out.println("Wind Speed: " + windSpeed + " m/s");

            // Example usage of device methods
            if (hour == 6) {
                livingRoomLight.dim(20); // Dim the living room light at 6 AM
                hvac.setTargetTemperature(24);
                hvac.setFanSpeed(1);
            } else if (hour == 18) {
                livingRoomLight.dim(80); // Dim the living room light at 6 PM
                hvac.setTargetTemperature(22);
                hvac.setFanSpeed(2);
            } else if (hour == 22){
                hvac.turnOff();
            }

            simulator.simulate(1.0); // Simulate for one hour
        }
        livingRoomLight.turnOff();
        bedroomLight.turnOff();
    }
}

