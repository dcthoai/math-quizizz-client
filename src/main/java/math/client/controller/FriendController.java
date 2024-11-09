package math.client.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.FriendRequest;
import math.client.dto.response.User;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.FriendListView;

import math.client.view.FriendRequestComponent;
import math.client.view.FriendRequestView;
import math.client.view.PlayerInfoView;
import math.client.view.Popup;
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
    private static final PlayerInfoView playerInfoView = PlayerInfoView.getInstance();
    private static final FriendRequestView friendRequestView = FriendRequestView.getInstance();
    private static final FriendController instance = new FriendController();
    private final Gson gson = new Gson();

    private FriendController() {}

    public static FriendController getInstance() {
        return instance;
    }

    private void getFriendRequestPending() {
        BaseRequest request = new BaseRequest("/api/friendship/pending");

        connection.sendMessageToServer(request, response ->{
            try {
                Type listFriendRequestType = new TypeToken<List<FriendRequest>>() {}.getType();
                List<FriendRequest> friendRequests = gson.fromJson(response.getResult(), listFriendRequestType);
                updateFriendRequestView(friendRequests);
            } catch (Exception e) {
                log.error("Cannot get friend request with status: pending", e);
            }
        });
    }

    private void getFriendShip() {
        BaseRequest request = new BaseRequest("/api/friendship/all");

        connection.sendMessageToServer(request, response -> {
            try {
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
                            findUser(friend.strip());
                        }
                    }
                });
            } catch (Exception e) {
                log.error("Cannot get friendships", e);
            }
        });
    }

    private void findUser(String username) {
        BaseRequest request = new BaseRequest("/api/user/find", username);

        connection.sendMessageToServer(request, response -> {
            try {
                User user = gson.fromJson(response.getResult(), User.class);
                openUserInfoView(user);
            } catch (Exception e) {
                Popup.notify("Lỗi", "Không tìm được thông tin về người chơi này");
                log.error("Failed to find user: {}", username, e);
            }
        });
    }

    private void openUserInfoView(User user) {
        playerInfoView.open();
        playerInfoView.setUserInfo(user);
        playerInfoView.getStatusButton(Constants.TYPE_FRIENDSHIP)
                .addActionListener(event -> inviteFriendToGame(user));
    }

    private void updateFriendRequestView(List<FriendRequest> friendRequests) {
        friendListView.updateFriendRequests(friendRequests);
        listenerForFriendRequestList();
    }

    private void listenerForFriendRequestList() {
        List<FriendRequestComponent> requestComponents = friendListView.getRequestComponents();

        requestComponents.forEach(requestComponent -> {
            Integer requestID = requestComponent.getFriendRequestID();

            requestComponent.getAcceptButton().addActionListener(event -> {
                // Accept friend request
            });

            requestComponent.getRejectButton().addActionListener(event -> {
                // Refuse friend request
            });
        });
    }

    private void inviteFriendToGame(User user) {
        BaseRequest request = new BaseRequest("/api/room/invite", user.getUsername(), "/room/invite");
        connection.sendMessageToServer(request);
    }

    @Override
    public void openView() {
        friendListView.open();
        getFriendShip();
        getFriendRequestPending();
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
