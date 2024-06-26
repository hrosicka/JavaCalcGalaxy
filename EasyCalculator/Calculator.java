public class Calculator {

    /**
     * A simple error class for division by zero exceptions.
     */
    public static class CalculatorError extends ArithmeticException {
        public CalculatorError(String message) {
            super(message);
        }
    }

    /**
     * This constructor initializes a Calculator object.
     */
    public Calculator() {
        // Intentionally left empty, as the constructor doesn't require any initialization.
    }

    /**
     * This method adds two numbers and returns the sum.
     *
     * @param a The first number to be added.
     * @param b The second number to be added.
     * @return The sum of a and b.
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * This method subtracts two numbers and returns the difference.
     *
     * @param a The first number from which the second number will be subtracted.
     * @param b The second number to be subtracted from the first number.
     * @return The difference of a and b.
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * This method multiplies two numbers and returns the product.
     *
     * @param a The first number to be multiplied.
     * @param b The second number to be multiplied.
     * @return The product of a and b.
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * This method divides two numbers and returns the quotient.
     *
     * @param a The dividend (number to be divided).
     * @param b The divisor (number by which to divide).
     * @return The quotient of a and b.
     * @throws CalculatorError If the divisor (b) is zero.
     */
    public double divide(double a, double b) throws CalculatorError {
        if (b == 0) {
            throw new CalculatorError("Division by zero is not allowed!");
        }
        return a / b;
    }
}