import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The Calculator class provides a simple GUI calculator using JOptionPane.
 * It prompts the user for two numbers and an operation, performs the calculation,
 * and displays the result. Supported operations are addition, subtraction,
 * multiplication, division, and exponentiation.
 * This class uses the ArithmeticOperations class for calculation logic.
 */
public class Calculator implements ActionListener {
    JFrame frame;  // Main frame for the calculator GUI.
    JTextField textfield;  // Text field for displaying input and results.
    JButton[] numberButtons = new JButton[10];  // Array of buttons for numbers 0-9.
    JButton addButton, subButton, mulButton, divButton, expButton, negButton, decButton, eqButton, clrButton;

    Font myFont = new Font("Monospaced", Font.BOLD, 30);  // Font for buttons and text field.

    double num1, num2, result = 0;  // Variables to hold numbers and result.
    char operator = ' ';  // Variable to hold the operator.

    /**
     * Calculator constructor initializes the calculator.
     * It sets up the GUI components and event listeners.
     */
    Calculator() {
        // Set up the main frame.
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(410, 410);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setLayout(null);

        // Set up the text field for display.
        textfield = new JTextField(20);
        textfield.setFont(myFont);
        textfield.setEditable(false); // User cannot type directly.
        textfield.setHorizontalAlignment(JTextField.RIGHT); // Right-align text.
        textfield.setBounds(30, 25, 340, 50); // Set bounds for the text field.
        frame.add(textfield);

        // Create operation and special buttons.
        addButton = new JButton("+");  // Addition
        subButton = new JButton("-");  // Subtraction
        mulButton = new JButton("*");  // Multiplication
        divButton = new JButton("/");  // Division
        expButton = new JButton("^");  // Exponentiation
        negButton = new JButton("*-"); // Negative
        decButton = new JButton(".");  // Decimal
        eqButton = new JButton("=");   // Equals
        clrButton = new JButton("C");  // Clear

        // Button grid layout parameters.
        int btnWidth = 85, btnHeight = 50;
        int startX = 30, startY = 100;

        // Initialize number buttons 0-9.
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i)); // Create button for each digit.
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(this); // Add event listener.
        }

        // Set up operation and special buttons with font and listeners.
        addButton.setFont(myFont);
        addButton.setFocusable(false);
        addButton.addActionListener(this);

        subButton.setFont(myFont);
        subButton.setFocusable(false);
        subButton.addActionListener(this);

        mulButton.setFont(myFont);
        mulButton.setFocusable(false);
        mulButton.addActionListener(this);

        divButton.setFont(myFont);
        divButton.setFocusable(false);
        divButton.addActionListener(this);

        expButton.setFont(myFont);
        expButton.setFocusable(false);
        expButton.addActionListener(this);

        negButton.setFont(myFont);
        negButton.setFocusable(false);
        negButton.addActionListener(this);

        decButton.setFont(myFont);
        decButton.setFocusable(false);
        decButton.addActionListener(this);

        eqButton.setFont(myFont);
        eqButton.setFocusable(false);
        eqButton.addActionListener(this);

        clrButton.setFont(myFont);
        clrButton.setFocusable(false);
        clrButton.addActionListener(this);

        // Number and operator buttons in desired order for the grid.
        JButton[][] buttonGrid = {
            {numberButtons[1], numberButtons[2], numberButtons[3], addButton},
            {numberButtons[4], numberButtons[5], numberButtons[6], subButton},
            {numberButtons[7], numberButtons[8], numberButtons[9], mulButton},
            {decButton,        numberButtons[0], expButton,        divButton}
        };

        // Set bounds and add to frame (for the 4x4 grid).
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JButton btn = buttonGrid[row][col];
                btn.setBounds(startX + col * btnWidth, startY + row * btnHeight, btnWidth, btnHeight);
                frame.add(btn);
            }
        }

        // Last line: negButton, clrButton, and eqButton (two buttons wide).
        int eqWidth = btnWidth * 2;
        int lastY = startY + 4 * btnHeight;

        negButton.setBounds(startX, lastY, btnWidth, btnHeight); // Negative button.
        frame.add(negButton);

        clrButton.setBounds(startX + btnWidth, lastY, btnWidth, btnHeight); // Clear button.
        frame.add(clrButton);

        eqButton.setBounds(startX + btnWidth * 2, lastY, eqWidth, btnHeight); // Equals button (double width).
        frame.add(eqButton);

        frame.setVisible(true);  // Make the frame visible.
    }

    /**
     * This method is called when an action event occurs, such as a button click.
     * It handles the logic for performing arithmetic operations based on user input.
     *
     * @param e the ActionEvent that triggered this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle number button presses.
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        // Handle decimal button press.
        if (e.getSource() == decButton) {
            String current = textfield.getText();
            // Only add decimal if not already present
            if (!current.contains(".")) {
                if (current.isEmpty()) {
                    textfield.setText("0.");
                } else {
                    textfield.setText(current + ".");
                }
            }
        } 
        // Handle negative button press (toggle sign).
        else if (e.getSource() == negButton) {
            String current = textfield.getText();
            if (!current.isEmpty() && !current.equals("0")) {
                if (current.startsWith("-")) {
                    textfield.setText(current.substring(1));
                } else {
                    textfield.setText("-" + current);
                }
            }
        } 
        // Handle addition.
        else if (e.getSource() == addButton) {
            if (!textfield.getText().isEmpty()) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '+';
                textfield.setText(""); // Prepare for next input
            }
        } 
        // Handle subtraction.
        else if (e.getSource() == subButton) {
            if (!textfield.getText().isEmpty()) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '-';
                textfield.setText("");
            }
        } 
        // Handle multiplication.
        else if (e.getSource() == mulButton) {
            if (!textfield.getText().isEmpty()) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '*';
                textfield.setText("");
            }
        } 
        // Handle division.
        else if (e.getSource() == divButton) {
            if (!textfield.getText().isEmpty()) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '/';
                textfield.setText("");
            }
        } 
        // Handle exponentiation.
        else if (e.getSource() == expButton) {
            if (!textfield.getText().isEmpty()) {
                num1 = Double.parseDouble(textfield.getText());
                operator = '^';
                textfield.setText("");
            }
        } 
        // Handle equals button press (perform calculation).
        else if (e.getSource() == eqButton) {
            if (!textfield.getText().isEmpty()) {
                num2 = Double.parseDouble(textfield.getText());
                switch (operator) {
                    case '+':
                        result = ArithmeticOperations.add(num1, num2);
                        break;
                    case '-':
                        result = ArithmeticOperations.subtract(num1, num2);
                        break;
                    case '*':
                        result = ArithmeticOperations.multiply(num1, num2);
                        break;
                    case '/':
                        result = ArithmeticOperations.divide(num1, num2);
                        break;
                    case '^':
                        result = ArithmeticOperations.power(num1, num2);
                        break;
                    default:
                        textfield.setText("Err");
                        return;
                }
                // If result can be converted to an integer, display it as such.
                if (result == (int) result) {
                    textfield.setText(String.valueOf((int) result));
                } else {
                    textfield.setText(String.valueOf(result));
                }

                num1 = result;  // Update num1 for potential further calculations.
            }
        } 
        // Handle clear button press (reset calculator).
        else if (e.getSource() == clrButton) {
            textfield.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = ' ';
        }
    }

    /**
     * The main method launches the calculator GUI, takes user input,
     * performs the selected arithmetic operation, and displays the result.
     *
     * @param args command-line arguments (not used).
     * @throws Exception if an error occurs during input or calculation.
     */
    public static void main(String[] args) throws Exception {
        new Calculator();  // Create an instance of Calculator.
    }
}