package math.client.controller;

import math.client.service.impl.UserServiceImpl;
import math.client.view.LoginFrame;

/**
 *
 * @author dctho
 */
public class UserController {
    private LoginFrame loginFrame;
    private UserServiceImpl userService;

    public UserController() {
        loginFrame = new LoginFrame(this);
        loginFrame.display();
        userService = new UserServiceImpl();
    }
    
    public void login(String username, String password) {
        boolean success = userService.login(username, password);
        
        if (success)
            System.out.println("Login success");
        else
            System.out.println("Login failed");
    }
}
