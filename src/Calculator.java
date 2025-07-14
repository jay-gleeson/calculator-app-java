import java.util.Scanner;

public class Calculator {

    /*
     * This class represents the GUI for the calculator.
     * It takes user input for two numbers and an operation,
     * performs the operation, and outputs the result.
     * Does not support decimal exponents like roots, unfortunately.
     */

    public static void main(String[] args) throws Exception {

        // Create input scanner object.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, welcome to Calculator!");

        // Take user's first number input.
        System.out.println("Please input your first number.");
        double a = scanner.nextDouble();  // Scans line for the first value.
        if (a % 1.0 == 0) {
            System.out.println("You entered " + ((int)a) + ".");
        } else {
            System.out.println("You entered " + a + ".");
        }
        scanner.nextLine();  // Clear input line.

        // Take user's operation input.
        String operation = "";
        do {
            System.out.println("Please input your operation (+, -, *, /, ^). ");
            operation = scanner.nextLine();  // Scans line for the operation.

            // If input is not a valid operation, tell user and retry.
            if (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/") && !operation.equals("^")) {
                System.out.println("Invalid operation. Please pick out of the following: +, -, *, /, ^. ");
            }
        } while (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/") && !operation.equals("^"));
        System.out.println("You entered operation " + operation + ".");

        // Take user's second number input.
        System.out.println("Please input your second number.");
        double b = scanner.nextDouble();  // Scans line for the second value.
        if (b % 1.0 == 0) {
            System.out.println("You entered " + ((int)b) + ".");
        } else {
            System.out.println("You entered " + b + ".");
        }

        // Based on input operation, perform calculation.
        double answer = 0;
        switch (operation) {
            case "+":  // If addition, add a and b.
                answer = ArithmeticOperations.add(a, b);
                break;
            case "-":  // If subtraction, subtract a and b.
                answer = ArithmeticOperations.subtract(a, b);
                break;
            case "*":  // If multiplication, multiply a and b.
                answer = ArithmeticOperations.multiply(a, b);
                break;
            case "/":  // If division, divide a and b.
                answer = ArithmeticOperations.divide(a, b);
                break;
            case "^":  // If exponential, a is base and b is exponent.
                answer = ArithmeticOperations.power(a, b);
                break;
        }
        
        // Is answer is floorable without losing significant digits, floor. Then print answer.
        if (answer % 1.0 == 0) {
            System.out.println("The answer is " + ((int)answer) + ".");
        } else {
            System.out.println("The answer is " + answer + ".");
        }

        // Close input scanner.
        scanner.close();
    }
}
