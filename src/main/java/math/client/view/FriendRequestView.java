package math.client.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendRequestView extends AbstractView {

    private Timer timer;
    private JLabel requestFromLabel;
    private ButtonStyle acceptButton;
    private ButtonStyle declineButton;
    private JLabel autoCloseLabel;
    private static final FriendRequestView instance = new FriendRequestView();

    public static FriendRequestView getInstance() {
        return instance;
    }

    private FriendRequestView() {
        super("Thông báo", 400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        startCountdown();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        JLabel frameLabel = new JLabel("Lời mời chơi game");
        frameLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(frameLabel, gbc);

        // Request title label
        JLabel requestTitleLabel = new JLabel("Bạn nhận được một lời mời chơi game từ: ", JLabel.CENTER);
        requestTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        requestTitleLabel.setForeground(new Color(0, 102, 204));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(requestTitleLabel, gbc);

        // Request from label
        requestFromLabel = new JLabel("", JLabel.CENTER);
        requestFromLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        requestFromLabel.setForeground(new Color(0, 102, 204));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(requestFromLabel, gbc);

        // Accept and Decline Buttons
        acceptButton = new ButtonStyle("Đồng ý", 100,  30);
        declineButton = new ButtonStyle("Từ chối", 100, 30);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(acceptButton);
        buttonPanel.add(declineButton);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        autoCloseLabel = new JLabel("Tự động đóng sau 30 giây", JLabel.CENTER);
        gbc.gridy = 4;
        panel.add(autoCloseLabel, gbc);

        add(panel);
    }

    private void startCountdown() {
        // Timer to auto-close the window after 60 seconds
        int[] count = new int[] {60};

        timer = new Timer(1000, event -> {
            if (count[0] > 0) {
                autoCloseLabel.setText("Tự động đóng trong " + count[0] + " giây");
                count[0] -= 1;
            } else {
                disposeFrame();
            }
        });

        timer.setInitialDelay(0);
        timer.start();

        declineButton.addActionListener(event -> disposeFrame());
    }

    private void disposeFrame() {
        timer.stop();
        this.dispose();
    }

    public void openView(String requestUsername) {
        this.requestFromLabel.setText(requestUsername);
        open();
    }

    public JButton getAcceptButton() {
        return acceptButton;
    }
}
