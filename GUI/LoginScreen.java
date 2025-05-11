import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private SmartHomeSimulator simulator; // Instance to pass to GUI

    public LoginScreen() {
        setTitle("Smart Home Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        simulator = new SmartHomeSimulator(0.30); // Create the simulator

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For now, let's bypass actual login and directly open the GUI
                openSmartHomeGUI();
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
    }

    private void openSmartHomeGUI() {
    new SmartHomeGUI(simulator); // Create SmartHomeGUI without assigning to a variable
    this.dispose(); // Close the login window
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen();
            }
        });
    }
}