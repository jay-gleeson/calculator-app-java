/**
 * This class represents the internal logic for arithmetic operations.
 * It provides methods for addition, subtraction, multiplication, division,
 * and exponentiation. It does not support decimal exponents like roots.
 * It is used by the Calculator class to perform calculations.
 */

public class ArithmeticOperations {

    /**
     * Instance variable to store the answer of the operation.
     */
    private double answer;

    /**
     * Adds two numbers.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the sum of a and b
     */
    public double add(double a, double b) {
        answer = a + b;
        return answer;
    }

    /**
     * Subtracts the second number from the first.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the result of a minus b
     */
    public double subtract(double a, double b) {
        answer = a - b;
        return answer;
    }

    /**
     * Multiplies two numbers.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the product of a and b
     */
    public double multiply(double a, double b) {
        answer = a * b;
        return answer;
    }

    /**
     * Divides the first number by the second.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the result of a divided by b
     * @throws ArithmeticException if b is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        answer = a / b;
        return answer;
    }

    /**
     * Raises a number to the power of another number.
     * Only supports integer exponents.
     *
     * @param a the base
     * @param b the exponent
     * @return the result of a raised to the power of b
     * @throws ArithmeticException if b is not an integer
     */
    public double power(double a, double b) {
        if (b % 1.0 != 0) {
            throw new ArithmeticException("Cannot raise to a decimal power.");
        }

        boolean negative = false;
        if (b < 0) {
            b = -b;
            negative = true;
        }

        answer = 1;
        for (int i = 0; i < b; i++) {
            answer *= a;
        }

        if (negative) {
            answer = 1 / answer;
        }
        return answer;
    }
}
