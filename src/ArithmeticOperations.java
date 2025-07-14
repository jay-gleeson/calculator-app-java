public class ArithmeticOperations {

    /*
     * This class represents the interal logic for arithmetic operations.
     * It provides methods for addition, subtraction, multiplication, division,
     * and exponentiation.
     * It does not support decimal exponents like roots, unfortunately.
     * It is used by the Calculator class to perform calculations.
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
        if (b == 0) {  /* If divisor is zero, throw exception. */
            throw new ArithmeticException("Cannot divide by zero.");
        }
        answer = a / b;
        return answer;
    }

    public static double power(double a, double b) {
        double answer;
        if (b % 1.0 != 0) { /* If exponent is a decimal, throw exception. */
            throw new ArithmeticException("Cannot raise to a decimal power.");
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
}
