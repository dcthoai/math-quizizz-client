package math.client.view;

import math.client.model.User;

import javax.swing.*;
import java.awt.*;

public class HomeGameView extends AbstractView {

    private static User user;
    private ButtonStyle quickPlayButton;
    private ButtonStyle joinRoomButton;
    private ButtonStyle createRoomButton;
    private ButtonStyle findRoomButton;
    private ButtonStyle friendListButton;
    private ButtonStyle leaderboardButton;
    private ButtonStyle logoutButton;
    private ButtonStyle exitGameButton;

    public HomeGameView(String username, int gamesPlayed, double winRate, int score, int rank) {
        super("Math Quzizz", 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        generateView(username, gamesPlayed, winRate, score, rank);
    }

    private void generateView(String username, int gamesPlayed, double winRate, int score, int rank) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Game title
        JPanel titlePanel = new JPanel();
        JLabel gameTitle = new JLabel("Math Quzizz Game", SwingConstants.CENTER);
        gameTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        titlePanel.add(gameTitle);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(titlePanel, gbc);

        JPanel playerInfoPanel = createPlayerInfoPanel(username, gamesPlayed, winRate, score, rank);
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(playerInfoPanel, gbc);

        JPanel buttonPanel = createButtonPanel();
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(buttonPanel, gbc);

        add(panel);

        pack();
    }

    private JPanel createPlayerInfoPanel(String username, int gamesPlayed, double winRate, int score, int rank) {
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Nickname: " + username);
        JLabel gamesPlayedLabel = new JLabel("Số ván chơi: " + gamesPlayed);
        JLabel winRateLabel = new JLabel("Tỉ lệ thắng: " + String.format("%.2f", winRate) + "%");
        JLabel scoreLabel = new JLabel("Điểm: " + score);
        JLabel rankLabel = new JLabel("Xếp hạng: " + rank);

        usernameLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        gamesPlayedLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        winRateLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        scoreLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        rankLabel.setFont(new Font("Roboto", Font.PLAIN, 16));

        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        gamesPlayedLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        winRateLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        rankLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        playerInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerInfoPanel.add(usernameLabel);
        playerInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerInfoPanel.add(gamesPlayedLabel);
        playerInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerInfoPanel.add(winRateLabel);
        playerInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerInfoPanel.add(scoreLabel);
        playerInfoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerInfoPanel.add(rankLabel);

        return playerInfoPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Tạo các nút
        quickPlayButton = new ButtonStyle("Chơi Nhanh", 150, 30);
        joinRoomButton = new ButtonStyle("Vào Phòng", 150, 30);
        createRoomButton = new ButtonStyle("Tạo Phòng", 150, 30);
        findRoomButton = new ButtonStyle("Tìm Phòng", 150, 30);
        friendListButton = new ButtonStyle("Danh Sách Bạn Bè", 150, 30);
        leaderboardButton = new ButtonStyle("Bảng Xếp Hạng", 150, 30);
        logoutButton = new ButtonStyle("Đăng Xuất", 150, 30);
        exitGameButton = new ButtonStyle("Thoát Game", 150, 30);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 15, 10, 15);

        //quickPlay, joinRoom, createRoom
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(quickPlayButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(joinRoomButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(createRoomButton, gbc);

        //searchRoom, listFriend, leaderBoard
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(findRoomButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(friendListButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(leaderboardButton, gbc);


        //Logout, exitGame
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        buttonPanel.add(logoutButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(exitGameButton, gbc);

        return buttonPanel;
    }


    public JButton getQuickPlayButton() {
        return quickPlayButton;
    }

    public JButton getJoinRoomButton() {
        return joinRoomButton;
    }

    public JButton getCreateRoomButton() {
        return createRoomButton;
    }

    public JButton getFindRoomButton() {
        return findRoomButton;
    }

    public JButton getFriendListButton() {
        return friendListButton;
    }

    public JButton getLeaderboardButton() {
        return leaderboardButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getExitGameButton() {
        return exitGameButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeGameView homeView = new HomeGameView("Player1", 25, 68.5, 1230, 10);
            homeView.setVisible(true);
        });
    }
}
