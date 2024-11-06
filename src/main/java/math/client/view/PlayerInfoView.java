package math.client.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import math.client.dto.response.User;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;

public class PlayerInfoView extends AbstractView {
    private String nickname = "";
    private int score = 0;
    private int rank = 0;
    private int totalGames = 0;
    private double winRate = 0;
    private boolean isFriend = false;
    private static final PlayerInfoView instance = new PlayerInfoView();

    public static PlayerInfoView getInstance() {
        return instance;
    }

    private PlayerInfoView() {
        super("Thông tin người chơi", 400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        generateView();
    }

    private void generateView() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setBackground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel cho tiêu đề
        JPanel labelPanel = createLabelPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 0, 10);
        mainPanel.add(labelPanel, gbc);

        // Panel cho thông tin người chơi
        JPanel infoPanel = createInfoPanel();
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(infoPanel, gbc);

        add(mainPanel);
        pack();
    }

    private JPanel createLabelPanel() {
        JPanel labelPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        labelPanel.setBackground(Color.LIGHT_GRAY);
        labelPanel.setPreferredSize(new Dimension(400, 50));

        JLabel playerInfoLabel = new JLabel("Thông tin người chơi");
        playerInfoLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        playerInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        labelPanel.add(playerInfoLabel, gbc);

        return labelPanel;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        infoPanel.setBorder(new LineBorder(Color.BLACK, 1));
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addInfoRow(infoPanel, gbc, "Nickname:", nickname);
        addInfoRow(infoPanel, gbc, "Số trận đã chơi:", String.valueOf(totalGames));
        addInfoRow(infoPanel, gbc, "Tỉ lệ thắng:", String.format("%.2f%%", winRate * 100));
        addInfoRow(infoPanel, gbc, "Điểm:", String.valueOf(score));
        addInfoRow(infoPanel, gbc, "Rank:", String.valueOf(rank));
        addFriendStatus(infoPanel, gbc);

        return infoPanel;
    }

    private void addInfoRow(JPanel panel, GridBagConstraints gbc, String label, String value) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(createLabel(value), gbc);
    }

    private void addFriendStatus(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Trạng thái:"), gbc);
        gbc.gridx = 1;

        if (isFriend) {
            panel.add(createLabel("Bạn bè"), gbc);
        } else {
            ButtonStyle addFriendButton = new ButtonStyle("Kết bạn", 150, 30);
            addFriendButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Kết bạn với " + nickname)
            );
            panel.add(addFriendButton, gbc);
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Roboto", Font.PLAIN, 15));
        return label;
    }

    public void setUserInfo(User user) {
        this.nickname = user.getUsername();
        this.score = user.getScore();
        this.rank = user.getRank();
        this.totalGames = user.getGamesPlayed();
        this.winRate = user.getWinRate();
    }

    public void setFriendStatus(boolean status) {
        this.isFriend = status;
    }
}
