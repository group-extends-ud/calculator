package service;

import javax.swing.border.Border;
import java.awt.*;

public class ResourceService {
    // Singleton
    private final static ResourceService rs = new ResourceService();

    /**
     *  COLOR PALETTES
     **/
    // Scientific model.Calculator
    public final Color SCB = new Color(5, 68, 99); // Blue
    public final Color SCR = new Color(102, 19, 13); // Red
    public final Color SCY = new Color(168, 133, 31); // Yellow
    public final Color SCG1 = new Color(107, 107, 107); // Gray 1
    public final Color SCG2 = new Color(34, 34, 34); // Gray 2

    /**
     * Fonts
     **/
    // Windows 10
    public final Font fText = new Font("Consolas", Font.PLAIN, 26);
    public final Font fText1 = new Font("Arial", Font.PLAIN, 20);

    /**
     * Borders
     **/
    public final Border bordeRedondeado;

    private ResourceService() {
        // GUIService reference
        GraphicService gs = GraphicService.getService();

        bordeRedondeado = gs.getRoundBorder(null, 40, false, null);
    }
    public static ResourceService getService() {
        return rs;
    }
}
