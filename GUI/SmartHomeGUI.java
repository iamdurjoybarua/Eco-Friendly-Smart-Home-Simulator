import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SmartHomeGUI extends JFrame {

    private SmartHomeSimulator simulator;
    private JPanel devicesPanel;

    public SmartHomeGUI(SmartHomeSimulator simulator) {
        this.simulator = simulator;

        setTitle("Smart Home Controller");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        devicesPanel = new JPanel();
        devicesPanel.setLayout(new GridLayout(0, 1, 10, 10));
        add(new JScrollPane(devicesPanel), BorderLayout.CENTER);

        displayDevices(); // Populate the panel with devices

        setVisible(true); // Make the GUI visible
    }

    private void displayDevices() {
        List<EnergyConsumer> devices = simulator.getDevices();
        devicesPanel.removeAll();

        if (devices != null) {
            for (EnergyConsumer device : devices) {
                JPanel deviceInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JLabel nameLabel = new JLabel(device.getName() + ": ");
                JLabel statusLabel = new JLabel("Status: " + (device.isStatus() ? "ON" : "OFF"));
                JButton onButton = new JButton("ON");
                JButton offButton = new JButton("OFF");

                onButton.addActionListener(e -> {
                    device.turnOn();
                    statusLabel.setText("Status: ON");
                    updateDeviceDetails(deviceInfoPanel, device);
                });

                offButton.addActionListener(e -> {
                    device.turnOff();
                    statusLabel.setText("Status: OFF");
                    updateDeviceDetails(deviceInfoPanel, device);
                });

                deviceInfoPanel.add(nameLabel);
                deviceInfoPanel.add(statusLabel);
                deviceInfoPanel.add(onButton);
                deviceInfoPanel.add(offButton);

                // Add specific details and controls based on device type
                if (device instanceof SmartLight) {
                    SmartLight light = (SmartLight) device;
                    JLabel brightnessLabel = new JLabel("Brightness: " + light.getBrightness() + "%");
                    deviceInfoPanel.add(brightnessLabel);

                    JButton dimButton = new JButton("Dim");
                    dimButton.addActionListener(e -> {
                        String brightnessStr = JOptionPane.showInputDialog(SmartHomeGUI.this, "Enter brightness (0-100):", light.getBrightness());
                        if (brightnessStr != null) {
                            try {
                                int newBrightness = Integer.parseInt(brightnessStr);
                                if (newBrightness >= 0 && newBrightness <= 100) {
                                    light.dim(newBrightness);
                                    updateDeviceDetails(deviceInfoPanel, device);
                                } else {
                                    JOptionPane.showMessageDialog(SmartHomeGUI.this, "Invalid brightness value (0-100).");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(SmartHomeGUI.this, "Invalid input. Please enter a number.");
                            }
                        }
                    });
                    deviceInfoPanel.add(dimButton);

                } else if (device instanceof SmartHVAC) {
                    SmartHVAC hvac = (SmartHVAC) device;
                    JLabel tempLabel = new JLabel("Target Temp: " + hvac.getTargetTemperature() + "°C");
                    JLabel fanLabel = new JLabel("Fan: " + hvac.getFanSpeed());
                    deviceInfoPanel.add(tempLabel);
                    deviceInfoPanel.add(fanLabel);

                    JButton setTempButton = new JButton("Set Temp");
                    setTempButton.addActionListener(e -> {
                        String tempStr = JOptionPane.showInputDialog(SmartHomeGUI.this, "Enter target temperature (°C):", hvac.getTargetTemperature());
                        if (tempStr != null) {
                            try {
                                double newTemp = Double.parseDouble(tempStr);
                                hvac.setTargetTemperature(newTemp);
                                updateDeviceDetails(deviceInfoPanel, device);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(SmartHomeGUI.this, "Invalid input. Please enter a number.");
                            }
                        }
                    });
                    deviceInfoPanel.add(setTempButton);

                    JButton setFanButton = new JButton("Set Fan");
                    setFanButton.addActionListener(e -> {
                        String fanStr = JOptionPane.showInputDialog(SmartHomeGUI.this, "Enter fan speed (0-3):", hvac.getFanSpeed());
                        if (fanStr != null) {
                            try {
                                int newFan = Integer.parseInt(fanStr);
                                if (newFan >= 0 && newFan <= 3) {
                                    hvac.setFanSpeed(newFan);
                                    updateDeviceDetails(deviceInfoPanel, device);
                                } else {
                                    JOptionPane.showMessageDialog(SmartHomeGUI.this, "Invalid fan speed (0-3).");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(SmartHomeGUI.this, "Invalid input. Please enter a number.");
                            }
                        }
                    });
                    deviceInfoPanel.add(setFanButton);
                }

                devicesPanel.add(deviceInfoPanel);
            } // Closing brace for the for loop
        } // Closing brace for the if (devices != null) block
    } // Closing brace for displayDevices()

    // Helper method to update the displayed details of a device
    private void updateDeviceDetails(JPanel panel, EnergyConsumer device) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText().startsWith("Status:")) {
                    label.setText("Status: " + (device.isStatus() ? "ON" : "OFF"));
                } else if (device instanceof SmartLight && label.getText().startsWith("Brightness:")) {
                    label.setText("Brightness: " + ((SmartLight) device).getBrightness() + "%");
                } else if (device instanceof SmartHVAC && label.getText().startsWith("Target Temp:")) {
                    label.setText("Target Temp: " + ((SmartHVAC) device).getTargetTemperature() + "°C");
                } else if (device instanceof SmartHVAC && label.getText().startsWith("Fan:")) {
                    label.setText("Fan: " + ((SmartHVAC) device).getFanSpeed());
                }
            }
        }
        panel.revalidate();
        panel.repaint();
    } // Closing brace for updateDeviceDetails()

} // Closing brace for the SmartHomeGUI class