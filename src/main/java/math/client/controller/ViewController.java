package math.client.controller;

import math.client.view.AbstractView;

/**
 * Implements this interface to use view control operations of different controllers flexibly.
 * @author dcthoai
 */
public interface ViewController {

    void openView();

    void hideView();

    void closeView();

    AbstractView getMainView();
}
