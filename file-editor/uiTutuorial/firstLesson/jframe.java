package ui.firstLesson;

import javax.swing.*;

public class jframe {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Greeting App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        JPanel panel = new JPanel(); // Default is FlowLayout
        JLabel label = new JLabel("Enter your name:");
        JTextField textField = new JTextField(30);
        JButton button = new JButton("Greet");
        JLabel output = new JLabel();

        button.addActionListener(e -> {
            String name = textField.getText();
            output.setText("Hello, " + name + "!");
        });

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.add(output);

        frame.add(panel);
        frame.setVisible(true);
    }
}
