package vista;

import service.GraphicService;
import service.ResourceService;

import java.awt.*;

import javax.swing.*;

public class Ventana extends JFrame {
    private static final ResourceService rs = ResourceService.getInstance();
    private final JTextField lExpression;

    public Ventana() {
        lExpression = new JTextField();
        lExpression.setBounds(32, 32, 1200, 32);
        lExpression.setForeground(Color.WHITE);
        lExpression.setBackground(null);
        lExpression.setCaretColor(Color.WHITE);
        lExpression.setBorder(null);
        lExpression.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lExpression);

        loadKeyboard();
        loadProperties();
    }

    private void loadKeyboard() {
        String[][] names = new String[][] {
            {"Left", "Right",   "!",   "e", "7", "8",  "9", "DEL", "AC"},
            { "Ans",   "abs", "X^2", "Y^X", "4", "5",  "6",   "X",  "/"},
            {   "π",   "sin", "cos", "tan", "1", "2",  "3",   "+",  "%"},
            {   "(",     ")", "nPr", "nCr", "0", ".", "//",   "-",  "="}
        };

        Color[][] colors = new Color[][] {
            { rs.SCY,  rs.SCY, rs.SCG3, rs.SCG3, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCR, rs.SCR},
            {rs.SCG3, rs.SCG3, rs.SCG3, rs.SCG3, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCB, rs.SCB},
            {rs.SCG3, rs.SCG3, rs.SCG3, rs.SCG3, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCB, rs.SCB},
            {rs.SCG3, rs.SCG3, rs.SCG3, rs.SCG3, rs.SCG1, rs.SCG1,  rs.SCB, rs.SCB, rs.SCB}
        };

        JPanel pKeyboard = new JPanel(null);

        pKeyboard.setBackground(new Color(56, 56, 56));
        pKeyboard.setBounds(2, 250, 1276, 468);
        add(pKeyboard);

        ButtonHandler buttonHandler = new ButtonHandler(this);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                JButton b1 = new Boton(names[i][j], 100 + j * 120, 30 + i * 110, colors[i][j]);
                b1.setActionCommand(i+","+j);
                b1.addActionListener(buttonHandler);
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

    public void insert(String str) {
        int cPos = lExpression.getCaretPosition();
        String text = lExpression.getText();

        String newText = text.substring(0, cPos) + str + text.substring(cPos);

        lExpression.setText(newText);
        lExpression.setCaretPosition(cPos+str.length());
    }

    public void del() {
        if(!lExpression.getText().isEmpty()) {
            String newExpression = lExpression.getText();
            lExpression.setText(newExpression.substring(0, newExpression.length()-1));
        }
    }

    public void ac() {
        lExpression.setText("");
    }

    public void left() {
        if(lExpression.getCaretPosition()>0) {
            lExpression.setCaretPosition(lExpression.getCaretPosition() - 1);
        }
    }

    public void right() {
        if(lExpression.getCaretPosition()<lExpression.getText().length()) {
            lExpression.setCaretPosition(lExpression.getCaretPosition() + 1);
        }
    }
}

class Test {
    public static void main(String[] args) {
        new Ventana();
    }
}