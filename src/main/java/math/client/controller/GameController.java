package math.client.controller;

import com.google.gson.Gson;

import math.client.dto.request.BaseRequest;
import math.client.dto.response.BaseResponse;
import math.client.dto.response.GameResult;
import math.client.dto.response.Question;
import math.client.dto.response.User;
import math.client.router.Action;
import math.client.router.RouterMapping;
import math.client.service.utils.ConnectionUtil;
import math.client.view.AbstractView;
import math.client.view.GameOverView;
import math.client.view.GameView;

import math.client.view.Popup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Action("/game")
@SuppressWarnings("unused")
public class GameController implements RouterMapping, ViewController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final GameView gameView = GameView.getInstance();
    private static final GameOverView gameOverView = GameOverView.getInstance();
    private static final GameController instance = new GameController();
    private final Gson gson = new Gson();

    public GameController() {}

    public static GameController getInstance() {
        return instance;
    }

    @Action("/user/info/update")
    public void getUserInfoInGame(BaseResponse response) {
        if (response.getStatus()) {
            User user = gson.fromJson(response.getResult(), User.class);

            gameView.setUsernameLabel(user.getUsername());
            gameView.setScoreLabel(String.valueOf(user.getCurrentPoint()));
            gameView.setRankLabel(String.valueOf(user.getCurrentRank()));
        }
    }

    @Action("/question")
    @SuppressWarnings("unused")
    public void getQuestion(BaseResponse response) {
        Question question = gson.fromJson(response.getResult(), Question.class);
        gameView.getQuestionLabel().setText("Mảng số: " + question.getNumbers() + ". Target: " + question.getTarget());
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

    private void quitGame() {
        try {
            closeView();
            BaseRequest request = new BaseRequest("/api/room/exit");
            connection.sendMessageToServer(request, response -> Popup.notify("Thông báo", "Bạn đã thoát trò chơi"));
        } catch (Exception ex) {
            log.error("Cannot exit room: ", ex);
        }

        HomeController.getInstance().run();
    }

    private void updateGameResult(String result) {
        try {
            GameResult gameResult = gson.fromJson(result, GameResult.class);

            gameOverView.updateUserResult(gameResult.getUser());
            gameOverView.updateRankingResult(gameResult.getRanking());
        } catch (Exception e) {
            log.error("Cannot get game results", e);
        }
    }

    @Action("/finish")
    public void finishGameResult(BaseResponse response) {
        closeView();
        gameOverView.open();
        updateGameResult(response.getResult());

        gameOverView.getBackButton().addActionListener(event -> {
            gameOverView.exit();
            HomeController.getInstance().openView();
        });

        gameOverView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HomeController.getInstance().openView();
            }
        });
    }

    public void run(Long time) {
        log.info("Initialize game controller successfully");
        openView();

        gameView.startCountdownTimer(time / 1000); // Convert milliseconds to seconds
        gameView.getAnswerField().addActionListener(event -> submitAnswer());
        gameView.getQuitButton().addActionListener(event -> quitGame());
    }

    @Override
    public void openView() {
        gameView.open();
        gameView.startCountdownTimer(5 * 60L); // 5 minutes
        gameView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitGame();
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
