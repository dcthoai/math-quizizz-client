package math.client.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class FriendRequestComponent {

    private final JPanel panel = new JPanel();
    private final Integer friendRequestID;
    private final JButton acceptButton;
    private final JButton rejectButton;

    public FriendRequestComponent(String userSendRequest, Integer friendRequestID) {
        this.friendRequestID = friendRequestID;
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        JLabel usernameLabel = new JLabel(userSendRequest);
        usernameLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 0, 0);
        acceptButton = new ButtonStyle("Chấp nhận", 105, 25);
        panel.add(acceptButton, gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(10, 10, 0, 10);
        rejectButton = new ButtonStyle("Từ chối", 85, 25);
        panel.add(rejectButton, gbc);

        acceptButton.setFocusable(true);
        rejectButton.setFocusable(true);

        panel.setBackground(Color.WHITE);
        panel.setFocusable(true);
        panel.setRequestFocusEnabled(true);
    }

    public Integer getFriendRequestID() {
        return friendRequestID;
    }

    public JButton getAcceptButton() {
        return acceptButton;
    }

    public JButton getRejectButton() {
        return rejectButton;
    }

    public JPanel getPanel() {
        return panel;
    }
}
