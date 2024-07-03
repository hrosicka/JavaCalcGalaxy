public class BMICalculator {

    public double calculateBMI(double weight, double height) {
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Výška a váha musí být kladná čísla.");
        }
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    public String classifyBMI(double bmi) {
        if (bmi < 18.5) {
            return "Podváha";
        } else if (bmi < 25) {
            return "Normální váha";
        } else if (bmi < 30) {
            return "Nadváha";
        } else {
            return "Obezita";
        }
    }
}