package math.client.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import math.client.connection.ConnectionUtils;
import math.client.controller.UserController;
import math.client.model.User;
import math.client.service.UserService;

/**
 *
 * @author dctho
 */
public class UserServiceImpl implements UserService {
    
    private ConnectionUtils connection;
    private static final Logger log = Logger.getLogger(UserController.class.getName());

    public UserServiceImpl() {
        try {
            connection = new ConnectionUtils();
        } catch (Exception e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "Failed to create a connection. " + e.getMessage()); // ERROR level log
        }
    }

    @Override
    public boolean login(String username, String password) {
        try {
            User user = new User(username, password);
            connection.sendData(user);

            Object response = connection.receiveData();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean logout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
