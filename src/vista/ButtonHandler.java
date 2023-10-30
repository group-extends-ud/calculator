package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String coordenadas = e.getActionCommand();
        if(coordenadas.equals("0,0")) {
            System.out.println(e.getActionCommand());
        }
    }
}
