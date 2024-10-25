package math.client.view;

import math.client.model.User;

import javax.swing.*;
import java.awt.*;

public class HomeGameView extends AbstractView {

    private JLabel usernameLabel;
    private JLabel gamesPlayedLabel;
    private JLabel winRateLabel;
    private JLabel scoreLabel;
    private JLabel rankLabel;
    private ButtonStyle quickPlayButton;
    private ButtonStyle userInfoButton;
    private ButtonStyle createRoomButton;
    private ButtonStyle findRoomButton;
    private ButtonStyle friendListButton;
    private ButtonStyle leaderboardButton;
    private ButtonStyle logoutButton;
    private ButtonStyle exitGameButton;
    private static final HomeGameView instance = new HomeGameView();

    public static HomeGameView getInstance() {
        return instance;
    }

    private HomeGameView() {
        super("Math Quizzes Game", 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    private void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Game title
        JPanel titlePanel = new JPanel();
        JLabel gameTitle = new JLabel("Math Quizzes Game", SwingConstants.CENTER);
        gameTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        titlePanel.add(gameTitle);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(titlePanel, gbc);

        JPanel playerInfoPanel = createPlayerInfoPanel();
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

    private JPanel createPlayerInfoPanel() {
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));

        usernameLabel = new JLabel("Nickname: Chưa có dữ liệu");
        gamesPlayedLabel = new JLabel("Số ván chơi: Chưa có dữ liệu");
        winRateLabel = new JLabel("Tỉ lệ thắng: Chưa có dữ liệu");
        scoreLabel = new JLabel("Điểm: Chưa có dữ liệu");
        rankLabel = new JLabel("Xếp hạng: Chưa có dữ liệu");

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
        userInfoButton = new ButtonStyle("Tài khoản", 150, 30);
        createRoomButton = new ButtonStyle("Tạo Phòng", 150, 30);
        findRoomButton = new ButtonStyle("Tìm Phòng", 150, 30);
        friendListButton = new ButtonStyle("Danh Sách Bạn Bè", 150, 30);
        leaderboardButton = new ButtonStyle("Bảng Xếp Hạng", 150, 30);
        logoutButton = new ButtonStyle("Đăng Xuất", 150, 30);
        exitGameButton = new ButtonStyle("Thoát Game", 150, 30);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 15, 10, 15);

        //quickPlay, findRoom, createRoom
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(quickPlayButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(findRoomButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(createRoomButton, gbc);

        //searchRoom, listFriend, leaderBoard
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(friendListButton, gbc);
        gbc.gridx = 1;
        buttonPanel.add(leaderboardButton, gbc);
        gbc.gridx = 2;
        buttonPanel.add(userInfoButton, gbc);

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

    public JButton getUserInfoButton() {
        return userInfoButton;
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

    public void setUserInfo(User user) {
        usernameLabel.setText("Nickname: " + user.getUsername());
        gamesPlayedLabel.setText("Số ván chơi: " + user.getGamesPlayed());
        winRateLabel.setText("Tỉ lệ thắng: " + String.format("%.2f", user.getWinRate()) + "%");
        scoreLabel.setText("Điểm: " + user.getScore());
        rankLabel.setText("Xếp hạng: " + user.getRank());
    }
}
