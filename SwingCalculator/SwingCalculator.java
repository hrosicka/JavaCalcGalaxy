import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

import javax.swing.*;

/**
 * A professional-grade Swing calculator application with enhanced features.
 *
 * @author [Hanka R.]
 */
public class SwingCalculator extends JFrame {

	private static final long serialVersionUID = 1L;

	// UI components
	private JSpinner number1Spinner;
	private JSpinner number2Spinner;
	private JComboBox<String> operatorComboBox;
	private JTextField resultField;
	private JButton calculateButton;
	
	/**
	 * Main entry point for the application.
	 *
	 * @param args Command-line arguments (unused)
	 */
	public static void main(String[] args) {
		// Set Nimbus look and feel
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SwingCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SwingCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SwingCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SwingCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingCalculator frame = new SwingCalculator();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	/* Creates a new instance of the SwingCalculator.
	/*/
	public SwingCalculator() {

		setTitle("Calculator");		// Set the frame title
		setSize(new Dimension(500, 280));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 280);

		// Create a main panel with BorderLayout for vertical layout
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		 // Create and add top and bottom panels
		mainPanel.add(createTopPanel(), BorderLayout.NORTH);
		mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);
	
		// Set the main panel as the content pane
		setContentPane(mainPanel);

	}

	/**
	/* Creates a JSpinner component for numeric input with a specified value range and step size.
	/*
	/* @param minValue The minimum allowed value
	/* @param initialValue The initial value displayed
	/* @param maxValue The maximum allowed value
	/* @param stepSize The increment for each spinner step
	/* @return The JSpinner component
	/*/
	private JSpinner getSpinner(double minValue, double initialValue, double maxValue, double stepSize) {
		// Create a SpinnerNumberModel using the provided values:
		SpinnerNumberModel model = new SpinnerNumberModel(initialValue, minValue, maxValue, stepSize);
	  
		// Create a JSpinner using the model:
		JSpinner operandSpinner = new JSpinner(model);
	  
		// Optionally customize the JSpinner's appearance (e.g., editor format):
		operandSpinner.setEditor(new JSpinner.NumberEditor(operandSpinner, "#,###0.000")); // Example format
	  
		return operandSpinner;
	  }

	/**
	/* Creates and initializes the top panel containing input fields.
	/*
	/* @return The top panel with UI components.
	/*/
	private JPanel createTopPanel() {
		// Create the first FlowLayout panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 60)); // Center-aligned with 5px spacing
		
		// Create spinners for numerical input with custom formatting
		number1Spinner = getSpinner(-10000.0, 0.0, 10000.0, 0.1);
		number2Spinner = getSpinner(-10000.0, 0.0, 10000.0, 0.1);
		
		// Create combo box for selecting mathematical operations
		operatorComboBox = new JComboBox<>();
		operatorComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"+", "-", "*", "/", "^"}));
		
		// Create result text field with right alignment and non-editable
		resultField = new JTextField("0.0", 10); // Set width for better display
		resultField.setEditable(false); // Make it non-editable
		resultField.setHorizontalAlignment(SwingConstants.RIGHT); // Right alignment

		// Add UI components to the top panel
		topPanel.add(number1Spinner);
		topPanel.add(operatorComboBox);
		topPanel.add(number2Spinner);
		topPanel.add(new JLabel("="));
		topPanel.add(resultField);

		// Add tooltips for user guidance
		number1Spinner.setToolTipText("Enter the first operand here.");
		number2Spinner.setToolTipText("Enter the second operand here.");
		operatorComboBox.setToolTipText("Select the mathematical operation.");
		resultField.setToolTipText("The calculated result will be displayed here.");

		return topPanel;
	}

	/**
	 * Creates and initializes the bottom panel containing the calculate button and a new clear button.
	 * 
	 * @return The bottom panel with the calculate and clear buttons.
	 */
	private JPanel createBottomPanel() {
		// Create the bottom panel with centered alignment and spacing
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
	  
		// Create and configure the calculate button
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(this::calculateButtonPressed); // Use method reference for cleaner syntax
		calculateButton.setPreferredSize(new Dimension(100, 40));
		calculateButton.setToolTipText("Click to perform the calculation.");
	  
		// Create and configure the clear button
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(this::clearButtonClicked); // Assuming a clearButtonClicked method exists
		clearButton.setPreferredSize(new Dimension(100, 40));
		clearButton.setToolTipText("Reset all inputs and result to default values."); // Add tooltip text

		// Add buttons to the panel
		bottomPanel.add(calculateButton);
		bottomPanel.add(clearButton);
	  
		return bottomPanel;
	}

	/**
	 * Performs the calculation based on the provided operands and operation.
	 *
	 * @param number1 The first operand
	 * @param operation The mathematical operation (+, -, *, /, ^)
	 * @param number2 The second operand
	 * @return The calculated result
	 * @throws ArithmeticException If an invalid operation or division by zero occurs
	 */
	public double calculate(double number1, String operation, double number2) throws ArithmeticException {
		switch (operation) {
			case "+":
				return add(number1, number2);
			case "-":
				return subtract(number1, number2);
			case "*":
				return multiply(number1, number2);
			case "/":
				return divide(number1, number2);
			case "^":
				return Math.pow(number1, number2);
			default:
				throw new IllegalArgumentException("Unsupported operation: " + operation);
		}
	}

	/**
	 * Performs the addition operation.
	 *
	 * @param number1 The first operand
	 * @param number2 The second operand
	 * @return The sum of the operands
	 */
	private double add(double number1, double number2) {
		return number1 + number2;
	}

	/**
	 * Performs the subtraction operation.
	 *
	 * @param number1 The first operand
	 * @param number2 The second operand
	 * @return The difference of the operands
	 */
	private double subtract(double number1, double number2) {
		return number1 - number2;
	}

	/**
	 * Performs the multiplication operation.
	 *
	 * @param number1 The first operand
	 * @param number2 The second operand
	 * @return The product of the operands
	 */
	private double multiply(double number1, double number2) {
		return number1 * number2;
	}
	
	/**
	 * Performs the division operation.
	 *
	 * @param number1 The first operand (numerator)
	 * @param number2 The second operand (denominator)
	 * @return The quotient of the operands
	 * @throws ArithmeticException If the denominator is zero
	 */
	private double divide(double number1, double number2) throws ArithmeticException {
		if (number2 == 0) {
			throw new ArithmeticException("Division by zero is not allowed!");
		}
		return number1 / number2;
	}

	/**
	 * Resets the calculator's UI elements to their default values.
	 * 
	 * This method is called when the "Clear" button is clicked. It performs the following actions:
	 *  - Sets the value of the number1Spinner to 0.0.
	 *  - Sets the value of the number2Spinner to 0.0.
	 *  - Selects the first item (usually "+") in the operatorComboBox.
	 *  - Sets the text of the resultField to "0.0".
	 */
	private void clearButtonClicked(ActionEvent evt) {
		number1Spinner.setValue(0.0); // Set default value for number1
		number2Spinner.setValue(0.0); // Set default value for number2
		operatorComboBox.setSelectedIndex(0); // Select the first item (usually "+")
		resultField.setText("0.0"); // Set default result text
	}

	/**
	 * Performs the calculation based on the user input and displays the result.
	 * 
	 * This method is called when the "Calculate" button is clicked. It performs the following actions:
	 *  - Gets the selected operation from the operatorComboBox.
	 *  - Retrieves the values from the number1Spinner and number2Spinner as doubles.
	 *  - Checks for division by zero if the operation is division.
	 *      - If division by zero, displays an error message and optionally sets a default result.
	 *      - Otherwise, calls the `calculate` method to perform the actual calculation.
	 *  - Formats the result using a DecimalFormat and sets the text of the resultField.
	 */
	private void calculateButtonPressed(ActionEvent evt) {
		String operation = String.valueOf(operatorComboBox.getSelectedItem());
		double number1 = (Double) number1Spinner.getValue();
		double number2 = (Double) number2Spinner.getValue();
	  
		double result;
		if (operation.equals("/") && number2 == 0) {
			JOptionPane.showMessageDialog(null, "Division by zero is not allowed!");
			result = 0; // Set a default value (optional)
		} else {
			result = calculate(number1, operation, number2);
		}
	  
		DecimalFormat df = new DecimalFormat("#.###");
		resultField.setText(df.format(result));
	}
}