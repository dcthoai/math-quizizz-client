package math.client.controller;

import com.google.gson.Gson;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.Room;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;
import math.client.view.*;
import math.client.view.Popup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RoomController implements RouterMapping {

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final SessionManager sessionManager = SessionManager.getInstance();
    private static final FriendListView friendListView = FriendListView.getInstance();
    private static final SearchRoomView searchRoomView = SearchRoomView.getInstance();
    private static final RoomListView roomListView = RoomListView.getInstance();
    private static final RoomView roomView = RoomView.getInstance();
    private static final RoomController instance = new RoomController();

    private final Gson gson = new Gson();

    private RoomController() {
        roomView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HomeController.getInstance().openView();
            }
        });
    }

    public static RoomController getInstance() {
        return instance;
    }

    public void openSearchRoomView() {
        searchRoomView.open();

        searchRoomView.getSearchRoomButton().addActionListener(event -> searchRoom());
        searchRoomView.getBackButton().addActionListener(event -> searchRoomView.exit());
    }

    public void openRoomListView() {
        roomListView.open();

        roomListView.getRoomList().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable roomTable = (JTable) evt.getSource();
                int row = roomTable.rowAtPoint(evt.getPoint());

                if (row >= 0) {
                    String roomID = (String) roomTable.getValueAt(row, 0);
                    System.out.println("Click on " + roomID);
                }
            }
        });
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

    private void openFriendListView() {
        friendListView.open();
    }

    public void createNewRoom() {
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

            });
        });
    }
}
