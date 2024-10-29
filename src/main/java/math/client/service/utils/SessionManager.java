package math.client.service.utils;

import math.client.dto.response.Room;
import math.client.dto.response.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * A class to manage user information and other game information during a login session
 * @author dcthoai
 */
public class SessionManager implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SessionManager.class);
    private static final SessionManager instance = new SessionManager();
    private static final Map<String, Room> rooms = new HashMap<>();
    private User user = new User();

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

    public List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    public void addRoom(Room room) {
        rooms.put(room.getRoomID(), room);
    }

    @Override
    public void run() {
        this.user = new User();
        log.info("Initialize session manager successfully");
    }
}
