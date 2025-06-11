import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private SmartHomeSimulator simulator;
    private DeviceDatabase db;
    private Map<String, String> users = new HashMap<>();

    public LoginScreen() {
        setTitle("Smart Home Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");

        loadUsers();  // Load users from users.txt

        loginButton.addActionListener(e -> {
            if (authenticate()) {
                openSmartHomeGUI();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialize your simulator and DB instances here
        db = new DeviceDatabase();
        simulator = new SmartHomeSimulator(0.30);
    }

    private void loadUsers() {
        File file = new File("users.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "users.txt not found! Please create the file with user credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty() || !line.contains(":")) continue;
                String[] parts = line.split(":", 2);
                users.put(parts[0].trim(), parts[1].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean authenticate() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private void openSmartHomeGUI() {
        new SmartHomeGUI(simulator, db);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
