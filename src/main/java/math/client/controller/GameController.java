package math.client.controller;

import com.google.gson.Gson;
import math.client.common.Constants;
import math.client.dto.request.BaseRequest;
import math.client.dto.response.BaseResponse;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;
import math.client.view.GameView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Action("/game")
public class GameController implements Runnable, RouterMapping {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    private static final SessionManager sessionManager = SessionManager.getInstance();
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final GameController instance = new GameController();
    private static final GameView gameView = GameView.getInstance();
    private final Gson gson = new Gson();

    public GameController() {}

    public static GameController getInstance() {
        return instance;
    }

    @Action("/question")
    public void getQuestion(BaseResponse<?> response) {
        System.out.println(response.getMessage());
    }

    @Override
    public void run() {
        log.info("Initialize game controller successfully");
        gameView.open();

        BaseRequest request = new BaseRequest("/api/game/start", Constants.NO_BODY, "/game/question");

        connection.sendMessageToServer(request);
    }
}
