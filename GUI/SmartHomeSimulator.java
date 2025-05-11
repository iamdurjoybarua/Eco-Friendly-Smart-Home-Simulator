import java.util.ArrayList;
import java.util.List;

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
        initializeDevicesAndSources(); // Initialize devices and sources here
    }

    private void initializeDevicesAndSources() {
        // Create smart home devices
        SmartLight livingRoomLight = new SmartLight("Living Room Light", 15, true); // 15W LED with occupancy sensor
        SmartLight bedroomLight = new SmartLight("Bedroom Light", 10, false); // 10W LED
        SmartHVAC hvac = new SmartHVAC("HVAC System", 1000); // 1000W
        SmartRefrigerator fridge = new SmartRefrigerator("Refrigerator", 150); // 150W

        // Create renewable energy sources
        SolarPanel solarPanel = new SolarPanel(10, 0.2); // 10 m^2, 20% efficiency
        WindTurbine windTurbine = new WindTurbine(5, 0.3);  // 5m blade diameter, 30% efficiency

        // Add devices and sources to the simulator
        addDevice(livingRoomLight);
        addDevice(bedroomLight);
        addDevice(hvac);
        addDevice(fridge);
        addRenewableSource(solarPanel);
        addRenewableSource(windTurbine);

        // Turn on initial devices (optional, GUI can handle initial state)
        // livingRoomLight.turnOn();
        // bedroomLight.turnOn();
        // hvac.turnOn();
        // fridge.turnOn();
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

    // Getter for the list of devices
    public List<EnergyConsumer> getDevices() {
        return this.devices;
    }

    // Main method (primarily for initial setup or potential text-based testing)
    public static void main(String[] args) {
        // For GUI, you would typically run the LoginScreen's main method.
        // This main method in SmartHomeSimulator could be used for initial setup
        // or if you wanted to run some text-based simulations independently.
        System.out.println("SmartHomeSimulator initialized. GUI is controlled by LoginScreen.");
    }
}