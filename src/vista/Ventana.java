import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame {
    public Ventana() {
        loadKeyboard();
        loadProperties();
    }

    private void loadKeyboard() {
        String[][] names = new String[][] {
            {"SHIFT", "MODE", "DRG", "LN", "7", "8", "9", "DEL", "AC"},
            {"MR", "MS", "M+", "X^2", "4", "5", "6", "X", "/"},
            {"HYP", "SIN", "COS", "TAN", "1", "2", "3", "+", "="},
            {"(",")", "X^-1", "sqrt", "0", ".", "+-", "-", "="}
        };

        JPanel pKeyboard = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                g.drawLine(635, 0, 635, 508);
            }
        };
        pKeyboard.setBackground(new Color(56, 56, 56));
        pKeyboard.setBounds(0, 212, 1280, 508);
        add(pKeyboard);

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                JButton b1 = new Boton(names[j][i], 80 + i * 120, 30 + j * 110, new Color(34, 34, 34));
                pKeyboard.add(b1);
            }
            for (int i = 5; i < 9; i++) {
                JButton b1 = new Boton(names[j][i], 120 + i * 120, 30 + j * 110, new Color(107, 107, 107));
                pKeyboard.add(b1);
            }
        }
    }

    private void loadProperties() {
        setUndecorated(true);
        getContentPane().setBackground(Color.BLACK);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }
}

class Test {
    public static void main(String[] args) {
        new Ventana();
    }
}