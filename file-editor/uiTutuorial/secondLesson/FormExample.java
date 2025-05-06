package uiTutuorial.secondLesson;

import javax.swing.*;
import java.awt.*;

public class FormExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Survey");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));

        JTextField nameField = new JTextField(20);

        JCheckBox knowsJava = new JCheckBox("Knows Java");
        JCheckBox knowsPython = new JCheckBox("Knows Python");

        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);

        JComboBox<String> countryBox = new JComboBox<>(new String[] {
                "Burundi", "Kenya", "Tanzania", "Uganda"
        });

        JTextArea bio = new JTextArea(4, 20);
        bio.setLineWrap(true);
        bio.setWrapStyleWord(true);

        JButton submit = new JButton("Submit");
        JLabel result = new JLabel();

        submit.addActionListener(e -> {
            String name = nameField.getText();
            String country = (String) countryBox.getSelectedItem();
            String lang = (knowsJava.isSelected() ? "Java " : "") +
                    (knowsPython.isSelected() ? "Python" : "");
            String genderSelected = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "None");

            result.setText("<html>Name: " + name +
                    "<br>Gender: " + genderSelected +
                    "<br>Languages: " + lang +
                    "<br>Country: " + country + "</html>");
        });

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Gender:"));
        panel.add(male);
        panel.add(female);
        panel.add(new JLabel("Languages:"));
        panel.add(knowsJava);
        panel.add(knowsPython);
        panel.add(new JLabel("Country:"));
        panel.add(countryBox);
        panel.add(new JLabel("Short Bio:"));
        panel.add(new JScrollPane(bio));
        panel.add(submit);
        panel.add(result);

        frame.add(panel);
        frame.setVisible(true);
    }
}
