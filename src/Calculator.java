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
    JFrame frame; // Main frame for the calculator GUI.
    JTextField textfield; // Text field for displaying input and results.
    JButton[] numberButtons = new JButton[10]; // Array of buttons for numbers 0-9.
    JButton[] operationButtons = new JButton[9]; // Array for operation buttons.

    private CalculatorLogic logic = new CalculatorLogic(); // Logic for calculations.

    final int ADD = 0, SUB = 1, MUL = 2, DIV = 3, EXP = 4, NEG = 5, DEC = 6, EQL = 7, CLR = 8; // Constants for button indices.

    Font myFont = new Font("Monospaced", Font.BOLD, 30); // Font for buttons and text field.

    double result = 0; // Variables to hold result.

    // Constants for layout and sizing.
    private static final int FRAME_WIDTH = 410;
    private static final int FRAME_HEIGHT = 410;
    private static final int TEXTFIELD_X = 30;
    private static final int TEXTFIELD_Y = 25;
    private static final int TEXTFIELD_WIDTH = 340;
    private static final int TEXTFIELD_HEIGHT = 50;
    private static final int BTN_WIDTH = 85;
    private static final int BTN_HEIGHT = 50;
    private static final int GRID_START_X = 30;
    private static final int GRID_START_Y = 100;

    /**
     * Calculator constructor initializes the calculator.
     * It sets up the GUI components and event listeners.
     */
    Calculator() {
        // Set up the main frame.
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setLayout(null);

        // Set up the text field for display.
        textfield = new JTextField(20);
        textfield.setFont(myFont);
        textfield.setEditable(false); // User cannot type directly.
        textfield.setHorizontalAlignment(JTextField.RIGHT); // Right-align text.
        textfield.setBounds(TEXTFIELD_X, TEXTFIELD_Y, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT); // Set bounds for the text field.
        frame.add(textfield);

        // Initialize operation buttons with labels and set their properties.
        operationButtons[ADD] = new JButton("+");
        operationButtons[SUB] = new JButton("-");
        operationButtons[MUL] = new JButton("*");
        operationButtons[DIV] = new JButton("/");
        operationButtons[EXP] = new JButton("^");
        operationButtons[NEG] = new JButton("*-");
        operationButtons[DEC] = new JButton(".");
        operationButtons[EQL] = new JButton("=");
        operationButtons[CLR] = new JButton("C");

        // Set up operation and special buttons with font and listeners.
        for (JButton button : operationButtons) {
            button.setFont(myFont);
            button.setFocusable(false); // Disable focus for buttons.
            button.addActionListener(this); // Add action listener for each button.
        }

        // Initialize number buttons 0-9.
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i)); // Create button for each digit.
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(this); // Add event listener.
        }

        // Number and operator buttons in desired order for the grid.
        JButton[][] buttonGrid = {
            {numberButtons[1], numberButtons[2], numberButtons[3], operationButtons[ADD]},
            {numberButtons[4], numberButtons[5], numberButtons[6], operationButtons[SUB]},
            {numberButtons[7], numberButtons[8], numberButtons[9], operationButtons[MUL]},
            {operationButtons[DEC], numberButtons[0], operationButtons[EXP], operationButtons[DIV]}
        };

        // Set bounds and add to frame (for the 4x4 grid).
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JButton btn = buttonGrid[row][col];
                btn.setBounds(GRID_START_X + col * BTN_WIDTH, GRID_START_Y + row * BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT);
                frame.add(btn);
            }
        }
        
        // Last line for the negative, clear, and equals buttons. Clear button is double width.
        operationButtons[NEG].setBounds(GRID_START_X, GRID_START_Y + 4 * BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT); // Negative button.
        frame.add(operationButtons[NEG]);

        operationButtons[CLR].setBounds(GRID_START_X + BTN_WIDTH, GRID_START_Y + 4 * BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT); // Clear button.
        frame.add(operationButtons[CLR]);

        operationButtons[EQL].setBounds(GRID_START_X + BTN_WIDTH * 2, GRID_START_Y + 4 * BTN_HEIGHT, BTN_WIDTH * 2, BTN_HEIGHT); // Equals button (double width).
        frame.add(operationButtons[EQL]);

        frame.setVisible(true); // Make the frame visible.
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
        if (e.getSource() == operationButtons[DEC]) {
            String current = textfield.getText();
            // Only add decimal if not already present.
            if (!current.contains(".")) {
                if (current.isEmpty()) {
                    textfield.setText("0.");
                } else {
                    textfield.setText(current + ".");
                }
            }
        } 

        // Handle negative button press (toggle sign).
        else if (e.getSource() == operationButtons[NEG]) {
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
        else if (e.getSource() == operationButtons[ADD]) {
            if (!textfield.getText().isEmpty()) {
                logic.setNum1(Double.parseDouble(textfield.getText()));
            }
            logic.setOperator('+');
            textfield.setText("");
        } 

        // Handle subtraction.
        else if (e.getSource() == operationButtons[SUB]) {
            if (!textfield.getText().isEmpty()) {
                logic.setNum1(Double.parseDouble(textfield.getText()));
            }
            logic.setOperator('-');
            textfield.setText("");
        } 

        // Handle multiplication.
        else if (e.getSource() == operationButtons[MUL]) {
            if (!textfield.getText().isEmpty()) {
                logic.setNum1(Double.parseDouble(textfield.getText()));
            }
            logic.setOperator('*');
            textfield.setText("");
        } 

        // Handle division.
        else if (e.getSource() == operationButtons[DIV]) {
            if (!textfield.getText().isEmpty()) {
                logic.setNum1(Double.parseDouble(textfield.getText()));
            }
            logic.setOperator('/');
            textfield.setText("");
        } 

        // Handle exponentiation.
        else if (e.getSource() == operationButtons[EXP]) {
            if (!textfield.getText().isEmpty()) {
                logic.setNum1(Double.parseDouble(textfield.getText()));
            }
            logic.setOperator('^');
            textfield.setText("");
        } 

        // Handle equals button press (perform calculation).
        else if (e.getSource() == operationButtons[EQL]) {
            if (!textfield.getText().isEmpty()) {
                logic.setNum2(Double.parseDouble(textfield.getText()));
                double result = logic.calculate();
                
                // If result can be converted to an integer, display it as such. Also, display "Err" if result is Nan.
                if (Double.isNaN(result)) {
                    textfield.setText("Err");
                } else if (Double.isInfinite(result)) {
                    textfield.setText("Inf");
                } else if (result == (int) result) {
                    textfield.setText(String.valueOf((int) result));
                } else {
                    textfield.setText(String.valueOf(result));
                }

                logic.setNum1(result); // Set the result as the first number for subsequent operations.
                logic.setOperator(' '); // Reset operator after calculation.
            }
        } 
        
        // Handle clear button press (reset calculator).
        else if (e.getSource() == operationButtons[CLR]) {
            textfield.setText("");
            logic.clear(); // Clear the logic state.
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
        new Calculator(); // Create an instance of Calculator.
    }
}