import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Swing application that calculates Body Mass Index (BMI)
public class SwingBMICalculator extends JFrame implements ActionListener {

    // Define colors used in the application
    private static final Color BACKGROUND_COLOR = new Color(240, 244, 249);
    private static final Color BUTTON_COLOR = new Color(198, 140, 226);
    private static final Color TEXTFIELD_COLOR = Color.WHITE;

    // Define insets (padding) for components
    private static final Insets COMPONENT_INSETS = new Insets(10, 20, 10, 20);
    private static final Insets TEXTFIELD_INSETS = new Insets(2, 5, 2, 5);

    // Text fields for user input (height and weight)
    private JTextField heightField;
    private JTextField weightField;

    // Labels to display BMI and classification
    private JLabel bmiLabel;
    private JLabel classificationLabel;

    // Button to trigger BMI calculation
    private JButton calculateButton;

    // Instance of a separate BMI calculator class
    BMICalculator calculator = new BMICalculator();

    // Constructor to initialize the application window
    public SwingBMICalculator() {

        // Set Nimbus Look and Feel (if available)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Log or display a user-friendly message if Nimbus is not available
            System.err.println("Failed to set Nimbus Look and Feel: " + e.getMessage());
        }
        
        getContentPane().setBackground(BACKGROUND_COLOR);

        UIManager.getLookAndFeelDefaults().put("Button[Enabled].backgroundPainter", new ButtonPainter(BUTTON_COLOR, BACKGROUND_COLOR));
        UIManager.getLookAndFeelDefaults().put("Button[Focused].backgroundPainter", new ButtonPainter(BUTTON_COLOR, BACKGROUND_COLOR));
        UIManager.getLookAndFeelDefaults().put("TextField[Enabled].backgroundPainter", new ButtonPainter(TEXTFIELD_COLOR, TEXTFIELD_COLOR));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(TEXTFIELD_COLOR, 0));
        
        setTitle("BMI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Create UI components
        heightField = new JTextField(15);
        weightField = new JTextField(15);
        bmiLabel = new JLabel("BMI:");
        classificationLabel = new JLabel();
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        // Add components to the layout with consistent formatting
        addComponent(new JLabel("Height (cm):"), 0, 0);
        addComponent(heightField, 1, 0);

        addComponent(new JLabel("Weight (kg):"), 0, 1);
        addComponent(weightField, 1, 1);

        addComponent(bmiLabel, 0, 2);

        addComponent(classificationLabel, 1, 2);

        addComponent(calculateButton, 0, 3, 2); // Spans two columns

        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);

        pack();
    }

    // Handles the "Calculate" button click event
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Parse the weight and height values from the text fields
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());

            // Calculate the BMI and classification
            double bmi = calculator.calculateBMI(weight, height);
            String classification = calculator.classifyBMI(bmi);

            // Update the BMI and classification labels
            bmiLabel.setText("BMI: " + String.format("%.2f", bmi));
            classificationLabel.setText("Classification: " + classification);
        } catch (NumberFormatException ex) {
            // Handle invalid input (non-numeric values)
            JOptionPane.showMessageDialog(this, "Invalid height or weight. Please enter numbers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to add components with basic GridBagLayout configuration (JLabel)
    private void addComponent(JLabel label, int gridX, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = COMPONENT_INSETS;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        add(label, constraints);
    }
    
    // Helper method to add components with basic GridBagLayout configuration and specific styling (JTextField)
    private void addComponent(JTextField textField, int gridX, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = COMPONENT_INSETS;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        textField.setPreferredSize(new Dimension(150, 30));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setMargin(TEXTFIELD_INSETS);
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10), textField.getBorder()));
        add(textField, constraints);
    }

    // Helper method to add components with basic GridBagLayout configuration and spanning multiple columns (JButton)
    private void addComponent(JButton button, int gridX, int gridY, int gridwidth) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = COMPONENT_INSETS;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = 2;
        add(button, constraints);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingBMICalculator().setVisible(true));
    }
}