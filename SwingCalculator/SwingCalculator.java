import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingCalculator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JSpinner number1Spinner;
    private final JSpinner number2Spinner;
    private final JComboBox<String> operatorComboBox;
    private final JLabel resultLabel;

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
		setSize(new Dimension(100, 50));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
        number1Spinner = getSpinner();
		number2Spinner = getSpinner();
		operatorComboBox = new JComboBox<>();
        operatorComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"+", "-", "*", "/", "^"}));
		
		JLabel lblNewLabel1 = new JLabel("=");
		
		resultLabel = new JLabel("0");

		contentPane.setLayout(new MigLayout("", "[97px][97px][97px][8px][46px][77px]", "[251px][]"));
		contentPane.add(number1Spinner, "cell 0 0,growx,aligny center");
		contentPane.add(operatorComboBox, "cell 1 0,growx,aligny center");
		contentPane.add(number2Spinner, "flowy,cell 2 0,growx,aligny center");
		contentPane.add(lblNewLabel1, "cell 3 0,alignx center,aligny center");
		contentPane.add(resultLabel, "cell 4 0,alignx left,aligny center");
		
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

		        // výpočet
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
		                        JOptionPane.showMessageDialog(null, "Nulou nelze dělit");
		                }
		        }
		        else if (operation.equals("^"))
		        		result = Math.pow(number1, number2);
		        
				resultLabel.setText(String.valueOf(result));
				
			}
		});
		contentPane.add(btnCalculate, "cell 2 1,alignx left,aligny center");
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
		operandSpinner.setEditor(new JSpinner.NumberEditor(operandSpinner, "#,##0.00")); // Example format

		return operandSpinner;
	}
}