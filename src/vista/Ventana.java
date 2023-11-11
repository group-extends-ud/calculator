package vista;

import control.AppController;
import service.ResourceService;

import java.awt.*;

import javax.swing.*;

public class Ventana extends JFrame {
    private final ResourceService rs;
    private final AppController appController;
    private JTextField lExpression;
    private JLabel lAnswer;
    private ButtonHandler buttonHandler;

    public Ventana(AppController c) {
        appController = c;
        rs = ResourceService.getService();
        
        loadTextFields();
        loadKeyboard();
        loadProperties();
    }

    private void loadTextFields() {
        lExpression = new JTextField();
        lExpression.setBounds(32, 72, 1200, 32);
        lExpression.setForeground(Color.WHITE);
        lExpression.setBackground(null);
        lExpression.setCaretColor(Color.WHITE);
        lExpression.setBorder(null);
        lExpression.setFont(rs.fText1);
        lExpression.setActionCommand("3,8");
        lExpression.addActionListener(buttonHandler);
        add(lExpression);

        lAnswer = new JLabel();
        lAnswer.setBounds(32, 146, 1200, 32);
        lAnswer.setForeground(Color.CYAN);
        lAnswer.setFont(rs.fText1);
        lAnswer.setHorizontalAlignment(JLabel.RIGHT);
        add(lAnswer);
    }

    private void loadKeyboard() {
        String[][] names = new String[][] {
            {"Left", "Right",   "!",   "e", "7", "8",  "9", "DEL", "AC"},
            { "Ans",   "abs", "X^2", "Y^X", "4", "5",  "6",   "X",  "/"},
            {   "π",   "sin", "cos", "tan", "1", "2",  "3",   "+",  "%"},
            {   "(",     ")", "nPr", "nCr", "0", ".", "\\",   "-",  "="}
        };

        Color[][] colors = new Color[][] {
            { rs.SCY,  rs.SCY, rs.SCG2, rs.SCG2, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCR, rs.SCR},
            {rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCB, rs.SCB},
            {rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCB, rs.SCB},
            {rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG1, rs.SCG1,  rs.SCB, rs.SCB, rs.SCB}
        };

        JPanel pKeyboard = new JPanel(null);

        pKeyboard.setBackground(new Color(56, 56, 56));
        pKeyboard.setBounds(2, 250, 1276, 468);
        add(pKeyboard);

        buttonHandler = new ButtonHandler(this);

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

    public void calcular() {
        appController.calcular();
    }

    public void showAnswer(String answer) {
        lAnswer.setText(answer);
    }

    public String getExpression() {
        return lExpression.getText();
    }
}