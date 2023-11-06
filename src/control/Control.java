package control;

import model.Calculator;
import model.Context;
import vista.Ventana;

import javax.swing.*;

public class Control {
    private final Ventana ventana;
    private final Context ctx;
    private final Calculator calc = new Calculator();

    public Control() {
        ventana = new Ventana(this);
        ctx = new Context(); // instantiate the context
    }

    public void calcular() {
        String expresion = ventana.getExpression();
        if(expresion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese una expresión",
                    "Expresión vacía", JOptionPane.ERROR_MESSAGE);
        }
        else {
            calc.setExpression(expresion); // set the expression to evaluate
            calc.setContext(ctx); // configure the calculator with the model.Context
            try {
                ventana.showAnswer(String.valueOf(calc.evaluate())); // Display the result
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese una expresión válida",
                        "Expresión inválida", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
