package math.client.view;

import math.client.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class CreateRoomView extends AbstractView {
    private ButtonStyle btnInvite;
    private ButtonStyle btnLeaveRoom;
    private String roomId;
    private Timer countdownTimer;
    private int countdown = 5;
    private List<User> listPlayer;
    private int playerCount;

    public CreateRoomView() {
        super("Create Room", 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        pack();
    }

    private JPanel createPanelInfo() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel labelRoomId = new JLabel("Room ID: " + "123456");
        labelRoomId.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel labelNumPlayers = new JLabel("Players: " + "1" + "/5");
        labelNumPlayers.setFont(new Font("Tahoma", Font.PLAIN, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 100);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelRoomId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 100, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(labelNumPlayers, gbc);
        return panel;
    }

    private JPanel createPanelPlayer() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] players = {"Player1", "Player2", "Player3", "Player4", "Player5"};
        for (int i = 0; i < players.length; i++) { // listPlayer.size()
            JLabel playerLabel = new JLabel(players[i]);
            playerLabel.setPreferredSize(new Dimension(150, 30));
            playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            playerLabel.setOpaque(true);
            playerLabel.setBackground(Color.LIGHT_GRAY);
            playerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(5, 5, 5, 5);

            panel.add(playerLabel, gbc);
        }

        return panel;
    }


    private JPanel createPanelBtn() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        btnInvite = new ButtonStyle("Invite", 150, 30);
        btnLeaveRoom = new ButtonStyle("Quit game", 150, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(btnInvite, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(btnLeaveRoom, gbc);

        return panel;
    }

    private void startCountdownDialog() {
        JDialog countdownDialog = new JDialog(this, "Game Starting Soon", true);
        countdownDialog.setSize(300, 150);
        countdownDialog.setLayout(new BorderLayout());
        countdownDialog.setLocationRelativeTo(this);

        JLabel countdownLabel = new JLabel("Game sẽ bắt đầu trong " + countdown + " giây.", SwingConstants.CENTER);
        countdownDialog.add(countdownLabel, BorderLayout.CENTER);

        countdownTimer = new Timer(1000, e -> {
            if (countdown > 0) {
                countdownLabel.setText("Game sẽ bắt đầu trong " + countdown + " giây.");
                countdown--;
            } else {
                countdownTimer.stop();
                countdownDialog.dispose();
                startGame();
            }
        });

        countdownTimer.start();
        countdownDialog.setVisible(true);
    }

    private void startGame() {
        //Game view
    }

    public JButton getBtnInvite() {
        return btnInvite;
    }

    public JButton getBtnLeaveRoom() {
        return btnLeaveRoom;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreateRoomView createRoomView = new CreateRoomView();
            createRoomView.setVisible(true);
        });
    }
}
