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

        // Optional: Turn on initial devices here or via GUI
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

    // Method to simulate the energy consumption and generation over a given duration (in hours)
    public void simulate(double duration) {
        double totalEnergyConsumption = 0.0;
        double totalRenewableEnergy = 0.0;

        // Simulate each device
        for (EnergyConsumer device : devices) {
            double deviceEnergy = device.getEnergyConsumption(duration);
            totalEnergyConsumption += deviceEnergy;
            System.out.println(device.getName() + " consumed " + String.format("%.3f", deviceEnergy) + " kWh.");
            device.displayStatus();
        }

        // Simulate renewable energy sources
        for (RenewableEnergySource source : renewableSources) {
            double energy = source.generateEnergy(); // Watts
            totalRenewableEnergy += energy * duration / 1000.0; // Convert Watts over duration to kWh
        }

        // Calculate net energy consumption (consumption - generation)
        double netEnergyConsumption = totalEnergyConsumption - totalRenewableEnergy;
        if (netEnergyConsumption < 0) {
            netEnergyConsumption = 0;
        }

        // Calculate the total cost of electricity
        double cost = netEnergyConsumption * electricityPricePerKWh;

        // Print the simulation results
        System.out.println("\n--- Simulation Results ---");
        System.out.println("Total Energy Consumption: " + String.format("%.3f", totalEnergyConsumption) + " kWh");
        System.out.println("Total Renewable Energy Generated: " + String.format("%.3f", totalRenewableEnergy) + " kWh");
        System.out.println("Net Energy Consumption: " + String.format("%.3f", netEnergyConsumption) + " kWh");
        System.out.println("Total Cost: $" + String.format("%.2f", cost)); // Format cost to 2 decimal places
        System.out.println("--- End Simulation ---");
    }

    // Getter for the list of devices
    public List<EnergyConsumer> getDevices() {
        return this.devices;
    }

    // Main method (for potential text-based testing or setup)
    public static void main(String[] args) {
        System.out.println("SmartHomeSimulator initialized. GUI is controlled by LoginScreen.");
    }
}
