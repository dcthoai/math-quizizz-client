package math.client.controller;

import com.google.gson.Gson;
import math.client.dto.request.BaseRequest;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;
import math.client.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Action("/room")
public class RoomController implements RouterMapping {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);
    private static final SessionManager sessionManager = SessionManager.getInstance();
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final RoomController instance = new RoomController();
    private static final SearchRoomView searchRoomView = SearchRoomView.getInstance();
    private static final RoomListView roomListView = RoomListView.getInstance();
    private static final RoomView gameView = RoomView.getInstance();
    private static final FriendListView friendListView = FriendListView.getInstance();

    private final Gson gson = new Gson();

    private RoomController() {}

    public static RoomController getInstance() {
        return instance;
    }

    public void openSearchRoomView() {
        searchRoomView.open();

        searchRoomView.getSearchRoomButton().addActionListener(event -> searchRoom());
        searchRoomView.getBackButton().addActionListener(event -> searchRoomView.exit());
    }

    public void openNewRoom() {
        gameView.open();

        gameView.getBtnInvite().addActionListener(event -> openFriendListView());
        gameView.getBtnLeaveRoom().addActionListener(event -> {
            gameView.exit();
            HomeGameView.getInstance().open();
        });
    }

    public void openRoomListView() {
        roomListView.open();
    }

    private void searchRoom() {
        String roomID = searchRoomView.getRoomCode().strip();
        BaseRequest request = new BaseRequest("/api/room/find", roomID);

        connection.sendMessageToServer(request, response -> {
            if (response.getStatus()) {

            } else {
                Popup.notify("Error", response.getMessage());
            }
        });
    }

    private void createRoom() {

    }

    private void openFriendListView() {
        friendListView.open();
    }
}
