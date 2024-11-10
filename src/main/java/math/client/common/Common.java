package math.client.common;

import javax.swing.JDialog;
import javax.swing.JFrame;
import math.client.controller.ViewController;
import math.client.view.AbstractView;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

/**
 * Common methods for applications
 * @author dcthoai
 */
@SuppressWarnings("unused")
public class Common {

    public static void openViewByController(ViewController controller, ViewController parentViewController) {
        if (Objects.nonNull(controller)) {
            AbstractView view = controller.getMainView();

            for (WindowListener listener : view.getWindowListeners()) {
                view.removeWindowListener(listener);
            }

            view.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (Objects.nonNull(parentViewController)) {
                        parentViewController.openView();
                    }
                }
            });

            controller.openView();
        }
    }

    public static void hideViewByController(ViewController controller) {
        if (Objects.nonNull(controller))
            controller.hideView();
    }

    public static void closeViewByController(ViewController controller) {
        if (Objects.nonNull(controller))
            controller.closeView();
    }

    public static void closeAllView() {
        Window[] windows = Window.getWindows();

        for (Window window : windows) {
            if (window instanceof JFrame) {
                JFrame frame = (JFrame) window;
                frame.dispose();
            } else if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                dialog.dispose();
            }
        }
    }
}
