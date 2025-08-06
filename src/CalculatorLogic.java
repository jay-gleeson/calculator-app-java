public class CalculatorLogic {
    double num1, num2, result = 0; // Variables to hold numbers and result.
    char operator = ' '; // Variable to hold the operator.

    /**
     * Sets the first number for the calculation.
     *
     * @param num1 the first number
     */
    public void setNum1(double num1) {
        this.num1 = num1;
    }

    /**
     * Sets the second number for the calculation.
     *
     * @param num2 the second number
     */
    public void setNum2(double num2) {
        this.num2 = num2;
    }

    /**
     * Sets the operator for the calculation.
     *
     * @param operator the operator character
     */
    public void setOperator(char operator) {
        this.operator = operator;
    }

    /**
     * Performs the calculation based on the operator.
     *
     * @return the result of the calculation
     */
    public double calculate() {
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 != 0 ? num1 / num2 : Double.NaN; // Handle division by zero.
                break;
            case '^':
                result = Math.pow(num1, num2);
                break;
            default:
                return Double.NaN; // Handle invalid operator.
        }

        // If result can be converted to an integer, return it as such.
        if (result == (int) result) {
            return (int) result;
        } else {
            return result;
        }
    }

    /**
     * Clears the calculator state.
     */
    public void clear() {
        num1 = num2 = result = 0;
        operator = ' ';
    }
}
