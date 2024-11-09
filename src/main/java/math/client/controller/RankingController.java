package math.client.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.JTable;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.Rank;
import math.client.dto.response.User;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.PlayerInfoView;
import math.client.view.Popup;
import math.client.view.RankView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Type;
import java.util.List;

@Action("/ranking")
@SuppressWarnings("unused")
public class RankingController implements Runnable, RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(RankingController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final PlayerInfoView playerInfoView = PlayerInfoView.getInstance();
    private static final RankView rankView = RankView.getInstance();
    private static final RankingController instance = new RankingController();
    private final Gson gson = new Gson();

    private RankingController() {}

    public static RankingController getInstance() {
        return instance;
    }

    private void getRanking() {
        BaseRequest request = new BaseRequest("/api/ranking/all");

        connection.sendMessageToServer(request, response -> {
            Type ranksType = new TypeToken<List<Rank>>() {}.getType();
            List<Rank> ranks = gson.fromJson(response.getResult(), ranksType);

            setRankingView(ranks);
        });
    }

    private void setRankingView(List<Rank> ranks) {
        rankView.setDataToTable(ranks);
        rankView.getTable().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                JTable rankingTable = (JTable) evt.getSource();
                int row = rankingTable.rowAtPoint(evt.getPoint());

                if (row >= 0) {
                    String playerName = (String) rankingTable.getValueAt(row, 1);
                    getPlayerInfo(playerName.strip());
                }
            }
        });
    }

    private void getPlayerInfo(String username) {
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
        playerInfoView.getStatusButton(Constants.TYPE_PLAYER)
                .addActionListener(event -> sendFriendRequest(user));
    }

    private void sendFriendRequest(User user) {
        BaseRequest request = new BaseRequest("/api/friendship/add", String.valueOf(user.getID()));

        connection.sendMessageToServer(request, response -> {
            if (response.getStatus())
                Popup.notify("Notify", "Gửi lời mời kết bạn thành công!");
            else
                Popup.notify("Error", response.getMessage());
        });
    }

    @Override
    public void run() {
        log.info("Initialize ranking controller successfully");
        rankView.open();
        getRanking();
    }

    @Override
    public void openView() {
        run();
    }

    @Override
    public void hideView() {
        rankView.hideView();
    }

    @Override
    public void closeView() {
        rankView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return rankView;
    }
}
