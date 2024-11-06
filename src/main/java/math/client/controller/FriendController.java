package math.client.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import math.client.dto.request.BaseRequest;
import math.client.dto.response.User;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.FriendListView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("unused")
public class FriendController implements RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(FriendController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final FriendListView friendListView = FriendListView.getInstance();
    private static final FriendController instance = new FriendController();
    private final Gson gson = new Gson();

    private FriendController() {}

    public static FriendController getInstance() {
        return instance;
    }

    private void getFriendShip() {
        BaseRequest request = new BaseRequest("/api/friendship/all");

        connection.sendMessageToServer(request, response -> {
            Type friendShipType = new TypeToken<List<User>>() {}.getType();
            List<User> friendShips = gson.fromJson(response.getResult(), friendShipType);

            friendListView.updateListFriend(friendShips);
            friendListView.getFriendTable().addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent evt) {
                    JTable friendTable = (JTable) evt.getSource();
                    int row = friendTable.rowAtPoint(evt.getPoint());

                    if (row >= 0) {
                        String friend = (String) friendTable.getValueAt(row, 0);
                        System.out.println("Click on " + friend);
                    }
                }
            });
        });
    }

    @Override
    public void openView() {
        friendListView.open();
        getFriendShip();
    }

    @Override
    public void hideView() {
        friendListView.hideView();
    }

    @Override
    public void closeView() {
        friendListView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return friendListView;
    }
}
