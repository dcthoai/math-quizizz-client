package math.client.dto.response;

@SuppressWarnings("unused")
public class GameHistory {

    private String finishedTime;
    private Integer score, userRank, totalPlayer;

    public GameHistory() {}

    public String getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    public Integer getTotalPlayer() {
        return totalPlayer;
    }

    public void setTotalPlayer(Integer totalPlayer) {
        this.totalPlayer = totalPlayer;
    }
}
