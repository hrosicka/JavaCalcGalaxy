import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingBMICalculator extends JFrame implements ActionListener {

    private JTextField heightField;
    private JTextField weightField;
    private JLabel bmiLabel;
    private JLabel classificationLabel;
    private JButton calculateButton;

    public SwingBMICalculator() {
        setTitle("Kalkulačka BMI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Vytvoření polí pro zadání výšky a váhy
        heightField = new JTextField(10);
        weightField = new JTextField(10);

        // Vytvoření štítků pro zobrazení BMI a klasifikace
        bmiLabel = new JLabel("BMI:");
        classificationLabel = new JLabel();

        // Vytvoření tlačítka pro výpočet
        calculateButton = new JButton("Vypočítat");
        calculateButton.addActionListener(this);

        // Uspořádání komponent GUI
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Výška (cm):"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(heightField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Váha (kg):"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(weightField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(bmiLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(classificationLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(calculateButton, constraints);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());

            BMICalculator calculator = new BMICalculator();
            double bmi = calculator.calculateBMI(height, weight);
            String classification = calculator.classifyBMI(bmi);

            bmiLabel.setText("BMI: " + String.format("%.2f", bmi));
            classificationLabel.setText("Klasifikace: " + classification);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Neplatná výška nebo váha. Zadejte čísla.",
                    "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingBMICalculator();
            }
        });
    }
}