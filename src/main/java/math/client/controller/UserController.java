package math.client.controller;

import math.client.service.impl.UserService;
import math.client.view.LoginView;
import math.client.view.Popup;
import math.client.view.RegisterView;

/**
 *
 * @author dcthoai
 */
public class UserController implements Runnable {

    private final LoginView loginView;
    private final RegisterView registerView;
    private final UserService userService;

    public UserController() {
        this.loginView = new LoginView();
        this.registerView = new RegisterView();
        this.userService = new UserService();
    }

    private void switchToRegisterView() {
        loginView.hideView();
        registerView.open();
    }

    public void switchToLoginView() {
        loginView.open();
        registerView.hideView();
    }

    private void register() {
        String username = registerView.getUsername();
        String password = registerView.getPassword();

        boolean isRegisterSuccess = userService.register(username.strip(), password.strip());

        if (isRegisterSuccess) {
            switchToLoginView();
            Popup.notify("Success", "Đăng ký thành công");
        } else {
            Popup.notify("Error", "Đăng ký thất bại");
        }
    }

    private void login() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();

        boolean isLoginSuccess = userService.login(username.strip(), password.strip());

        if (isLoginSuccess) {
            Popup.notify("Success", "Đăng nhập thành công");
//            exitComponent();
        } else {
            Popup.notify("Error", "Đăng nhập thất bại");

//            Popup.confirmDialog("Confirm", "Bạn có chắc chắn muốn xóa cái này không?", event -> {
//                System.out.println("ok");
//            });
        }
    }

    private void exitComponent() {
        loginView.exit();
        registerView.exit();
    }

    @Override
    public void run() {
        loginView.open();
        loginView.getLoginButton().addActionListener(event -> login());
        loginView.getRegisterButton().addActionListener(event -> switchToRegisterView());

        registerView.getBackButton().addActionListener(event -> switchToLoginView());
        registerView.getRegisterButton().addActionListener(event -> register());
    }
}
