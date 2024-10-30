package math.client.controller;

import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.User;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.RankView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Action("/ranking")
public class RankingController implements Runnable, RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(RankingController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final RankingController instance = new RankingController();
    private static final RankView rankView = RankView.getInstance();

    private RankingController() {}

    public static RankingController getInstance() {
        return instance;
    }

    private void getRanking() {
        BaseRequest request = new BaseRequest("/api/ranking");
//        connection.sendMessageToServer(request, response -> setRankingView((List<User>) response.getResult()));
    }

    private void setRankingView(List<User> users) {
        rankView.setDataToTable(users);
    }

    @Override
    public void run() {
        log.info("Initialize ranking controller successfully");
        rankView.open();
        getRanking();
    }

    @Override
    public void openView() {
        rankView.open();
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
