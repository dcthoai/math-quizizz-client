package math.client.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import math.client.dto.response.User;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class PlayerInfoView extends AbstractView {

    private JLabel nicknameLabel;
    private JLabel scoreLabel;
    private JLabel rankLabel;
    private JLabel totalGamesLabel;
    private JLabel winRateLabel;
    private JPanel infoPanel;
    private JButton statusFriendButton;
    private static final PlayerInfoView instance = new PlayerInfoView();

    public static PlayerInfoView getInstance() {
        return instance;
    }

    private PlayerInfoView() {
        super("Thông tin người chơi", 400, 350);
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
        infoPanel = createInfoPanel();
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

        nicknameLabel = createLabel("N/A");
        scoreLabel = createLabel("0");
        rankLabel = createLabel("0");
        totalGamesLabel = createLabel("0");
        winRateLabel = createLabel("0.0%");

        addInfoRow(infoPanel, gbc, "Nickname:", nicknameLabel);
        addInfoRow(infoPanel, gbc, "Số trận đã chơi:", totalGamesLabel);
        addInfoRow(infoPanel, gbc, "Tỉ lệ thắng:", winRateLabel);
        addInfoRow(infoPanel, gbc, "Điểm:", scoreLabel);
        addInfoRow(infoPanel, gbc, "Rank:", rankLabel);
        addFriendStatus(infoPanel, gbc);

        return infoPanel;
    }

    private void addInfoRow(JPanel panel, GridBagConstraints gbc, String label, JLabel valueLabel) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(valueLabel, gbc);
    }

    private void addFriendStatus(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(createLabel("Thao tác:"), gbc);
        gbc.gridx = 1;

        statusFriendButton = new ButtonStyle("N/A", 150, 30);
        panel.add(statusFriendButton, gbc);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Roboto", Font.PLAIN, 15));
        return label;
    }

    public void setUserInfo(User user) {
        nicknameLabel.setText(user.getUsername());
        scoreLabel.setText(String.valueOf(user.getScore()));
        rankLabel.setText(String.valueOf(user.getRank()));
        totalGamesLabel.setText(String.valueOf(user.getGamesPlayed()));
        winRateLabel.setText(String.format("%.2f%%", user.getWinRate()));

        infoPanel.revalidate();
        infoPanel.repaint();
    }

    public JButton getStatusButton(String type) {
        statusFriendButton.setText(type);

        for (ActionListener actionListener : statusFriendButton.getActionListeners())
            statusFriendButton.removeActionListener(actionListener);

        return statusFriendButton;
    }
}
