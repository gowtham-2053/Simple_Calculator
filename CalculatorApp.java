import java.awt.*;
import java.awt.event.*;

public class CalculatorApp {
    // Create frame
    private Frame frame;
    private TextField textField;
    private String operator;
    private double num1, num2, result;

    public CalculatorApp() {
        // Frame setup
        frame = new Frame("Calculator");
        textField = new TextField();
        operator = "";

        // Layout setup
        frame.setLayout(new BorderLayout());
        frame.add(textField, BorderLayout.NORTH);
        
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 4));

        // Buttons setup
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        // Add buttons to panel
        for (String text : buttons) {
            Button button = new Button(text);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);

        // Frame size and visibility
        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    // Button Click Listener
    class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.charAt(0) == '=') {
                // Perform the calculation
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Error");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
                operator = "";
            } else if (command.charAt(0) == 'C') {
                // Clear the text field
                textField.setText("");
                operator = "";
            } else if ("0123456789.".indexOf(command) >= 0) {
                // Append the number or dot
                textField.setText(textField.getText() + command);
            } else {
                // Operator clicked, store the number and operator
                if (!textField.getText().equals("")) {
                    num1 = Double.parseDouble(textField.getText());
                    operator = command;
                    textField.setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        // Run the application
        new CalculatorApp();
    }
}
