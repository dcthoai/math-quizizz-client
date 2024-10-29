package math.client.controller;

import com.google.gson.Gson;

import math.client.common.Common;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.User;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;
import math.client.view.AbstractView;
import math.client.view.HomeGameView;
import math.client.view.Popup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeController implements Runnable, RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private static final SessionManager sessionManager = SessionManager.getInstance();
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final HomeGameView homeView = HomeGameView.getInstance();
    private static final HomeController instance = new HomeController();
    private final Gson gson = new Gson();

    private HomeController() {}

    public static HomeController getInstance() {
        return instance;
    }

    private void createRoom() {
        homeView.hideView();
        Common.openViewByController(RoomController.getInstance(), instance);
    }

    private void findRoom() {
        homeView.hideView();
        Common.openViewByController(SearchRoomController.getInstance(), instance);
    }

    private void viewFriendList() {
        homeView.hideView();
        Common.openViewByController(FriendController.getInstance(), instance);
    }

    private void viewRanking() {
        homeView.hideView();
        Common.openViewByController(RankingController.getInstance(), instance);
    }

    private void getUserInfo() {
        BaseRequest request = new BaseRequest("/api/user/info", Constants.NO_BODY, Constants.NO_ACTION);

        connection.sendMessageToServer(request, response -> {
            if (response.getStatus()) {
                User user = gson.fromJson(response.getResult(), User.class);
                homeView.setUserInfo(user);
            } else {
                Popup.notify("Error", response.getMessage());
            }
        });
    }

    private void logout() {
        BaseRequest request = new BaseRequest("/api/user/logout", Constants.NO_BODY, Constants.NO_ACTION);

        connection.sendMessageToServer(request, response -> {
            boolean isLogoutSuccess = response.getStatus();

            if (isLogoutSuccess) {
                UserController.getInstance().run();
                Popup.notify("Success", response.getMessage());
                closeView();
            } else {
                Popup.notify("Error", response.getMessage());
            }
        });
    }

    @Override
    public void openView() {
        homeView.open();
    }

    @Override
    public void hideView() {
        homeView.hideView();
    }

    @Override
    public void closeView() {
        homeView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return homeView;
    }

    @Override
    public void run() {
        log.info("Initialize home controller successfully");
        homeView.open();
        getUserInfo();

        homeView.getCreateRoomButton().addActionListener(event -> createRoom());
        homeView.getFindRoomButton().addActionListener(event -> findRoom());
        homeView.getRankingButton().addActionListener(event -> viewRanking());
        homeView.getFriendListButton().addActionListener(event -> viewFriendList());
        homeView.getLogoutButton().addActionListener(event -> logout());
    }
}
