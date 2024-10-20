package math.client.view;

import math.client.model.User; // Thay thế bằng mô hình người chơi của bạn

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RankView extends AbstractView {
    private List<User> listRank; // Danh sách người dùng cho xếp hạng
    private DefaultTableModel tableModel;

    public RankView() {
        super("Rank List", 420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    public void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        JLabel labelListRank = new JLabel("Bảng xếp hạng");
        labelListRank.setFont(new Font("Roboto", Font.BOLD, 24));



        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(labelListRank, gbc);

        String[] columnNames = {"Nickname", "Score", "Rank"};

        Object[][] data = {
                {"Player 1", "2000", "1"},
                {"Player 2", "1500", "2"},
                {"Player 3", "1200", "3"},
                {"Player 4", "1000", "4"}
        };

        tableModel = new DefaultTableModel(data, columnNames);
        JTable rankTable = new JTable(tableModel);
        rankTable.setFillsViewportHeight(true);
        rankTable.setDefaultEditor(Object.class, null);
        rankTable.setBorder(null);
        rankTable.setRowHeight(30);
        rankTable.setFocusable(false);

        rankTable.setFont(new Font("Roboto", Font.PLAIN, 15));
        rankTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 13));
        rankTable.setBackground(new Color(255, 255, 255));
        rankTable.getTableHeader().setBackground(Color.LIGHT_GRAY);

        // Thiết lập độ rộng cột
        rankTable.getColumnModel().getColumn(0).setMinWidth(60);
        rankTable.getColumnModel().getColumn(0).setMaxWidth(150);
        rankTable.getColumnModel().getColumn(1).setMinWidth(60);
        rankTable.getColumnModel().getColumn(1).setMaxWidth(150);
        rankTable.getColumnModel().getColumn(2).setMinWidth(60);
        rankTable.getColumnModel().getColumn(2).setMaxWidth(150);

        rankTable.getTableHeader().setReorderingAllowed(false);
        rankTable.getColumnModel().getColumn(0).setResizable(false);
        rankTable.getColumnModel().getColumn(1).setResizable(false);
        rankTable.getColumnModel().getColumn(2).setResizable(false);

        rankTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = rankTable.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    String username = (String) rankTable.getValueAt(row, 0);
                    System.out.println("Click on " + username);
                }
            }
        });

        // Căn giữa nội dung trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            rankTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(rankTable);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(scrollPane, gbc);

        getContentPane().add(panel);
    }

//    public void setDataToTable(List<User> users) {
//        this.listRank = users;
//        tableModel.setRowCount(0);
//        int i = 0;
//        for (User user : listRank) {
//            tableModel.addRow(new Object[]{
//                    user.getNickname(),
//                    user.geScore(),
//                    user.getRank()
//            });
//            i++;
//        }
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RankView view = new RankView();
                view.setVisible(true);
            }
        });
    }
}
