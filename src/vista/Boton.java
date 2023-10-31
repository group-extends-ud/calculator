package vista;

import service.GUIService;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.Border;

public class Boton extends JButton {
    private GUIService g;
    private final static Color onColor = new Color(255, 207, 31);
    private final Color offColor;

    public Boton(String text, int x, int y, Color bg) {
        super(text);
        offColor = bg;
        
        setBounds(x, y, 110, 80);
        setFont(GUIService.fText);
        setBorder(GUIService.bordeRedondeado);
        setForeground(Color.WHITE);
        setBackground(bg);
        setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(onColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(offColor);
            }
        });
    }
}
