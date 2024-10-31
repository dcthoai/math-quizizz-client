package math.client.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Insets;

public class SearchRoomView extends AbstractView {

    private ButtonStyle searchButton;
    private ButtonStyle backButton;
    private JTextField roomCodeField;
    private static final SearchRoomView instance = new SearchRoomView();

    public static SearchRoomView getInstance() {
        return instance;
    }

    private SearchRoomView() {
        super("Join Room", 400, 250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
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
        searchButton = new ButtonStyle("Vào phòng", 150, 30);

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
        buttonPanel.add(searchButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Thêm panel vào frame
        add(panel);
    }

    public JButton getSearchRoomButton() {
        return searchButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public String getRoomCode() {
        return roomCodeField.getText();
    }
}
