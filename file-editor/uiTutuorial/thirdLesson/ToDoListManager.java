package uiTutuorial.thirdLesson;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ToDoListManager {
    public static void main(String[] args) {
        // Set up frame
        JFrame frame = new JFrame("To-Do List Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        // Table columns and model
        String[] columns = { "Task", "Status" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Input panel
        JPanel inputPanel = new JPanel();
        JTextField taskField = new JTextField(20);
        JComboBox<String> statusBox = new JComboBox<>(new String[] { "Pending", "Done" });
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Selected");

        inputPanel.add(new JLabel("Task:"));
        inputPanel.add(taskField);
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(statusBox);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Add task button logic
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            String status = (String) statusBox.getSelectedItem();
            if (!task.isEmpty()) {
                model.addRow(new Object[] { task, status });
                taskField.setText("");
                statusBox.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(frame, "Task field cannot be empty.", "Input Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Delete selected row logic
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to delete.", "No Selection",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Final layout
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
