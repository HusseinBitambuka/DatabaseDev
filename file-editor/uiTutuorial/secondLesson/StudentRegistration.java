package uiTutuorial.secondLesson;

import javax.swing.*;
import java.awt.*;

public class StudentRegistration {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // Center form panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Name & Email
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();

        // Gender
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        JPanel genderPanel = new JPanel();
        genderPanel.add(male);
        genderPanel.add(female);

        // Courses
        JCheckBox java = new JCheckBox("Java");
        JCheckBox python = new JCheckBox("Python");
        JCheckBox ai = new JCheckBox("AI");
        JPanel coursePanel = new JPanel();
        coursePanel.add(java);
        coursePanel.add(python);
        coursePanel.add(ai);

        // Academic Level
        JComboBox<String> levelBox = new JComboBox<>(new String[] {
                "Undergraduate", "Graduate", "PhD"
        });

        // Notes
        JTextArea notesArea = new JTextArea(4, 20);
        JScrollPane notesScroll = new JScrollPane(notesArea);

        // Output
        JLabel result = new JLabel();

        // Buttons
        JButton submit = new JButton("Submit");
        JButton clear = new JButton("Clear");

        submit.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "Not selected");

            StringBuilder courses = new StringBuilder();
            if (java.isSelected())
                courses.append("Java ");
            if (python.isSelected())
                courses.append("Python ");
            if (ai.isSelected())
                courses.append("AI ");

            String level = (String) levelBox.getSelectedItem();
            String notes = notesArea.getText();

            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name and Email are required!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            result.setText("<html><h3>Registration Summary</h3>"
                    + "<b>Name:</b> " + name + "<br>"
                    + "<b>Email:</b> " + email + "<br>"
                    + "<b>Gender:</b> " + gender + "<br>"
                    + "<b>Courses:</b> " + courses + "<br>"
                    + "<b>Level:</b> " + level + "<br>"
                    + "<b>Notes:</b> " + notes + "</html>");
        });

        clear.addActionListener(e -> {
            nameField.setText("");
            emailField.setText("");
            genderGroup.clearSelection();
            java.setSelected(false);
            python.setSelected(false);
            ai.setSelected(false);
            levelBox.setSelectedIndex(0);
            notesArea.setText("");
            result.setText("");
        });

        // Add form components
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderPanel);
        formPanel.add(new JLabel("Courses:"));
        formPanel.add(coursePanel);
        formPanel.add(new JLabel("Level:"));
        formPanel.add(levelBox);
        formPanel.add(new JLabel("Notes:"));
        formPanel.add(notesScroll);

        // Bottom button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submit);
        buttonPanel.add(clear);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(result, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}
