public class BMICalculator {

    /**
     * Threshold value used to classify BMI as underweight.
     */
    private static final double UNDERWEIGHT_THRESHOLD = 18.5;

    /**
     * Threshold value used to classify BMI as normal weight.
     */
    private static final double NORMAL_WEIGHT_THRESHOLD = 25;

    /**
     * Threshold value used to classify BMI as overweight.
     */
    private static final double OVERWEIGHT_THRESHOLD = 30;

    /**
     * Calculates the Body Mass Index (BMI) based on the provided weight and height.
     *
     * @param weight The weight in kilograms (kg).
     * @param height The height in centimeters (cm).
     * @return The calculated BMI value.
     * @throws IllegalArgumentException if either weight or height is not a positive number.
     */
    public double calculateBMI(double weight, double height) {
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Both height and weight must be positive numbers.");
        }
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    /**
     * Classifies a given BMI value into one of the following categories:
     *  - Underweight
     *  - Normal weight
     *  - Overweight
     *  - Obese
     *
     * @param bmi The Body Mass Index value.
     * @return The classification based on the provided BMI value.
     */
    public String classifyBMI(double bmi) {
        if (bmi < UNDERWEIGHT_THRESHOLD) {
            return "Underweight";
        } else if (bmi < NORMAL_WEIGHT_THRESHOLD) {
            return "Normal weight";
        } else if (bmi < OVERWEIGHT_THRESHOLD) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}