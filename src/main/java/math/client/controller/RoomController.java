package math.client.controller;

import com.google.gson.Gson;

import math.client.common.Common;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.BaseResponse;
import math.client.dto.response.GameInvitation;
import math.client.dto.response.Room;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;

import math.client.view.FriendRequestView;
import math.client.view.Popup;
import math.client.view.RoomView;
import math.client.view.AbstractView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

@Action("/room")
@SuppressWarnings("unused")
public class RoomController implements RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final RoomView roomView = RoomView.getInstance();
    private static final RoomController instance = new RoomController();
    private final Gson gson = new Gson();

    public RoomController() {}

    public static RoomController getInstance() {
        return instance;
    }

    private void createNewRoom() {
        BaseRequest request = new BaseRequest("/api/room/new");

        connection.sendMessageToServer(request, response -> {
            Room room = gson.fromJson(response.getResult(), Room.class);

            roomView.open();
            roomView.updateView(room);

            roomView.getStartGameButton().addActionListener(event -> {
                BaseRequest newRequest = new BaseRequest("/api/game/start", Constants.NO_BODY, "/room/play-game");
                connection.sendMessageToServer(newRequest);
            });

            roomView.getInviteButton().addActionListener(event -> {
                closeView();
                Common.openViewByController(FriendController.getInstance(), instance);
            });
        });
    }

    @Action("/invite")
    public void inviteFromFriend(BaseResponse response) {
        GameInvitation gameInvitation = gson.fromJson(response.getResult(), GameInvitation.class);

        FriendRequestView.getInstance().openView(gameInvitation.getInviter());
        FriendRequestView.getInstance().getAcceptButton().addActionListener(event -> {
            BaseRequest request = new BaseRequest("/api/room/join", gameInvitation.getRoomID());

            connection.sendMessageToServer(request, res -> {
                if (res.getStatus()) {
                    openView();
                } else {
                    Popup.notify("Error", res.getMessage());
                }
            });
        });
    }

    @Action("/play-game")
    public void playGame(BaseResponse response) {
        if (Objects.nonNull(response) && response.getStatus()) {
            roomView.exit();
            Long gameTimeout = Long.parseLong(response.getResult());
            GameController.getInstance().run(gameTimeout);
        } else {
            Popup.notify("Error", response.getMessage());
        }
    }

    @Action("/users/update")
    public void updateRoomUsers(BaseResponse response) {
        Room room = gson.fromJson(response.getResult(), Room.class);

        if (Objects.nonNull(room))
            roomView.updateView(room);
    }

    private void quitRoom() {
        try {
            BaseRequest request = new BaseRequest("/api/room/exit");

            connection.sendMessageToServer(request, response ->
                Popup.notify("Notification", "Bạn đã rời khỏi phòng")
            );
        } catch (Exception ex) {
            log.error("Cannot exit room: ", ex);
        }
    }

    @Override
    public void openView() {
        roomView.open();
        createNewRoom();

        roomView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitRoom();
            }
        });
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
