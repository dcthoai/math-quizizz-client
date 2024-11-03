package math.client.view;

class MatchHistory {
    private String playTime;
    private int rank;
    private int score;
    private int playerCount;

    // Constructor, getters và setters
    public MatchHistory(String playTime, int rank, int score, int playerCount) {
        this.playTime = playTime;
        this.rank = rank;
        this.score = score;
        this.playerCount = playerCount;
    }

    public String getPlayTime() {
        return playTime;
    }

    public int getRank() {
        return rank;
    }

    public int getScore() {
        return score;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}