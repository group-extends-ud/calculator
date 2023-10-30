import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Boton extends JButton {
    private Font font = new Font("Consolas", Font.PLAIN, 26);

    public Boton(String text, int x, int y, Color bg) {
        super(text);
        setBounds(x, y, 110, 80);
        setFont(font);
        setForeground(Color.WHITE);
        setBackground(bg);
        setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.YELLOW);
            }
        });
    }
}
