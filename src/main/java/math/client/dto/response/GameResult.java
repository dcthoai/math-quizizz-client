package math.client.dto.response;

import java.util.Map;

@SuppressWarnings("unused")
public class GameResult {

    private User user;
    private Map<String, Integer> ranking;

    public GameResult() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Integer> getRanking() {
        return ranking;
    }

    public void setRanking(Map<String, Integer> ranking) {
        this.ranking = ranking;
    }
}
