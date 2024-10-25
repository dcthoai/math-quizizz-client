package math.client.controller;

import com.google.gson.Gson;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;
import math.client.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Action("/home")
public class HomeController implements Runnable, RouterMapping {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private static final SessionManager sessionManager = SessionManager.getInstance();
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final HomeController instance = new HomeController();
    private static final HomeGameView homeView = HomeGameView.getInstance();
    private final Gson gson = new Gson();

    private HomeController() {}

    public static HomeController getInstance() {
        return instance;
    }

    private void quickPlay() {
//        RoomController.getInstance().openRoomListView();
        GameController.getInstance().run();
    }

    private void userInfo() {
        PlayerInfoView.getInstance().run();
    }

    private void createRoom() {
        homeView.hideView();
        RoomController.getInstance().openNewRoom();
    }

    private void findRoom() {
        RoomController.getInstance().openSearchRoomView();
    }

    private void viewFriendList() {
        FriendListView.getInstance().open();
    }

    private void viewRanking() {
        RankingController.getInstance().run();
    }

    private void logout() {
        BaseRequest request = new BaseRequest("/api/user/logout", Constants.NO_BODY, "/logout");

        connection.sendMessageToServer(request, response -> {
            boolean isLogoutSuccess = response.getStatus();

            if (isLogoutSuccess) {
                UserController.getInstance().run();
                Popup.notify("Success", response.getMessage());
                exitComponent();
            } else {
                Popup.notify("Error", response.getMessage());
            }
        });
    }

    private void exitGame() {
        exitComponent();
        System.exit(0); // Shutdown application (No error)
    }

    private void exitComponent() {
        homeView.exit();
    }

    @Override
    public void run() {
        log.info("Initialize home controller successfully");
        homeView.open();

        homeView.getQuickPlayButton().addActionListener(event -> quickPlay());
        homeView.getUserInfoButton().addActionListener(event -> userInfo());
        homeView.getCreateRoomButton().addActionListener(event -> createRoom());
        homeView.getFindRoomButton().addActionListener(event -> findRoom());
        homeView.getFriendListButton().addActionListener(event -> viewFriendList());
        homeView.getLeaderboardButton().addActionListener(event -> viewRanking());
        homeView.getLogoutButton().addActionListener(event -> logout());
        homeView.getExitGameButton().addActionListener(event -> exitGame());
    }
}
