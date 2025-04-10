import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Abstract class representing a generic energy-consuming device
abstract class EnergyConsumer {
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

// Interface for renewable energy sources
interface RenewableEnergySource {
    // Method to generate energy (in Watts)
    double generateEnergy();
}

// Class representing a smart light, inheriting from EnergyConsumer
class SmartLight extends EnergyConsumer {
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

// Class representing a smart refrigerator
class SmartRefrigerator extends EnergyConsumer {
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

// Class representing solar panels, implementing RenewableEnergySource
class SolarPanel implements RenewableEnergySource {
    private double surfaceArea; // in square meters
    private double efficiency; // as a decimal (e.g., 0.2 for 20%)
    private double sunlightIntensity; // in Watts per square meter

    // Constructor for the SolarPanel
    public SolarPanel(double surfaceArea, double efficiency) {
        this.surfaceArea = surfaceArea;
        this.efficiency = efficiency;
        this.sunlightIntensity = 0.0; // Initially no sunlight
    }

    // Setter for the sunlight intensity
    public void setSunlightIntensity(double sunlightIntensity) {
        this.sunlightIntensity = sunlightIntensity;
    }

    // Override the generateEnergy method to calculate energy produced by the solar panel
    @Override
    public double generateEnergy() {
        // Calculate energy generated: Area * Intensity * Efficiency
        return surfaceArea * sunlightIntensity * efficiency;
    }

    // Getter for the surface area of the solar panel
    public double getSurfaceArea() {
        return surfaceArea;
    }
}

// Class representing a wind turbine
class WindTurbine implements RenewableEnergySource {
    private double bladeDiameter; // in meters
    private double windSpeed; // in meters per second
    private double efficiency;

    // Constructor for the WindTurbine
    public WindTurbine(double bladeDiameter, double efficiency) {
        this.bladeDiameter = bladeDiameter;
        this.windSpeed = 0.0;
        this.efficiency = efficiency;
    }

    // Setter for the wind speed
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    // Override the generateEnergy method to calculate energy produced by the wind turbine
    @Override
    public double generateEnergy() {
        // Simplified power calculation:  Power = 0.5 * airDensity * sweptArea * windSpeed^3 * efficiency
        double airDensity = 1.225; // kg/m^3 (standard air density)
        double sweptArea = Math.PI * Math.pow(bladeDiameter / 2, 2);
        return 0.5 * airDensity * sweptArea * Math.pow(windSpeed, 3) * efficiency;
    }

    // Getter for the blade diameter of the wind turbine
    public double getBladeDiameter() {
        return bladeDiameter;
    }
}

// Class to simulate the smart home environment
public class SmartHomeSimulator {
    private List<EnergyConsumer> devices; // List to hold all energy-consuming devices
    private List<RenewableEnergySource> renewableSources; // List to hold all renewable energy sources
    private double electricityPricePerKWh; // in dollars

    // Constructor for the SmartHomeSimulator
    public SmartHomeSimulator(double electricityPricePerKWh) {
        this.devices = new ArrayList<>();
        this.renewableSources = new ArrayList<>();
        this.electricityPricePerKWh = electricityPricePerKWh;
    }

    // Method to add an energy-consuming device to the simulator
    public void addDevice(EnergyConsumer device) {
        this.devices.add(device);
    }

    // Method to add a renewable energy source to the simulator
    public void addRenewableSource(RenewableEnergySource source) {
        this.renewableSources.add(source);
    }

    // Method to simulate the energy consumption and generation over a given duration
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

        // Calculate net energy consumption (consumption - generation)
        double netEnergyConsumption = totalEnergyConsumption - totalRenewableEnergy;
        // If renewable energy generated is more than consumed, net consumption is 0
        if (netEnergyConsumption < 0) {
            netEnergyConsumption = 0;
        }

        // Calculate the total cost of electricity
        double cost = netEnergyConsumption * electricityPricePerKWh;

        // Print the simulation results
        System.out.println("\n--- Simulation Results ---");
        System.out.println("Total Energy Consumption: " + totalEnergyConsumption + " kWh");
        System.out.println("Total Renewable Energy Generated: " + totalRenewableEnergy + " kWh");
        System.out.println("Net Energy Consumption: " + netEnergyConsumption + " kWh");
        System.out.println("Total Cost: $" + String.format("%.2f", cost)); // Format cost to 2 decimal places
        System.out.println("--- End Simulation ---");
    }

    // Main method to run the smart home simulator
    public static void main(String[] args) {
        // Create a SmartHomeSimulator object with an electricity price of $0.30 per kWh
        SmartHomeSimulator simulator = new SmartHomeSimulator(0.30);

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

        // Turn on initial devices
        livingRoomLight.turnOn();
        bedroomLight.turnOn();
        hvac.turnOn();
        fridge.turnOn();

        // Simulate for 24 hours (in hours)
        double simulationDuration = 24.0;

        // Set initial temperature for HVAC
        hvac.setCurrentTemperature(28);

        // Create a Random object for simulating environmental conditions
        Random random = new Random();
        // Loop through each hour of the simulation
        for (int hour = 0; hour < simulationDuration; hour++) {
            // Simulate sunlight intensity (peaking at noon with a sinusoidal pattern)
            double sunlightIntensity = 1000 * Math.max(0, 1 - Math.abs(hour - 12) / 12.0);
            solarPanel.setSunlightIntensity(sunlightIntensity);

            // Simulate wind speed (random value between 0 and 10 m/s)
            double windSpeed = 0 + random.nextDouble() * 10;
            windTurbine.setWindSpeed(windSpeed);

            // Simulate change in current temperature for HVAC
            hvac.setCurrentTemperature(hvac.getCurrentTemperature() - 0.5 + random.nextDouble() * 1);

            // Print the environmental conditions for the current hour
            System.out.println("\n--- Hour " + hour + " ---");
            System.out.println("Sunlight Intensity: " + sunlightIntensity + " W/m^2");
            System.out.println("Wind Speed: " + windSpeed + " m/s");

            // Example usage of device methods based on the hour
            if (hour == 6) {
                livingRoomLight.dim(20); // Dim the living room light at 6 AM
                hvac.setTargetTemperature(24);
                hvac.setFanSpeed(1);
            } else if (hour == 18) {
                livingRoomLight.dim(80); // Dim the living room light at 6 PM
                hvac.setTargetTemperature(22);
                hvac.setFanSpeed(2);
            } else if (hour == 22) {
                hvac.turnOff(); // Turn off HVAC at 10 PM
            }

            // Simulate energy consumption and generation for this hour
            simulator.simulate(1.0); // Simulate for one hour
        }
        // Turn off the lights at the end of the simulation
        livingRoomLight.turnOff();
        bedroomLight.turnOff();
    }
}
