import javax.swing.JOptionPane;

/**
 * The Calculator class provides a simple GUI calculator using JOptionPane.
 * It prompts the user for two numbers and an operation, performs the calculation,
 * and displays the result. Supported operations are addition, subtraction,
 * multiplication, division, and exponentiation.
 * This class uses the ArithmeticOperations class for calculation logic.
 */
public class Calculator {

    /**
     * The main method launches the calculator GUI, takes user input,
     * performs the selected arithmetic operation, and displays the result.
     *
     * @param args command-line arguments (not used)
     * @throws Exception if an error occurs during input or calculation
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
        
        // If answer is floorable without losing significant digits, floor. Then print answer.
        String answerMsg = "<html><code>" + a +  " " + operation + " " + b + " = " + answer + "</code><br>The answer is: </html>";
        if (answer % 1.0 == 0) {
            JOptionPane.showMessageDialog(null, answerMsg + ((int)answer));
        } else {
            JOptionPane.showMessageDialog(null, answerMsg + answer);
        }
    }
}