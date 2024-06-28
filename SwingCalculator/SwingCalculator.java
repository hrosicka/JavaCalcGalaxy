import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

/**
 * A professional-grade Swing calculator application with enhanced features.
 *
 * @author [Hanka R.]
 */
public class SwingCalculator extends JFrame {

	private static final long serialVersionUID = 1L;

	private JSpinner number1Spinner;
	private JSpinner number2Spinner;
	private JComboBox<String> operatorComboBox;
	private JTextField resultField;
	// private final JButton calculateButton;
	
	/**
	 * Main entry point for the application.
	 *
	 * @param args Command-line arguments (unused)
	 */
	public static void main(String[] args) {
		
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
	 * Creates a new instance of the AdvancedSwingCalculator.
	 */
	public SwingCalculator() {

		setTitle("Calculator");		// Set the frame title
		setSize(new Dimension(500, 280));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 280);


		// Create a main panel to hold the two FlowLayouts
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout()); // Use BorderLayout for vertical arrangement

		// Add the panels to the main panel
		mainPanel.add(createTopPanel(), BorderLayout.NORTH);
		mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);
	
		// Set the main panel as the content pane
		setContentPane(mainPanel);

	}

	/**
	 * Creates and returns a JSpinner component for numeric input.
	 *
	 * @return The JSpinner component.
	 */
	private JSpinner getSpinner() {
		// Set reasonable default values for the spinner:
		double minValue = -10000.0;  // Adjusted minimum value
		double initialValue = 0.0;   // Adjusted initial value
		double maxValue = 10000.0;   // Adjusted maximum value
		double stepSize = 0.1;      // Adjusted step size

		// Consider customizing these values based on your specific requirements.

		// Create a SpinnerNumberModel using the defined values:
		SpinnerNumberModel model = new SpinnerNumberModel(initialValue, minValue, maxValue, stepSize);

		// Create a JSpinner using the model:
		JSpinner operandSpinner = new JSpinner(model);

		// Optionally customize the JSpinner's appearance (e.g., editor format):
		operandSpinner.setEditor(new JSpinner.NumberEditor(operandSpinner, "#,###0.000")); // Example format

		return operandSpinner;
	}

	private JPanel createTopPanel() {
		// Create the first FlowLayout panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 60)); // Center-aligned with 5px spacing
		
		number1Spinner = getSpinner();
		number2Spinner = getSpinner();
		operatorComboBox = new JComboBox<>();
		operatorComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"+", "-", "*", "/", "^"}));
		
		resultField = new JTextField("0.0", 10); // Set width for better display
		resultField.setEditable(false); // Make it non-editable
		resultField.setHorizontalAlignment(SwingConstants.RIGHT); // Right alignment

		// Add components to the grid
		topPanel.add(number1Spinner);
		topPanel.add(operatorComboBox);
		topPanel.add(number2Spinner);
		topPanel.add(new JLabel("="));
		topPanel.add(resultField);

		// tootips
		number1Spinner.setToolTipText("Enter the first operand here.");
		number2Spinner.setToolTipText("Enter the second operand here.");
		operatorComboBox.setToolTipText("Select the mathematical operation.");
		resultField.setToolTipText("The calculated result will be displayed here.");

		return topPanel;
	}

	private JPanel createBottomPanel() {
		// Create the first FlowLayout panel
		// Create the second FlowLayout panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));

		JButton calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				calculateButtonPressed(evt);
			}

			private void calculateButtonPressed(ActionEvent evt) {
				
				String operation = String.valueOf(operatorComboBox.getSelectedItem());
				double number1 = (Double)number1Spinner.getValue();
				double number2 = (Double)number2Spinner.getValue();
				double result = 0.0;

				// Use the Calculator object to perform the chosen operation
				// and display the result with clean formatting.
				DecimalFormat df = new DecimalFormat("#.###");
				
				if (operation.equals("+"))
					result = number1 + number2;
				else if (operation.equals("-"))
					result = number1 - number2;
				else if (operation.equals("*"))
					result = number1 * number2;
				else if (operation.equals("/"))
				{
					if (number2 != 0)
						result = number1 / number2;
					else
					{
						result = 0;
						JOptionPane.showMessageDialog(null, "Division by zero is not allowed!");
					}
				}
				else if (operation.equals("^"))
					result = Math.pow(number1, number2);

				resultField.setText(String.valueOf(df.format(result)));
				
			}
		});
		// Add the calculate button to the bottom panel
		calculateButton.setPreferredSize(new Dimension(100, 40));
		bottomPanel.add(calculateButton);

		// tootip
		calculateButton.setToolTipText("Click to perform the calculation.");

		return bottomPanel;
	}

	private double calculate(double number1, String operation, double number2) {
		double result = 0.0;
		switch (operation) {
		  case "+":
			result = number1 + number2;
			break;
		  case "-":
			result = number1 - number2;
			break;
		  // ... similar cases for other operations
		}
		return result;
	  }
}