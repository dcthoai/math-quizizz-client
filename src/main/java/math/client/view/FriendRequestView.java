package math.client.view;

import javax.swing.*;
import java.awt.*;
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
        super("Yêu cầu kết bạn", 400, 300);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        JLabel frameLabel = new JLabel("Yêu cầu kết bạn");
        frameLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(frameLabel, gbc);

        // Request title label
        JLabel requestTitleLabel = new JLabel("Bạn nhận được một lời mời kết bạn", JLabel.CENTER);
        requestTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        requestTitleLabel.setForeground(new Color(0, 102, 204));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(requestTitleLabel, gbc);

        // Request from label
        requestFromLabel = new JLabel("Từ " + "(ID=" + ")", JLabel.CENTER);
        requestFromLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        requestFromLabel.setForeground(new Color(0, 102, 204));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(requestFromLabel, gbc);

        // Accept and Decline Buttons
        acceptButton = new ButtonStyle("Đồng ý", 80,  30);
        declineButton = new ButtonStyle("Từ chối", 80, 30);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(acceptButton);
        buttonPanel.add(declineButton);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        autoCloseLabel = new JLabel("Tự động đóng sau 10 giây", JLabel.CENTER);
        gbc.gridy = 4;
        panel.add(autoCloseLabel, gbc);

        add(panel);

        // Timer to auto-close the window after 10 seconds
        timer = new Timer(1000, new ActionListener() {
            int count = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count >= 0) {
                    autoCloseLabel.setText("Tự động đóng trong " + count + " giây");
                } else {
                    ((Timer) (e.getSource())).stop();
                    disposeFrame();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();

//        acceptButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                handleAccept();
//            }
//        });

        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDecline();
            }
        });
    }

    private void handleDecline() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Bạn đã từ chối lời mời kết bạn!");
        dispose();
    }

    private void disposeFrame() {
        this.dispose();
    }
}
