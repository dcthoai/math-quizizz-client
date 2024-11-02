package math.client.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import math.client.dto.request.BaseRequest;
import math.client.dto.response.Rank;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.RankView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;

@Action("/ranking")
@SuppressWarnings("unused")
public class RankingController implements Runnable, RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(RankingController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final RankingController instance = new RankingController();
    private static final RankView rankView = RankView.getInstance();
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
