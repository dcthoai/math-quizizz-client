package math.client.model;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private final String roomID;
    private List<User> players = new ArrayList<>();

    public Room(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomID() {
        return roomID;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void updateRoomPlayers(List<User> users) {
        this.players = users;
    }
}
