import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingCalculator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JSpinner number1Spinner;
    private final JSpinner number2Spinner;
    private final JComboBox<String> operatorComboBox;
    private final JTextField resultField;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public SwingCalculator() {
		setTitle("Calculator");
		setSize(new Dimension(500, 280));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 280);


		// Create a main panel to hold the two FlowLayouts
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout()); // Use BorderLayout for vertical arrangement
	
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

		// Create the second FlowLayout panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				calculateButtonPressed(evt);
				

			}

			private void calculateButtonPressed(ActionEvent evt) {
				
				String operation = String.valueOf(operatorComboBox.getSelectedItem());
				double number1 = (Double)number1Spinner.getValue();
				double number2 = (Double)number2Spinner.getValue();
				double result = 0;
				
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

				resultField.setText(String.valueOf(result));
				
			}
		});
		// Add the calculate button to the bottom panel
		btnCalculate.setPreferredSize(new Dimension(100, 40));
		bottomPanel.add(btnCalculate);

		// Add the panels to the main panel
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
	
		// Set the main panel as the content pane
		setContentPane(mainPanel);

		// tootips
		number1Spinner.setToolTipText("Enter the first operand here.");
		number2Spinner.setToolTipText("Enter the second operand here.");
		operatorComboBox.setToolTipText("Select the mathematical operation.");
		resultField.setToolTipText("The calculated result will be displayed here.");
		btnCalculate.setToolTipText("Click to perform the calculation.");
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
}