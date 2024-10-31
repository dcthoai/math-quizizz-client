package math.client.view;

import math.client.dto.response.Room;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;

import java.util.List;
import java.util.Objects;

public class RoomView extends AbstractView {

    private final JLabel[] players = new JLabel[5];
    private ButtonStyle inviteButton;
    private ButtonStyle startGameButton;
    private JLabel roomIDLabel;
    private JLabel roomPlayersQuantityLabel;
    private static final RoomView instance = new RoomView();

    public static RoomView getInstance() {
        return instance;
    }

    private RoomView() {
        super("Phòng chơi mới", 480, 420);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    private void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelInfo = createPanelInfo();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(panelInfo, gbc);

        JPanel panelPlayers = createPanelPlayer();

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(panelPlayers, gbc);

        JPanel panelButton = createPanelBtn();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(panelButton, gbc);

        add(panel);
    }

    private JPanel createPanelInfo() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        roomIDLabel = new JLabel();
        roomIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        roomPlayersQuantityLabel = new JLabel();
        roomPlayersQuantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 50);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(roomIDLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 50, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(roomPlayersQuantityLabel, gbc);
        return panel;
    }

    private JPanel createPanelPlayer() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < 5; ++i) {
            players[i] = new JLabel();
            players[i].setPreferredSize(new Dimension(200, 35));
            players[i].setHorizontalAlignment(SwingConstants.CENTER);
            players[i].setOpaque(true);
            players[i].setBackground(Color.LIGHT_GRAY);
            players[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(5, 5, 5, 5);

            panel.add(players[i], gbc);
        }

        return panel;
    }

    private JPanel createPanelBtn() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        inviteButton = new ButtonStyle("Mời bạn", 150, 30);
        startGameButton = new ButtonStyle("Vào game", 150, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(inviteButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(startGameButton, gbc);

        return panel;
    }

    public JButton getInviteButton() {
        return inviteButton;
    }

    public JButton getStartGameButton() {
        return startGameButton;
    }

    public void updateView(Room room) {
        List<String> users = room.getUsers();
        int usersSize = users.size();

        roomIDLabel.setText("ID phòng: " + room.getRoomID());
        roomPlayersQuantityLabel.setText("Người chơi: " + room.getUsers().size() + "/5");

        for (int i = 0; i < 5; ++i) {
            if (i < usersSize) {
                String username = users.get(i);

                if (Objects.nonNull(username) && !username.isEmpty()) {
                    this.players[i].setText(username);
                    continue;
                }
            }

            this.players[i].setText("Trống");
        }
    }
}
