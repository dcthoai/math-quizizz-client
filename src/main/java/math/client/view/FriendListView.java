package math.client.view;

import javax.swing.BoxLayout;
import math.client.dto.response.FriendRequest;
import math.client.dto.response.User;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FriendListView extends AbstractView {

    private JTable friendTable;
    private DefaultTableModel friendTableModel;
    private JPanel friendRequestPanel;
    private List<FriendRequestComponent> requestComponents = new ArrayList<>();
    private static final FriendListView instance = new FriendListView();

    public static FriendListView getInstance() {
        return instance;
    }

    private FriendListView() {
        super("Danh sách bạn bè", 500, 450);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    private void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel labelListFriend = new JLabel("Bạn bè");
        labelListFriend.setFont(new Font("Roboto", Font.BOLD, 24));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(labelListFriend, gbc);

        // Friend request view
        JLabel friendRequestLabel = new JLabel("Lời mời kết bạn");
        friendRequestLabel.setFont(new Font("Roboto", Font.BOLD, 13));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        panel.add(friendRequestLabel, gbc);

        friendRequestPanel = new JPanel();
        friendRequestPanel.setLayout(new BoxLayout(friendRequestPanel, BoxLayout.Y_AXIS));

        JScrollPane friendRequestScrollPane = new JScrollPane(friendRequestPanel);
        friendRequestScrollPane.setBorder(null);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.25;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(friendRequestScrollPane, gbc);
        // End friend request view

        JLabel friendListLabel = new JLabel("Danh sách bạn bè");
        friendListLabel.setFont(new Font("Roboto", Font.BOLD, 13));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        panel.add(friendListLabel, gbc);

        String[] columnNames = {"Tên", "Tổng điểm", "Xếp hạng", "Trạng thái"};
        friendTableModel = new DefaultTableModel(null, columnNames);

        friendTable = new JTable(friendTableModel);
        friendTable.setFillsViewportHeight(true);
        friendTable.setDefaultEditor(Object.class, null);
        friendTable.setBorder(null);
        friendTable.setFocusable(false);
        friendTable.setRowHeight(30);

        friendTable.setFont(new Font("Roboto", Font.PLAIN, 15));
        friendTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 13));
        friendTable.setBackground(new Color(255, 255, 255));
        friendTable.getTableHeader().setBackground(Color.LIGHT_GRAY);

        friendTable.getColumnModel().getColumn(0).setMinWidth(60);
        friendTable.getColumnModel().getColumn(0).setMaxWidth(150);
        friendTable.getColumnModel().getColumn(1).setMinWidth(60);
        friendTable.getColumnModel().getColumn(1).setMaxWidth(150);
        friendTable.getColumnModel().getColumn(2).setMinWidth(60);
        friendTable.getColumnModel().getColumn(2).setMaxWidth(150);
        friendTable.getColumnModel().getColumn(3).setMinWidth(60);
        friendTable.getColumnModel().getColumn(3).setMaxWidth(150);

        friendTable.getTableHeader().setReorderingAllowed(false);
        friendTable.getColumnModel().getColumn(0).setResizable(false);
        friendTable.getColumnModel().getColumn(1).setResizable(false);
        friendTable.getColumnModel().getColumn(2).setResizable(false);
        friendTable.getColumnModel().getColumn(3).setResizable(false);

        StatusCellRenderer statusRenderer = new StatusCellRenderer();
        friendTable.getColumnModel().getColumn(3).setCellRenderer(statusRenderer);

        // Center table content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 1; i < columnNames.length; i++) {
            friendTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(friendTable);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.75;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(scrollPane, gbc);

        add(panel);
    }

    public void updateListFriend(List<User> users) {
        friendTableModel.setRowCount(0);

        users.forEach(user -> {
            String status = user.getLoginStatus() ? "Online" : "Offline";
            String[] newRow = { "  " + user.getUsername(), String.valueOf(user.getScore()), String.valueOf(user.getRank()), status };
            friendTableModel.addRow(newRow);
        });

        friendTableModel.fireTableDataChanged();
    }

    public void updateFriendRequests(List<FriendRequest> friendRequests) {
        requestComponents.clear();
        friendRequestPanel.removeAll();

        requestComponents = friendRequests.stream().map(friendRequest -> {
            FriendRequestComponent requestComponent = new FriendRequestComponent(friendRequest.getUserSendRequest(), friendRequest.getID());
            friendRequestPanel.add(requestComponent.getPanel());

            return requestComponent;
        }).collect(Collectors.toList());

        friendRequestPanel.revalidate();
        friendRequestPanel.repaint();
    }

    public List<FriendRequestComponent> getRequestComponents() {
        return requestComponents;
    }

    public JTable getFriendTable() {
        return friendTable;
    }
}
