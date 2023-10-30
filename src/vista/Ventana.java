package vista;

import service.GUIService;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class Ventana extends JFrame {
    private final GUIService g;
    private final JLabel lExpression;

    public Ventana() {
        g = GUIService.getService();

        lExpression = new JLabel();
        lExpression.setBounds(32, 32, 1200, 32);
        lExpression.setForeground(Color.WHITE);
        lExpression.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lExpression);

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

        JPanel pKeyboard = new JPanel(null);

        pKeyboard.setBackground(new Color(56, 56, 56));
        pKeyboard.setBounds(2, 250, 1276, 468);
        add(pKeyboard);

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 9; i++) {
                Color bg;
                if(i<4) {
                    bg = new Color(34, 34, 34);
                }
                else {
                    bg = new Color(107, 107, 107);
                }
                JButton b1 = new Boton(names[j][i], 100 + i * 120, 30 + j * 110, bg);
                b1.setActionCommand(i+","+j);
                b1.addActionListener(new ButtonHandler());
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