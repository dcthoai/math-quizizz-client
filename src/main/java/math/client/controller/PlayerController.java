package math.client.controller;

import com.google.gson.Gson;

import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.PlayerInfoView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class PlayerController implements RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final PlayerInfoView playerInfoView = PlayerInfoView.getInstance();
    private static final PlayerController instance = new PlayerController();
    private final Gson gson = new Gson();

    private PlayerController() {}

    public static PlayerController getInstance() {
        return instance;
    }

    @Override
    public void openView() {
        playerInfoView.open();
    }

    @Override
    public void hideView() {
        playerInfoView.hideView();
    }

    @Override
    public void closeView() {
        playerInfoView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return playerInfoView;
    }
}
