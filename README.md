# JavaCalcGalaxy - Learn Java Programming with Simple Calculators!

Looking for a fun and practical way to learn Java programming? This repository is for you! It contains a collection of simple Java calculators designed to help you grasp the fundamentals of Java programming. Experiment with the code, try out different operations and functions, and learn how to create your own simple programs.

## [**Basic Command-Line Calculator with Error Handling**](https://github.com/hrosicka/JavaCalcGalaxy/tree/master/StarterCalculator)

- This code implements a simple calculator application that operates through the command line.
- It offers basic functionalities for addition, subtraction, multiplication, and division.

### Key Features:

- **Text-based interface:** User interacts with the calculator through standard input/output streams.
- **Robust input validation:** Ensures user enters valid numbers (float) using a do-while loop and exception handling (InputMismatchException).
- **Clear prompts and messages:** Guides the user with informative messages for input and potential errors.
- **Error handling:** Prevents division by zero errors with a informative error message (ArithmeticException).
- **Formatted output:** Displays results using DecimalFormat for readability, rounding to 3 decimal places.
- **Resource management:** Explicitly closes the Scanner object to release resources.
  
Overall, this code demonstrates a well-structured and functional console calculator with a focus on user experience through clear prompts and robust error handling.
This code provides a solid foundation for building more advanced calculator functionalities in the future.

## [**EasyCalculator: Interactive Command-Line Calculator with Custom Error Handling**](https://github.com/hrosicka/JavaCalcGalaxy/tree/master/EasyCalculator)

- This code implements a user-friendly Command-Line Calculator for basic arithmetic operations.
- It offers a clear and interactive experience through its text-based interface.

### Key Features:

- **Modular design:** Utilizes a separate Calculator class to encapsulate calculation logic, promoting code reusability.
- **Custom error handling:** Defines a CalculatorError class specifically for division by zero exceptions, providing informative error messages.
- **User-friendly menu:** Guides the user with a clear menu displaying available operations and exit option.
- **Robust input validation:** Ensures user enters valid numbers (double) using a do-while loop and exception handling (InputMismatchException).
- **Formatted output:** Displays results using DecimalFormat for readability, rounding to 3 decimal places.
- **Resource management:** Explicitly closes the Scanner object to release resources.
  
Overall, this code demonstrates a well-structured and interactive console calculator with a focus on user experience through clear prompts, robust error handling, and a custom error class.

This code builds upon the previous example by introducing a modular design with a dedicated Calculator class and a custom error class for division by zero, showcasing more advanced object-oriented programming concepts.


## [**Professional-Grade Swing Calculator with User-Friendly Interface**](https://github.com/hrosicka/JavaCalcGalaxy/tree/master/SwingCalculator)

- This code implements a feature-rich calculator application using Java's Swing library. 
- It offers a well-designed graphical user interface (GUI) for a smooth user experience.

### Key Features:

- **User-friendly input:** Utilizes JSpinners for convenient numeric input with customizable formatting (e.g., supporting decimals).
- **Operator selection:** Provides a JComboBox for selecting various mathematical operations, including addition, subtraction, multiplication, division, and exponentiation.
- **Clear display:** Displays the calculated result in a dedicated JTextField with right-alignment for better readability.
- **Error handling:** Prevents division by zero errors with a informative message dialog.
- **Detailed tooltips:** Offers informative tooltips for each component, guiding users on their functionalities.
- **Organized layout:** Employs FlowLayout and BorderLayout for a clean and intuitive layout.
- **Customizable appearance:** Allows for easy modification of visual elements like button size and text field formatting.
  
Overall, this code demonstrates a well-structured and functional Swing calculator application with a focus on user experience and error handling.
