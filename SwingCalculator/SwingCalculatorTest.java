import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SwingCalculatorTest {

    @Test
    public void testAddition() {
        SwingCalculator calculator = new SwingCalculator();
        double result = calculator.calculate(5.0, "+", 3.0);
        assertEquals(8.0, result, "Addition failed!");
    }

    @Test
    public void testSubtraction() {
        SwingCalculator calculator = new SwingCalculator();
        double result = calculator.calculate(10.0, "-", 2.0);
        assertEquals(8.0, result, "Subtraction failed!");
    }

    @Test
        public void testMultiplication() {
        SwingCalculator calculator = new SwingCalculator();
        double result = calculator.calculate(4.0, "*", 2.5);
        assertEquals(10.0, result, "Multiplication failed!");

        result = calculator.calculate(-3.0, "*", 2.0);
        assertEquals(-6.0, result, "Multiplication with negative number failed!");
    }

    @Test
        public void testDivision() {
        SwingCalculator calculator = new SwingCalculator();
        double result = calculator.calculate(12.0, "/", 3.0);
        assertEquals(4.0, result, "Division failed!");

        assertThrows(ArithmeticException.class, () -> calculator.calculate(10.0, "/", 0.0));
    }

    @Test
    public void testExponentiation() {
        SwingCalculator calculator = new SwingCalculator();
        double result = calculator.calculate(2.0, "^", 3.0);
        assertEquals(8.0, result, "Exponentiation failed!");

        result = calculator.calculate(3.0, "^", -2.0);
        assertEquals(1.0 / 9.0, result, "Exponentiation with negative exponent failed!");
    }

    @Test
    public void testEmptyInput() {
        SwingCalculator calculator = new SwingCalculator();
        assertThrows(NumberFormatException.class, () -> calculator.calculate(3.0, "+", Double.parseDouble("")));
        assertThrows(NumberFormatException.class, () -> calculator.calculate(Double.parseDouble(""), "+", 3.0));
    }
}