package math.client.view;

import javax.swing.table.TableModel;
import math.client.dto.response.Rank;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;

import java.util.List;

public class RankView extends AbstractView {

    private JTable rankTable;
    private DefaultTableModel tableModel;
    private static final RankView instance = new RankView();

    public static RankView getInstance() {
        return instance;
    }

    private RankView() {
        super("Rank List", 500, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        String[] columnNames = {"Xếp hạng", "Người chơi", "Tổng điểm"};

        tableModel = new DefaultTableModel(columnNames, 0);
        rankTable = new JTable(tableModel);
        rankTable.setFillsViewportHeight(true);
        rankTable.setDefaultEditor(Object.class, null);
        rankTable.setBorder(null);
        rankTable.setRowHeight(30);
        rankTable.setFocusable(false);

        rankTable.setFont(new Font("Roboto", Font.PLAIN, 15));
        rankTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 13));
        rankTable.setBackground(new Color(255, 255, 255));
        rankTable.getTableHeader().setBackground(Color.LIGHT_GRAY);

        rankTable.getColumnModel().getColumn(0).setMinWidth(60);
        rankTable.getColumnModel().getColumn(0).setMaxWidth(150);
        rankTable.getColumnModel().getColumn(1).setMinWidth(100);
        rankTable.getColumnModel().getColumn(1).setMaxWidth(200);
        rankTable.getColumnModel().getColumn(2).setMinWidth(60);
        rankTable.getColumnModel().getColumn(2).setMaxWidth(150);

        rankTable.getTableHeader().setReorderingAllowed(false);
        rankTable.getColumnModel().getColumn(0).setResizable(false);
        rankTable.getColumnModel().getColumn(1).setResizable(false);
        rankTable.getColumnModel().getColumn(2).setResizable(false);

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

    public void setDataToTable(List<Rank> ranks) {
        tableModel.setRowCount(0);

        for (Rank rank : ranks) {
            tableModel.addRow(new Object[]{
                    rank.getUserRank(),
                    rank.getUsername(),
                    rank.getScore()
            });
        }
    }

    public JTable getTable() {
        return rankTable;
    }
}
