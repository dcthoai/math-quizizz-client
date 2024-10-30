package math.client.controller;

import com.google.gson.Gson;

import math.client.dto.request.BaseRequest;
import math.client.dto.response.BaseResponse;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.service.utils.SessionManager;
import math.client.view.AbstractView;
import math.client.view.GameView;

import math.client.view.Popup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Action("/game")
@SuppressWarnings("unused")
public class GameController implements Runnable, RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    private static final SessionManager sessionManager = SessionManager.getInstance();
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final GameView gameView = GameView.getInstance();
    private static final GameController instance = new GameController();
    private final Gson gson = new Gson();

    public GameController() {}

    public static GameController getInstance() {
        return instance;
    }

    @Action("/question")
    @SuppressWarnings("unused")
    public void getQuestion(BaseResponse response) {
        gameView.getQuestionLabel().setText(response.getResult());
    }

    @Action("/invalid-answer")
    public void invalidAnswer(BaseResponse response) {
        Popup.notify("Error", response.getMessage());
    }

    private void submitAnswer() {
        String answer = gameView.getAnswerField().getText();
        BaseRequest request = new BaseRequest("/api/game/answer", answer);

        connection.sendMessageToServer(request, response -> {
            if (response.getStatus()) {
                gameView.getAnswerField().setText("You win");
            } else {
                gameView.getAnswerField().setText("You lost");
            }
        });
    }

    @Override
    public void run() {
        log.info("Initialize game controller successfully");
        openView();
    }

    @Override
    public void openView() {
        gameView.open();
        gameView.getSubmitButton().addActionListener(event -> submitAnswer());

        gameView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    BaseRequest request = new BaseRequest("/api/room/exit");
                    connection.sendMessageToServer(request, response -> Popup.notify("Thông báo", "Bạn đã thoát trò chơi"));
                } catch (Exception ex) {
                    log.error("Cannot exit room: ", ex);
                }

                HomeController.getInstance().run();
            }
        });
    }

    @Override
    public void hideView() {
        gameView.hideView();
    }

    @Override
    public void closeView() {
        gameView.exit();
    }

    @Override
    public AbstractView getMainView() {
        return gameView;
    }
}
