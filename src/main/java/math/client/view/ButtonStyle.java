package math.client.view;

import javax.swing.*;
import java.awt.*;

public class ButtonStyle extends JButton {
    public ButtonStyle(String text, int width, int height) {
        super(text);

        setFont(new Font("Roboto", Font.BOLD, 13));
        setPreferredSize(new Dimension(width, height));
        setFocusPainted(false);
        setBackground(Color.LIGHT_GRAY);
    }
}
