package control;

import model.Calculator;
import model.Context;
import vista.Ventana;

import javax.swing.*;

public class AppController {
    private final Ventana ventana;
    private final Context ctx;
    private final Calculator calc;

    public AppController() {
        calc = new Calculator(); // instantiate the calculator
        ctx = new Context(); // instantiate the context
        ventana = new Ventana(this);
    }

    public void calcular() {
        String expression = ventana.getExpression();
        if(expression.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese una expresión",
                    "Expresión vacía", JOptionPane.ERROR_MESSAGE);
        }
        else {
            calc.setExpression(expression); // set the expression to evaluate
            calc.setContext(ctx); // configure the calculator with the model.Context
            try {
                String answer = String.valueOf(calc.evaluate());
                if(answer.equals("Infinity")) {
                    answer = "Cannot divide by zero";
                }
                else if(answer.equals("null")) {
                    answer = "Mathematical error";
                }
                ventana.showAnswer(answer); // Display the result
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese una expresión válida",
                        "Expresión inválida", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
