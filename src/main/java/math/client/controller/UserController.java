package math.client.controller;

import com.google.gson.Gson;
import math.client.dto.request.BaseRequest;
import math.client.dto.request.UserRequest;
import math.client.service.utils.ConnectionUtil;
import math.client.view.LoginView;
import math.client.view.Popup;
import math.client.view.RegisterView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcthoai
 */
public class UserController implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final LoginView loginView;
    private final RegisterView registerView;
    private final Gson gson = new Gson();
    private static final ConnectionUtil connection = ConnectionUtil.getInstance();
    private static final UserController instance = new UserController();

    private UserController() {
        this.loginView = new LoginView();
        this.registerView = new RegisterView();
    }

    public static UserController getInstance() {
        return instance;
    }

    private void switchToRegisterView() {
        loginView.hideView();
        registerView.open();
    }

    private void switchToLoginView() {
        loginView.open();
        registerView.hideView();
    }

    private void register() {
        String username = registerView.getUsername();
        String password = registerView.getPassword();
        UserRequest user = new UserRequest(username.strip(), password.strip());
        BaseRequest request = new BaseRequest("/api/user/login", gson.toJson(user), "/register");

        connection.sendMessageToServer(request, response -> {
            boolean isRegisterSuccess = response.getStatus();

            if (isRegisterSuccess) {
                switchToLoginView();
                Popup.notify("Success", response.getMessage());
            } else {
                Popup.notify("Error", response.getMessage());
            }
        });
    }

    private void login() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        UserRequest user = new UserRequest(username.strip(), password.strip());
        BaseRequest request = new BaseRequest("/api/user/login", gson.toJson(user), "/register");

        connection.sendMessageToServer(request, response -> {
            boolean isLoginSuccess = response.getStatus();

            if (isLoginSuccess) {
                // Switch to main view here

                Popup.notify("Success", response.getMessage());
                exitComponent();
            } else {
                Popup.notify("Error", response.getMessage());
            }
        });
    }

    private void exitComponent() {
        loginView.exit();
        registerView.exit();
    }

    @Override
    public void run() {
        log.info("Initialize application view successfully");
        loginView.open();
        loginView.getLoginButton().addActionListener(event -> login());
        loginView.getRegisterButton().addActionListener(event -> switchToRegisterView());

        registerView.getBackButton().addActionListener(event -> switchToLoginView());
        registerView.getRegisterButton().addActionListener(event -> register());
    }
}
