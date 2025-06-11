import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDatabase {
    private static final String DB_URL = "jdbc:sqlite:smarthome.db";
    private Connection conn;

    public DeviceDatabase() {
        connect();
        createTables();
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to SQLite database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        String createDevicesTable = """
            CREATE TABLE IF NOT EXISTS devices (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                type TEXT NOT NULL,
                status INTEGER NOT NULL,
                brightness INTEGER,
                targetTemperature REAL,
                fanSpeed INTEGER
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createDevicesTable);
            System.out.println("Devices table ensured.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EnergyConsumer> loadDevices() {
        List<EnergyConsumer> devices = new ArrayList<>();
        String sql = "SELECT * FROM devices";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                boolean status = rs.getInt("status") == 1;

                switch (type) {
                    case "SmartLight" -> {
                        int brightness = rs.getInt("brightness");
                        SmartLight light = new SmartLight(name, 10, false); // example wattage
                        light.setStatus(status);
                        light.dim(brightness);
                        devices.add(light);
                    }
                    case "SmartHVAC" -> {
                        double targetTemp = rs.getDouble("targetTemperature");
                        int fanSpeed = rs.getInt("fanSpeed");
                        SmartHVAC hvac = new SmartHVAC(name, 1000); // example wattage
                        hvac.setStatus(status);
                        hvac.setTargetTemperature(targetTemp);
                        hvac.setFanSpeed(fanSpeed);
                        devices.add(hvac);
                    }
                    case "SmartRefrigerator" -> {
                        SmartRefrigerator fridge = new SmartRefrigerator(name, 150); // example wattage
                        fridge.setStatus(status);
                        devices.add(fridge);
                    }
                    default -> System.out.println("Unknown device type: " + type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    public void saveDevice(EnergyConsumer device) {
        String selectSQL = "SELECT id FROM devices WHERE name = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
            selectStmt.setString(1, device.getName());
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                updateDevice(device, id);
            } else {
                insertDevice(device);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertDevice(EnergyConsumer device) {
        String insertSQL = """
            INSERT INTO devices (name, type, status, brightness, targetTemperature, fanSpeed)
            VALUES (?, ?, ?, ?, ?, ?);
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, device.getName());
            pstmt.setString(2, getDeviceType(device));
            pstmt.setInt(3, device.isStatus() ? 1 : 0);

            if (device instanceof SmartLight light) {
                pstmt.setInt(4, light.getBrightness());
                pstmt.setNull(5, Types.REAL);
                pstmt.setNull(6, Types.INTEGER);
            } else if (device instanceof SmartHVAC hvac) {
                pstmt.setNull(4, Types.INTEGER);
                pstmt.setDouble(5, hvac.getTargetTemperature());
                pstmt.setInt(6, hvac.getFanSpeed());
            } else {
                pstmt.setNull(4, Types.INTEGER);
                pstmt.setNull(5, Types.REAL);
                pstmt.setNull(6, Types.INTEGER);
            }
            pstmt.executeUpdate();
            System.out.println("Inserted device: " + device.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateDevice(EnergyConsumer device, int id) {
        String updateSQL = """
            UPDATE devices
            SET status = ?, brightness = ?, targetTemperature = ?, fanSpeed = ?
            WHERE id = ?;
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setInt(1, device.isStatus() ? 1 : 0);

            if (device instanceof SmartLight light) {
                pstmt.setInt(2, light.getBrightness());
                pstmt.setNull(3, Types.REAL);
                pstmt.setNull(4, Types.INTEGER);
            } else if (device instanceof SmartHVAC hvac) {
                pstmt.setNull(2, Types.INTEGER);
                pstmt.setDouble(3, hvac.getTargetTemperature());
                pstmt.setInt(4, hvac.getFanSpeed());
            } else {
                pstmt.setNull(2, Types.INTEGER);
                pstmt.setNull(3, Types.REAL);
                pstmt.setNull(4, Types.INTEGER);
            }

            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Updated device: " + device.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getDeviceType(EnergyConsumer device) {
        if (device instanceof SmartLight) return "SmartLight";
        if (device instanceof SmartHVAC) return "SmartHVAC";
        if (device instanceof SmartRefrigerator) return "SmartRefrigerator";
        return "Unknown";
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("SQLite connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
