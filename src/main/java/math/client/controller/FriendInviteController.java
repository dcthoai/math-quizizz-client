package math.client.controller;

import com.google.gson.Gson;

import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.FriendRequestView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class FriendInviteController implements RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(FriendInviteController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final FriendRequestView friendRequestView = FriendRequestView.getInstance();
    private static final FriendInviteController instance = new FriendInviteController();
    private final Gson gson = new Gson();

    private FriendInviteController() {}

    public static FriendInviteController getInstance() {
        return instance;
    }

    @Override
    public void openView() {
        friendRequestView.open();
    }

    @Override
    public void hideView() {
        friendRequestView.hideView();
    }

    @Override
    public void closeView() {
        friendRequestView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return friendRequestView;
    }
}
