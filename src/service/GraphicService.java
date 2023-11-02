package service;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.Border;

public class GraphicService {
    private final static GraphicService instance = new GraphicService();

    private GraphicService() {}

    public static GraphicService getService() {
        return instance;
    }
    
    public Border getRoundBorder(Color color, int radio, boolean esLineal, Image imagen) {
        return new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int ancho, int alto) {
                Graphics2D g2= (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
                Area area;
                Component padreContenedor  = c.getParent();
                RoundRectangle2D roundRect = new RoundRectangle2D.Double();
                roundRect.setRoundRect(x, y, ancho - 1, alto - 1, radio, radio);
                if(esLineal){
                    drawBackground(c, padreContenedor, imagen, g2, ancho, alto);
                    area = drawBorder(c, g2, color, ancho, alto, roundRect);
                }
                else{
                    area = drawBorder(c, g2, color, ancho, alto, roundRect);
                    drawBackground(c, padreContenedor, imagen, g2, ancho, alto);
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
    }
    
    public Area drawBorder(
            Component c, Graphics2D g2, Color color, int ancho, int alto, RectangularShape figura
    ) {
        if(color == null)
            g2.setPaint(c.getBackground());
        else
            g2.setPaint(color);
        Area area = new Area(figura);
        Rectangle rect = new Rectangle(0,0,ancho, alto);
        Area regionBorde = new Area(rect);
        regionBorde.subtract(area);
        g2.setClip(regionBorde);
        return area;
    }
    
    public void drawBackground(
            Component c, Component padreContenedor, Image imagen, Graphics2D g2, int ancho, int alto
    ) {
        if(imagen != null)
            g2.drawImage(
                imagen,0, 0, imagen.getWidth(null), imagen.getHeight(null),
                c.getX(), c.getY(), imagen.getWidth(null) + c.getX(),
            imagen.getHeight(null) + c.getY(), c
            );
        else{
            g2.setColor(padreContenedor.getBackground());
            g2.fillRect(0, 0, ancho, alto);
        }
    }
}
