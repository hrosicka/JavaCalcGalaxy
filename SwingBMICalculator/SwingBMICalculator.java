import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Swing application that calculates Body Mass Index (BMI) and displays the classification based on the calculated value.
 * This application provides a user-friendly interface with informative tooltips for each classification.
 *  * @author [Hanka Robovska]
 */
public class SwingBMICalculator extends JFrame implements ActionListener {

    // Define application-wide constants (improve readability and maintainability)
    private static final int TEXTFIELD_WIDTH = 150;
    private static final int TEXTFIELD_HEIGHT = 30;

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

    /**
     * Constructor to initialize the application window with default settings and layout.
     * Sets the title, default close operation, layout manager, and creates UI components.
     */
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

        // Customize button and text field appearance using ButtonPainter
        UIManager.getLookAndFeelDefaults().put("Button[Enabled].backgroundPainter", new ButtonPainter(BUTTON_COLOR, BACKGROUND_COLOR));
        UIManager.getLookAndFeelDefaults().put("Button[Focused].backgroundPainter", new ButtonPainter(BUTTON_COLOR, BACKGROUND_COLOR));
        UIManager.getLookAndFeelDefaults().put("TextField[Enabled].backgroundPainter", new ButtonPainter(TEXTFIELD_COLOR, TEXTFIELD_COLOR));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(TEXTFIELD_COLOR, 0));
        
        setTitle("BMI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Set the icon image (using the ImageIcon approach)
		ImageIcon icon = new ImageIcon(SwingCalculator.class.getResource("/resources/calc_icon.png"));
		setIconImage(icon.getImage());

        // Set the desired fixed size for the window
        setSize(new Dimension(500, 400));
        setResizable(false);

        // Create UI components with clear descriptions
        heightField = new JTextField(15);
        heightField.setToolTipText("Enter your height in centimeters (cm)."); // Set informative tooltip
        weightField = new JTextField(15);
        weightField.setToolTipText("Enter your weight in kilograms (kg).");
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

    /**
     * Handles the "Calculate" button click event.
     * Parses weight and height from text fields, validates them, calculates BMI and classification using the BMICalculator object,
     * sets the tooltip text based on the classification, and updates the BMI and classification labels.
     *
     * @param e The ActionEvent object generated by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Get height and weight values from text fields
            double height = getInputValue(heightField);
            double weight = getInputValue(weightField);

            // Validate input values
            if (height <= 0 || weight <= 0) {
                JOptionPane.showMessageDialog(this, "Height and weight must be positive values.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;  // Exit the method if values are not positive
            }

            // Calculate the BMI and classification
            double bmi = calculator.calculateBMI(weight, height);
            String classification = calculator.classifyBMI(bmi);

            // Update BMI and classification labels
            updateLabels(bmi, classification);

            // Set tooltip based on classification
            setTooltipBasedOnClassification(classification);

        } catch (NumberFormatException ex) {
            // Handle invalid input (non-numeric values)
            JOptionPane.showMessageDialog(this, "Invalid height or weight. Please enter numbers.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Parses the text from the given JTextField into a double value.
     *
     * @param textField The JTextField containing the text to be parsed.
     * @return The parsed double value.
     * @throws NumberFormatException If the text cannot be parsed as a double.
     */
    private double getInputValue(JTextField textField) {
        return Double.parseDouble(textField.getText());
    }

    /**
     * Updates the BMI and classification labels with the given values.
     *
     * @param bmi The calculated BMI value.
     * @param classification The BMI classification.
     */
    private void updateLabels(double bmi, String classification) {
        // Update the BMI and classification labels
        bmiLabel.setText("BMI: " + String.format("%.2f", bmi));
        classificationLabel.setText("Classification: " + classification);
    }

    /**
     * Helper method to add a JLabel component to the application window using GridBagLayout.
     * Sets the constraints for the label, including horizontal fill, insets, and grid position (gridx, gridy).
     *
     * @param label The JLabel component to be added.
     * @param gridX The x-coordinate position of the label in the grid layout.
     * @param gridY The y-coordinate position of the label in the grid layout.
     */
    private void addComponent(JLabel label, int gridX, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = COMPONENT_INSETS;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        add(label, constraints);
    }
    
    /**
     * Helper method to add a JTextField component to the application window using GridBagLayout.
     * Sets the constraints for the text field, including horizontal fill, insets, grid position (gridx, gridy),
     * preferred size, horizontal alignment, margins, and custom border.
     *
     * @param textField The JTextField component to be added.
     * @param gridX The x-coordinate position of the text field in the grid layout.
     * @param gridY The y-coordinate position of the text field in the grid layout.
     */
    private void addComponent(JTextField textField, int gridX, int gridY) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = COMPONENT_INSETS;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        textField.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setMargin(TEXTFIELD_INSETS);
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10), textField.getBorder()));
        add(textField, constraints);
    }

    /**
     * Helper method to add a JButton component to the application window using GridBagLayout.
     * Sets the constraints for the button, including horizontal fill, insets, grid position (gridx, gridy),
     * and the number of columns to span (gridwidth).
     *
     * @param button The JButton component to be added.
     * @param gridX The x-coordinate position of the button in the grid layout.
     * @param gridY The y-coordinate position of the button in the grid layout.
     * @param gridwidth The number of columns the button should span in the grid layout.
     */
    private void addComponent(JButton button, int gridX, int gridY, int gridwidth) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = COMPONENT_INSETS;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.gridwidth = 2;
        add(button, constraints);
    }

    /**
     * Sets the tooltip text for the classification label based on the provided classification value.
     * Uses a switch statement to assign appropriate tooltip text for different BMI classifications.
     *
     * @param classification The BMI classification string (e.g., "Underweight", "Normal weight", etc.)
     */
    private void setTooltipBasedOnClassification(String classification) {
        switch (classification) {
            case "Underweight":
                classificationLabel.setToolTipText("BMI below the healthy weight range.");
                break;
            case "Normal weight":
                classificationLabel.setToolTipText("BMI within the healthy weight range.");
                break;
            case "Overweight":
                classificationLabel.setToolTipText("BMI above the healthy weight range. Consider lifestyle changes to reduce BMI.");
                break;
            case "Obese":
                classificationLabel.setToolTipText("Elevated BMI that may increase health risks.");
                break;
            default:
                classificationLabel.setToolTipText("Unclassified BMI.");
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingBMICalculator().setVisible(true));
    }
}