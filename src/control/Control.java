package control;

import model.Calculator;
import model.Context;
import vista.Ventana;

public class Control {
    private final Ventana ventana;
    private final Context ctx;
    private final Calculator calc = new Calculator();

    public Control() {
        ventana = new Ventana(this);
        ctx = new Context(); // instantiate the context
    }

    public void calcular() {
        calc.setExpression(ventana.getExpression()); // set the expression to evaluate
        calc.setContext(ctx); // configure the calculator with the model.Context
        ventana.showAnswer(String.valueOf(calc.evaluate())); // Display the result
    }
}
