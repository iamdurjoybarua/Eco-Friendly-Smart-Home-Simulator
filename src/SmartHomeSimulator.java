import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
