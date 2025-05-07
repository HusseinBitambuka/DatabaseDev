package com.editor.ui;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditorUI extends JFrame {

    private JTextArea textArea; // The text area where the user can input/edit text
    private File currentFile; // To keep track of the current file being edited

    // Constructor to set up the UI
    public TextEditorUI() {
        // Set the title of the window
        super("Java Text Editor");

        // Initialize components
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Setting the default font for the text area

        // Set the layout for the frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // File Menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Open action
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(e -> openFile());
        fileMenu.add(openItem);

        // Save action
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> saveFile());
        fileMenu.add(saveItem);

        // Exit action
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Window Settings
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
    }

    // Method to handle opening a file
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea.read(reader, null); // Read file content into text area
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "File could not be opened.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to handle saving a file
    private void saveFile() {
        if (currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            } else {
                return; // User canceled, return early
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            textArea.write(writer); // Write text area content to file
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File could not be saved.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to run the editor
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextEditorUI editor = new TextEditorUI();
            editor.setVisible(true);
        });
    }
}
