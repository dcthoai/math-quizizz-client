package math.client.service.utils;

import math.client.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManager implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SessionManager.class);
    private static final SessionManager instance = new SessionManager();
    private User user;

    private SessionManager() {}

    public static SessionManager getInstance() {
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        this.user = new User();
        log.info("Initialize session manager successfully");
    }
}
