package uiTutuorial.firstLesson;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Use GridLayout for better form layout
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel userNameLabel = new JLabel("Username:");
        JTextField userInput = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passInput = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JLabel output = new JLabel();

        loginButton.addActionListener(e -> {
            String name = userInput.getText();
            output.setText("The user \"" + name + "\" is logged in!");
        });

        panel.add(userNameLabel);
        panel.add(userInput);
        panel.add(passwordLabel);
        panel.add(passInput);
        panel.add(new JLabel()); // empty placeholder
        panel.add(loginButton);
        panel.add(new JLabel()); // another empty cell
        panel.add(output);

        frame.add(panel);
        frame.setVisible(true);
    }
}
