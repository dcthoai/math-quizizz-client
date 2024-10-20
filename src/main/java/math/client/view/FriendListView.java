package math.client.view;

import math.client.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FriendListView extends AbstractView {
    private List<User> listFriend;
    private DefaultTableModel tableModel;

    public FriendListView() {
        super("List Friend", 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    public void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel labelListFriend = new JLabel("Danh sách bạn bè");
        labelListFriend.setFont(new Font("Roboto", Font.BOLD, 24));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(labelListFriend, gbc);

        String[] columnNames = {"Nickname", "Score", "Rank", ""};

        Object[][] data = {
                {"Friend 1","1000", "1", "Online"},
                {"Friend 2","1000", "2", "Offline"},
                {"Friend 3","1000", "3", "Online"},
                {"Friend 4","1000", "4", "Offline"}
        };

        tableModel = new DefaultTableModel(data, columnNames);
        JTable friendTable = new JTable(tableModel);
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

        friendTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = friendTable.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    String username = (String) friendTable.getValueAt(row, 0);
                    System.out.println("Click on " + username);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(friendTable);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(scrollPane, gbc);

        getContentPane().add(panel);
        pack();
    }

//    public void updateListFriend(List<User> users) {
//        this.listFriend = users;
//        tableModel.setRowCount(0);
//        for (User user : listFriend) {
//            tableModel.addRow(new Object[]{
//                    user.getNickname(),
//                    user.geScore(),
//                    user.getRank(),
//                    user.getStatus()
//            });
//        }
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FriendListView view = new FriendListView();
                view.setVisible(true);
            }
        });
    }
}
