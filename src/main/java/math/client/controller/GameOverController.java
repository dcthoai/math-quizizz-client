package math.client.controller;

import com.google.gson.Gson;

import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.GameOverView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameOverController implements RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(GameOverController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final GameOverView gameOverView =  GameOverView.getInstance();
    private static final GameOverController instance = new GameOverController();
    private final Gson gson = new Gson();

    private GameOverController() {}

    public static GameOverController getInstance() {
        return instance;
    }

    @Override
    public void openView() {
        gameOverView.open();
    }

    @Override
    public void hideView() {
        gameOverView.hideView();
    }

    @Override
    public void closeView() {
        gameOverView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return gameOverView;
    }
}
