package vista;

import service.ResourceService;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Boton extends JButton {
    private final static ResourceService rs = ResourceService.getInstance();

    public Boton(String text, int x, int y, Color bg) {
        super(text);
        
        setBounds(x, y, 110, 80);
        setFont(rs.fText);
        setBorder(rs.bordeRedondeado);
        setForeground(Color.WHITE);
        setBackground(bg);
        setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(rs.SCY);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(bg);
            }
        });
    }
}
