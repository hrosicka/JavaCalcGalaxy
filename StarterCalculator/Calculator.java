import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    /**
     * Main entry point for the calculator application.
     *
     * @param args Unused command-line arguments.
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {

        // Create a Scanner object to interact with the user for input.
        // Scanner is configured with the system input stream and a character
        // encoding of "Windows-1250" to handle potential regional character sets.
        Scanner sc = new Scanner(System.in, "Windows-1250");

        // Display a welcome message to the user.
        System.out.println("Calculator");

        // Declare variables to store the operands (numbers) entered by the user.
        // Using primitive float data type for space efficiency and suitability for most calculations.
        float firstOperand = 0.0f;
        float secondOperand = 0.0f;

        // Variables to track the validity of user input for operands.
        boolean isFirstOperandValid = false;
        boolean isSecondOperandValid = false;

        // Input validation loop for the first operand.
        // Employs a do-while loop to ensure at least one iteration for input.
        do {
            try {
                // Prompt the user to enter the first operand.
                System.out.println("Enter the first number:");

                // Read the first operand as a float using Scanner's nextFloat() method.
                firstOperand = sc.nextFloat();

                // Mark the input as valid if no exceptions occur.
                isFirstOperandValid = true;
            } catch (InputMismatchException e) {
                // Handle invalid input (non-numeric characters).
                System.err.println("Invalid number entered. Please try again.");

                // Consume the remaining junk characters in the input stream (optional).
                sc.next();
            }
        } while (!isFirstOperandValid);

        // Similar input validation loop for the second operand.
        do {
            try {
                System.out.println("Enter the second number:");
                secondOperand = sc.nextFloat();
                isSecondOperandValid = true;
            } catch (InputMismatchException e) {
                System.err.println("Invalid number entered. Please try again.");
                sc.next();
            }
        } while (!isSecondOperandValid);

        // Create a DecimalFormat object to format results with 3 decimal places.
        DecimalFormat df = new DecimalFormat("#.###");

        // Perform basic arithmetic operations on the validated operands.
        float sum = firstOperand + secondOperand;
        float difference = firstOperand - secondOperand;
        float product = firstOperand * secondOperand;

        // Display the calculated results in a user-friendly format, rounded to 3 decimal places.
        System.out.println(firstOperand + " + " + secondOperand + " = " + df.format(sum));
        System.out.println(firstOperand + " - " + secondOperand + " = " + df.format(difference));
        System.out.println(firstOperand + " * " + secondOperand + " = " + df.format(product));

        // Division operation with robust error handling for division by zero.
        try {
            float quotient = firstOperand / secondOperand;

            // Explicitly check for "isInfinite" to catch potential division by zero.
            if (Float.isInfinite(quotient)) {
                throw new ArithmeticException("Division by zero is not allowed!");
            }

            System.out.println(firstOperand + " / " + secondOperand + " = " + df.format(quotient));
        } catch (ArithmeticException e) {
            // Handle division by zero exception with a clear error message.
            System.err.println("Error: " + e.getMessage());
        }

        // Close the Scanner object to release resources.
        sc.close();
    }
}
