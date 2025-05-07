package uiTutuorial.thirdLesson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ContactBook {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Contact Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        // Table setup
        String[] columns = { "Name", "Email", "Phone" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField nameField = new JTextField(10);
        JTextField emailField = new JTextField(12);
        JTextField phoneField = new JTextField(10);
        JButton addButton = new JButton("Add Contact");
        JButton deleteButton = new JButton("Delete Selected");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Add button action
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            model.addRow(new Object[] { name, email, phone });
            nameField.setText("");
            emailField.setText("");
            phoneField.setText("");
        });

        // Delete button action
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a contact to delete.", "No Selection",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Layout
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
