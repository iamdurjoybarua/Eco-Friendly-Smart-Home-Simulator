import javax.swing.SwingUtilities;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create database helper
        DeviceDatabase db = new DeviceDatabase();

        // Load devices from database or create default ones if none found
        List<EnergyConsumer> devices = db.loadDevices();

        if (devices.isEmpty()) {
            System.out.println("No devices found in database. Creating default devices...");

            SmartLight livingRoomLight = new SmartLight("Living Room Light", 15, true);
            SmartLight bedroomLight = new SmartLight("Bedroom Light", 10, false);
            SmartHVAC hvac = new SmartHVAC("HVAC System", 1000);
            SmartRefrigerator fridge = new SmartRefrigerator("Refrigerator", 150);

            devices.add(livingRoomLight);
            devices.add(bedroomLight);
            devices.add(hvac);
            devices.add(fridge);

            // Save default devices to DB
            for (EnergyConsumer device : devices) {
                db.saveDevice(device);
            }
        } else {
            System.out.println("Loaded " + devices.size() + " devices from database.");
        }

        // Create simulator and set loaded devices
        SmartHomeSimulator simulator = new SmartHomeSimulator(0.12); // electricity price per kWh
        simulator.getDevices().clear();
        simulator.getDevices().addAll(devices);

        // Launch GUI on the Swing event dispatch thread
        SwingUtilities.invokeLater(() -> new SmartHomeGUI(simulator, db));
    }
}
