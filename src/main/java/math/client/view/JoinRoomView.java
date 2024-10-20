package math.client.view;

import javax.swing.*;
import java.awt.*;

public class JoinRoomView extends AbstractView {

    private ButtonStyle joinRoomButton;
    private ButtonStyle backButton;
    private JTextField roomCodeField;

    public JoinRoomView() {
        super("Join Room", 400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateView();
    }

    private void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel roomCodeLabel = new JLabel("Room ID:");
        roomCodeLabel.setFont(new Font("Roboto", Font.BOLD, 13));

        roomCodeField = new JTextField(20);
        roomCodeField.setFont(new Font("Roboto", Font.PLAIN, 13));
        roomCodeField.setPreferredSize(new Dimension(150, 30));

        backButton = new ButtonStyle("Quay lại", 150, 30);
        joinRoomButton = new ButtonStyle("Vào phòng", 150, 30);

        // Label mã phòng
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(roomCodeLabel, gbc);

        // Ô nhập mã phòng
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(roomCodeField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.add(backButton);
        buttonPanel.add(joinRoomButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Thêm panel vào frame
        add(panel);
    }

    public JButton getJoinRoomButton() {
        return joinRoomButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public String getRoomCode() {
        return roomCodeField.getText();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Tạo và hiển thị JoinRoomView
            JoinRoomView joinRoomView = new JoinRoomView();
            joinRoomView.setVisible(true);
        });
    }
}
