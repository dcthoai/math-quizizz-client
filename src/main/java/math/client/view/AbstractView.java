package math.client.view;

import javax.swing.*;

public class AbstractView extends JFrame {

    public AbstractView() {}

    public AbstractView(String title) {
        setTitle(title);
        pack();
    }

    public AbstractView(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
    }

    public void title(String title) {
        setTitle(title);
    }

    public void size(int width, int height) {
        setSize(width, height);
    }

    public void open() {
        setVisible(true);
        setLocationRelativeTo(null); // Center the frame
    }

    public void hideView() {
        setVisible(false);
    }

    public void exit() {
        dispose();
    }
}