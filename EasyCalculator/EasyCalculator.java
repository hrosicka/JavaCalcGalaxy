import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public class EasyCalculator {

    public static void main(String[] args) {

        // Create a Scanner object for user input.
        Scanner sc = new Scanner(System.in, "Windows-1250");

        // Welcome message and initialize the calculator object
        System.out.println("Welcome to the Easy Calculator!");
        Calculator calculator = new Calculator();

        // Main loop to handle user interaction.
        while (true) {
            // Display the menu options.
            System.out.println("\nMenu:");
            System.out.println("1. Add (+) ");
            System.out.println("2. Subtract (-)");
            System.out.println("3. Multiply (*)");
            System.out.println("4. Divide (/)");
            System.out.println("5. Exit (q)");

            // Get user input for the desired operation.
            int operationChoice;
            do {
                System.out.print("Enter your choice (1-5): ");
                try {
                    operationChoice = sc.nextInt();
                    break; // Exit the loop if input is valid (integer)
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a number (1-5).");
                    sc.next(); // Consume the invalid input
                }
            } while (true); // Loop until valid input is entered

            // Perform the selected operation using the Calculator object.
            if (operationChoice == 5) {
                System.out.println("Exiting the calculator.");
                break;
            }

            // Get operands from the user.
            double firstOperand, secondOperand;
            do {
                System.out.print("Enter the first number: ");
                try {
                    firstOperand = sc.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a number.");
                    sc.next(); // Consume the invalid input
                }
            } while (true);

            do {
                System.out.print("Enter the second number: ");
                try {
                    secondOperand = sc.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a number.");
                    sc.next(); // Consume the invalid input
                }
            } while (true);

            // Use the Calculator object to perform the chosen operation
            // and display the result with clean formatting.
            DecimalFormat df = new DecimalFormat("#.###");
            double result;
            try {
                switch (operationChoice) {
                    case 1:
                        result = calculator.add(firstOperand, secondOperand);
                        System.out.println(firstOperand + " + " + secondOperand + " = " + df.format(result));
                        break;
                    case 2:
                        result = calculator.subtract(firstOperand, secondOperand);
                        System.out.println(firstOperand + " - " + secondOperand + " = " + df.format(result));
                        break;
                    case 3:
                        result = calculator.multiply(firstOperand, secondOperand);
                        System.out.println(firstOperand + " * " + secondOperand + " = " + df.format(result));
                        break;
                    case 4:
                        result = calculator.divide(firstOperand, secondOperand);
                        System.out.println(firstOperand + " / " + secondOperand + " = " + df.format(result));
                        break;
                    default:
                        System.err.println("Invalid choice.");
                }
            } catch (Calculator.CalculatorError e) {
                System.err.println(e.getMessage());
            }
        }

        // Close the Scanner object.
        sc.close();
    }
}