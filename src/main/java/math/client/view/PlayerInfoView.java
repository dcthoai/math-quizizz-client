package math.client.view;

import math.client.model.User;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PlayerInfoView extends AbstractView {
    private final String nickname;
    private final int score;
    private final int rank;
    private final int totalGames;
    private final double winRate;

    private final boolean isFriend;
    private static User user;


    public PlayerInfoView(String nickname, int score, int rank, int totalGames, double winRate, boolean isFriend) {
        super("Player Information", 400, 300);
        this.nickname = nickname;
        this.score = score;
        this.rank = rank;
        this.totalGames = totalGames;
        this.winRate = winRate;
        this.isFriend = isFriend;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            ButtonStyle addFriendButton = new ButtonStyle("Kết bạn", 80, 30);
            addFriendButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Kết bạn với " + nickname);
            });
            panel.add(addFriendButton, gbc);
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Roboto", Font.PLAIN, 15));
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerInfoView view = new PlayerInfoView("Player1", 1500, 3, 25, 0.72, true);
            view.setVisible(true);
        });
    }
}
