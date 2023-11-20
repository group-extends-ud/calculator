package vista;

import control.AppController;
import service.ResourceService;

import java.awt.*;

import javax.swing.*;

public class Ventana extends JFrame {
    private final ResourceService rs;
    private final AppController appController;
    private JTextField tfExpression;
    private JLabel lAnswer;
    private ButtonHandler buttonHandler;

    public Ventana(AppController c) {
        appController = c;
        rs = ResourceService.getService();

        loadKeyboard();
        loadTextFields();
        loadProperties();
    }

    private void loadTextFields() {
        tfExpression = new JTextField();
        tfExpression.setBounds(32, 72, 1200, 32);
        tfExpression.setForeground(Color.WHITE);
        tfExpression.setBackground(null);
        tfExpression.setCaretColor(Color.WHITE);
        tfExpression.setBorder(null);
        tfExpression.setFont(rs.fText1);
        tfExpression.setActionCommand("3,7");
        tfExpression.addActionListener(buttonHandler);
        add(tfExpression);

        lAnswer = new JLabel();
        lAnswer.setBounds(32, 146, 1200, 32);
        lAnswer.setForeground(Color.CYAN);
        lAnswer.setFont(rs.fText1);
        lAnswer.setHorizontalAlignment(JLabel.RIGHT);
        add(lAnswer);
    }

    private void loadKeyboard() {
        String[][] names = new String[][] {
            {"Left", "Right","Exit", "7", "8",  "9", "DEL", "AC"},
            {   "e",   "X^2", "X^Y", "4", "5",  "6",   "X",  "/"},
            {   "π",   "nPr", "nCr", "1", "2",  "3",   "+",  "%"},
            {   "τ",     "(",   ")", "0", ".", "\\",   "-",  "="}
        };

        Color[][] colors = new Color[][] {
            { rs.SCY,  rs.SCY,  rs.SCY, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCR, rs.SCR},
            {rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCB, rs.SCB},
            {rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG1, rs.SCG1, rs.SCG1, rs.SCB, rs.SCB},
            {rs.SCG2, rs.SCG2, rs.SCG2, rs.SCG1, rs.SCG1,  rs.SCB, rs.SCB, rs.SCB}
        };

        JPanel pKeyboard = new JPanel(null);

        pKeyboard.setBackground(new Color(56, 56, 56));
        pKeyboard.setBounds(2, 250, 1276, 468);
        add(pKeyboard);

        buttonHandler = new ButtonHandler(this);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                JButton b1 = new Boton(names[i][j], 150 + j * 120, 30 + i * 110, colors[i][j]);
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
        int cPos = tfExpression.getCaretPosition();
        String text = tfExpression.getText();

        String newText = text.substring(0, cPos) + str + text.substring(cPos);

        tfExpression.setText(newText);
        tfExpression.setCaretPosition(cPos+str.length());
    }

    public void del() {
        if(!tfExpression.getText().isEmpty()) {
            String newExpression = tfExpression.getText();
            tfExpression.setText(newExpression.substring(0, newExpression.length()-1));
        }
    }

    public void ac() {
        tfExpression.setText("");
        lAnswer.setText("");
    }

    public void left() {
        if(tfExpression.getCaretPosition()>0) {
            tfExpression.setCaretPosition(tfExpression.getCaretPosition() - 1);
        }
    }

    public void right() {
        if(tfExpression.getCaretPosition()< tfExpression.getText().length()) {
            tfExpression.setCaretPosition(tfExpression.getCaretPosition() + 1);
        }
    }

    public void calcular() {
        appController.calcular(tfExpression.getText());
    }

    public void showAnswer(String answer) {
        lAnswer.setText(answer);
    }

    public String getExpression() {
        return tfExpression.getText();
    }
}