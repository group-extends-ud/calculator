package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {
    private final Ventana ventana; // cargador

    public ButtonHandler(Ventana v) {
        ventana = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String coordenadas = e.getActionCommand();

        switch (coordenadas) {
            // panel numérico
            case "3,4" -> ventana.insert("0");
            case "2,4" -> ventana.insert("1");
            case "2,5" -> ventana.insert("2");
            case "2,6" -> ventana.insert("3");
            case "1,4" -> ventana.insert("4");
            case "1,5" -> ventana.insert("5");
            case "1,6" -> ventana.insert("6");
            case "0,4" -> ventana.insert("7");
            case "0,5" -> ventana.insert("8");
            case "0,6" -> ventana.insert("9");
            case "3,5" -> ventana.insert(".");

            // constantes
            case "0,3" -> ventana.insert("e"); // euler
            case "2,0" -> ventana.insert("π"); // pi

            // operaciones
            case "2,7" -> ventana.insert("+");
            case "3,7" -> ventana.insert("-");
            case "1,7" -> ventana.insert("*");
            case "1,8" -> ventana.insert("/"); // división
            case "2,8" -> ventana.insert("%"); // módulo
            case "3,6" -> ventana.insert("//"); // división entera
            case "1,2" -> ventana.insert("^2"); // elevar al cuadrado
            case "1,3" -> ventana.insert("^("); // elevar a la x
            case "0,2" -> ventana.insert("!"); // factorial
            case "1,1" -> ventana.insert("abs("); // valor absoluto
            case "3,2" -> ventana.insert("P"); // permutación
            case "3,3" -> ventana.insert("C"); // combinatoria

            // funciones trigonométricas
            case "2,1" -> ventana.insert("sin("); // seno
            case "2,2" -> ventana.insert("cos("); // cos
            case "2,3" -> ventana.insert("tan("); // tan

            // caracteres
            case "3,0" -> ventana.insert("(");
            case "3,1" -> ventana.insert(")");

            // interacción
            case "0,0" -> ventana.left();
            case "0,1" -> ventana.right();
            case "0,7" -> ventana.del();
            case "0,8" -> ventana.ac();

            // historial
            case "1,0" -> ventana.insert("Ans");

            // pending
            default -> System.out.println(coordenadas);
        }
    }

}
