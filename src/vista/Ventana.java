import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

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

        JPanel pKeyboard = new JPanel(null);

        Border bordeRedondeado = DibujarBordeRedondeado(null, 40, false, null);
        pKeyboard.setBackground(new Color(56, 56, 56));
        pKeyboard.setBounds(2, 250, 1276, 468);
        add(pKeyboard);

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                JButton b1 = new Boton(names[j][i], 100 + i * 120, 30 + j * 110, new Color(34, 34, 34));
                b1.setBorder(bordeRedondeado);
                pKeyboard.add(b1);
            }
            for (int i = 4; i < 9; i++) {
                JButton b1 = new Boton(names[j][i], 100 + i * 120, 30 + j * 110, new Color(107, 107, 107));
                b1.setBorder(bordeRedondeado);
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
    
    public Border DibujarBordeRedondeado (Color color, int radio, boolean esLineal, Image imagen) {
        Border bordeRedondeado = new Border() {

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int ancho, int alto) {
                Graphics2D g2= (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
                Area area;
                Component padreContenedor  = c.getParent();
                RoundRectangle2D rectanguloBordeado = new RoundRectangle2D.Double();
                rectanguloBordeado.setRoundRect(x, y, ancho - 1, alto - 1, radio, radio);
                if(esLineal){
                    dibujarFondo(c, padreContenedor, imagen, g2, ancho, alto);
                    area = dibujarBorde(c, g2, color, x, y, ancho, alto, rectanguloBordeado);
                }
                else{
                    area = dibujarBorde(c, g2, color, x, y, ancho, alto, rectanguloBordeado);
                    dibujarFondo(c, padreContenedor, imagen, g2, ancho, alto);
                }
                g2.setClip(null);
                g2.draw(area);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(2, 2, 2, 2);
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        };
        return bordeRedondeado;
    }
    
    public Area dibujarBorde(
        Component c, Graphics2D g2, Color color, int x, int y, int ancho, int alto, RectangularShape figura
    ){
        if(color == null)
            g2.setPaint(c.getBackground());
        else
            g2.setPaint(color);
        Area area = new Area(figura);
        Rectangle rectangulo = new Rectangle(0,0,ancho, alto);
        Area regionBorde = new Area(rectangulo);
        regionBorde.subtract(area);
        g2.setClip(regionBorde);
        return area;
    }
    
    public void dibujarFondo(Component c, Component padreContenedor, Image imagen, Graphics2D g2, int ancho, int alto){
        if(imagen != null)
            g2.drawImage(
                imagen, 
                0, 0, imagen.getWidth(null), imagen.getHeight(null),
                c.getX(), c.getY(), imagen.getWidth(null) + c.getX(), imagen.getHeight(null) + c.getY(),
                c
            );
        else{
            g2.setColor(padreContenedor.getBackground());
            g2.fillRect(0, 0, ancho, alto);
        }
    }

}

class Test {
    public static void main(String[] args) {
        new Ventana();
    }
}