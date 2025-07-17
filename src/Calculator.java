import javax.swing.JOptionPane;

public class Calculator {

    /*
     * This class represents the GUI for the calculator.
     * It uses JOptionPane to interact with the user.
     * It takes user input for two numbers and an operation,
     * performs the operation, and outputs the result.
     * Does not support decimal exponents like roots, unfortunately.
     */

    public static void main(String[] args) throws Exception {

        // Welcome user to the calculator.
        JOptionPane.showMessageDialog(null, "Welcome to Calculator!");

        // Take user's first number input.
        double a = Double.parseDouble(JOptionPane.showInputDialog(null, "<html><code></code><br>Enter first number:</html>"));

        // Take user's operation input.
        String operation;
        do {
            operation = JOptionPane.showInputDialog(null, "<html><code>" + a + "</code><br>Enter operation (+, -, *, /, ^):</html>");

            // If input is not a valid operation, tell user and retry.
            if (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/") && !operation.equals("^")) {
                JOptionPane.showMessageDialog(null, "Invalid operation. Please pick out of the following: +, -, *, /, ^. ");
            }
        } while (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/") && !operation.equals("^"));

        // Take user's second number input.
        double b = Double.parseDouble(JOptionPane.showInputDialog(null, "<html><code>" + a +  " " + operation + "</code><br>Enter second number:</html>"));

        // Create an instance of ArithmeticOperations to perform calculations.
        ArithmeticOperations math = new ArithmeticOperations();

        // Based on input operation, perform calculation.
        double answer = 0;
        switch (operation) {
            case "+":  // If addition, add a and b.
                answer = math.add(a, b);
                break;
            case "-":  // If subtraction, subtract a and b.
                answer = math.subtract(a, b);
                break;
            case "*":  // If multiplication, multiply a and b.
                answer = math.multiply(a, b);
                break;
            case "/":  // If division, divide a and b.
                answer = math.divide(a, b);
                break;
            case "^":  // If exponential, a is base and b is exponent.
                answer = math.power(a, b);
                break;
        }
        
        // Is answer is floorable without losing significant digits, floor. Then print answer.
        String answerMsg = "<html><code>" + a +  " " + operation + " " + b + " = " + answer + "</code><br>The answer is: </html>";
        if (answer % 1.0 == 0) {
            JOptionPane.showMessageDialog(null, answerMsg + ((int)answer));
        } else {
            JOptionPane.showMessageDialog(null, answerMsg + answer);
        }
    }
}
