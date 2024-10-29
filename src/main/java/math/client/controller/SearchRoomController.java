package math.client.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.Room;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;
import math.client.view.AbstractView;
import math.client.view.Popup;
import math.client.view.RoomListView;
import math.client.view.SearchRoomView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class SearchRoomController implements RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(SearchRoomController.class);
    private static final SessionManager sessionManager = SessionManager.getInstance();
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final SearchRoomView searchRoomView = SearchRoomView.getInstance();
    private static final RoomListView roomListView = RoomListView.getInstance();
    private static final SearchRoomController instance = new SearchRoomController();
    private final Gson gson = new Gson();

    private SearchRoomController() {}

    public static SearchRoomController getInstance() {
        return instance;
    }

    public void openSearchRoomView() {
        searchRoomView.open();
        searchRoomView.getSearchRoomButton().addActionListener(event -> searchRoom());
        searchRoomView.getBackButton().addActionListener(event -> searchRoomView.exit());
    }

    private void searchRoom() {
        String roomID = searchRoomView.getRoomCode().strip();
        BaseRequest request = new BaseRequest("/api/room/find", roomID);

        connection.sendMessageToServer(request, response -> {
            if (response.getStatus()) {
                System.out.println("Get room successful: " + response.getResult());
            } else {
                Popup.notify("Error", response.getMessage());
            }
        });
    }

    @Override
    public void openView() {
        roomListView.open();
        roomListView.getSearchRoomButton().addActionListener(event -> openSearchRoomView());

        BaseRequest request = new BaseRequest("/api/room/list", Constants.NO_BODY, Constants.NO_ACTION);

        connection.sendMessageToServer(request, response -> {
            Type roomListType = new TypeToken<List<Room>>() {}.getType();
            List<Room> rooms = gson.fromJson(response.getResult(), roomListType);

            if (Objects.nonNull(rooms) && !rooms.isEmpty()) {
                roomListView.updateListRooms(rooms);

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
        });
    }

    @Override
    public void hideView() {
        roomListView.hideView();
    }

    @Override
    public void closeView() {
        roomListView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return roomListView;
    }
}