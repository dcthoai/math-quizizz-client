package math.client.controller;

import com.google.gson.Gson;

import math.client.common.Common;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.Room;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;

import math.client.view.RoomView;
import math.client.view.AbstractView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomController implements RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final SessionManager sessionManager = SessionManager.getInstance();
    private static final RoomView roomView = RoomView.getInstance();
    private static final RoomController instance = new RoomController();
    private final Gson gson = new Gson();

    private RoomController() {}

    public static RoomController getInstance() {
        return instance;
    }

    private void createNewRoom() {
        BaseRequest request = new BaseRequest("/api/room/new", Constants.NO_BODY, Constants.NO_ACTION);

        connection.sendMessageToServer(request, response -> {
            Room room = gson.fromJson(response.getResult(), Room.class);

            roomView.open();
            roomView.updateView(room);

            roomView.getStartGameButton().addActionListener(event -> {
                roomView.exit();
                GameController.getInstance().run();
            });

            roomView.getInviteButton().addActionListener(event -> {
                Common.openViewByController(FriendInviteController.getInstance(), instance);
            });
        });
    }

    @Override
    public void openView() {
        roomView.open();
        createNewRoom();
    }

    @Override
    public void hideView() {
        roomView.hideView();
    }

    @Override
    public void closeView() {
        roomView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return roomView;
    }
}
