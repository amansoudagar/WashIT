package Wash_package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class loginowner {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public loginowner() {
        frame = new JFrame("OWNER LOGIN");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);

        panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(600, 300, 80, 25);
        panel.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(700, 300, 200, 25);
        panel.add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(600, 340, 80, 25);
        panel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(700, 340, 200, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(750, 390, 100, 25);
        panel.add(loginButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\amans\\OneDrive\\Pictures\\JAVA\\LoginImage.jpg"));
        lblNewLabel.setBounds(0, 0, 1920, 1080);
        panel.add(lblNewLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginOwner();
            }
        });

        frame.setVisible(true);
    }

    private void loginOwner() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wash_itdb", "root", "");

            String sql = "SELECT * FROM laundry_owner WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Successful login
                JOptionPane.showMessageDialog(frame, "Login successful!");
            } else {
                // Failed login
                JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle database-related errors, show an error message, or log the exception
            JOptionPane.showMessageDialog(frame, "Error during login. Please try again.");
        }
    }
}

public class login_owner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new loginowner();
        });
    }
}