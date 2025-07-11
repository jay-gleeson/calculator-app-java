import java.util.Scanner;

public class Calculator {

    /*
     * This class represents a calcalator bare-bones.
     * Does not support decimal exponents like roots, unfortunately.
     * 
     * Will write in logic for modularity, using multiple classes.
     */

    public static double add(double a, double b) {
        double answer;
        answer = a + b;
        return answer;
    }

    public static double subtract(double a, double b) {
        double answer;
        answer = a - b;
        return answer;
    }

    public static double multiply(double a, double b) {
        double answer;
        answer = a * b;
        return answer;
    }

    public static double divide(double a, double b) {
        double answer;
        answer = a / b;
        return answer;
    }

    public static double power(double a, double b) {
        double answer;
        if (b % 1.0 != 0) { /* If exponent is a decimal, floor. */
            System.out.println("Unfortunately, roots are not supported!");
            System.out.println("Flooring exponent.");
            b = (int)b;
        }
            
        // Exponent logic, allowing negative exponents.
        boolean negative = false;  // Initialize negative exponent boolean.
        if (b < 0) { /* If exponent is less than zero, set negative exponent boolean to true. */
            b = -b;
            negative = true;
        }
        
        // Answer should start at one in case b is zero.
        answer = 1;
        for (int i = 0; i < b; i++) {
            answer *= a;  // Exponent logic, a is multiplied by itself b times.
        }
        
        // If b was negative initially, follow exponential logic.
        if (negative) {
            answer = 1 / answer;  // If b was negative, answer will be 1/a^|b|.
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {

        // Create input scanner object.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, welcome to Calculator!");

        // Take user's first number input.
        System.out.println("Please input your first number.");
        double a = scanner.nextDouble();  // Scans line for the first value.
        System.out.println("You entered value " + a + ".");
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
        System.out.println("You entered value " + b + ".");

        // Based on input operation, perform calculation.
        double answer = 0;
        switch (operation) {
            case "+":  // If addition, add a and b.
                answer = add(a, b);
                break;
            case "-":  // If subtraction, subtract a and b.
                answer = subtract(a, b);
                break;
            case "*":  // If multiplication, multiply a and b.
                answer = multiply(a, b);
                break;
            case "/":  // If division, divide a and b.
                answer = divide(a, b);
                break;
            case "^":  // If exponential, a is base and b is exponent.
                answer = power(a, b);
                break;
        }
        
        // Is answer is floorable without losing significant digits, floor.
        if (answer % 1.0 == 0) {
            answer = (int)answer;
        }

        // Output final answer to console.
        System.out.println("The answer is " + answer + ".");

        // Close input scanner.
        scanner.close();
    }
}
