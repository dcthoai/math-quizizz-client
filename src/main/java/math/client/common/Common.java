package math.client.common;

import math.client.controller.ViewController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

/**
 * Common methods for applications
 * @author dcthoai
 */
public class Common {

    public static void openViewByController(ViewController controller, ViewController parentViewController) {
        if (Objects.nonNull(controller)) {
            controller.openView();
            controller.getMainView().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (Objects.nonNull(parentViewController)) {
                        parentViewController.openView();
                    }
                }
            });
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
}
