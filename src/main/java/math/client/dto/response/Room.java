package math.client.dto.response;

import java.util.List;

@SuppressWarnings("unused")
public class Room {

    private String roomID;
    private Boolean isPlayingGame;
    private List<String> users;

    public Room() {}

    public Room(String roomID, Boolean isPlayingGame, List<String> users) {
        this.roomID = roomID;
        this.isPlayingGame = isPlayingGame;
        this.users = users;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Boolean getPlayingGame() {
        return isPlayingGame;
    }

    public void setPlayingGame(Boolean playingGame) {
        isPlayingGame = playingGame;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
