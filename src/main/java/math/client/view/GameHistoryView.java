package math.client.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import math.client.dto.response.GameHistory;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class GameHistoryView extends AbstractView {
    private DefaultTableModel tableModel;
    private static final GameHistoryView instance = new GameHistoryView();

    public static GameHistoryView getInstance() {
        return instance;
    }

    private GameHistoryView() {
        super("Lịch sử đấu", 600, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    public void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel labelHistory = new JLabel("Lịch sử đấu");
        labelHistory.setFont(new Font("Roboto", Font.BOLD, 24));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(labelHistory, gbc);
        String[] columnNames = {"Thứ hạng", "Điểm số", "Số người chơi", "Thời gian"};
        tableModel = new DefaultTableModel(columnNames, 0);

        JTable historyTable = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? getBackground() : new Color(240, 240, 240));
                }
                return c;
            }
        };

        historyTable.setFillsViewportHeight(true);
        historyTable.setDefaultEditor(Object.class, null);
        historyTable.setBorder(new LineBorder(Color.GRAY));
        historyTable.setRowHeight(30);
        historyTable.setFocusable(false);
        historyTable.setFont(new Font("Roboto", Font.PLAIN, 15));
        historyTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 13));
        historyTable.setBackground(new Color(255, 255, 255));
        historyTable.getTableHeader().setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < columnNames.length; i++) {
            historyTable.getColumnModel().getColumn(i).setMinWidth(60);
            historyTable.getColumnModel().getColumn(i).setMaxWidth(150);
            historyTable.getColumnModel().getColumn(i).setResizable(false);
        }

        historyTable.getColumnModel().getColumn(3).setMaxWidth(220);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < columnNames.length; i++) {
            historyTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBorder(new LineBorder(Color.GRAY));

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

    public void updateUserGameHistories(List<GameHistory> gameHistories) {
        tableModel.setRowCount(0);

        gameHistories.forEach(gameHistory ->
            tableModel.addRow(new Object[]{
                    gameHistory.getUserRank(),
                    gameHistory.getScore(),
                    gameHistory.getTotalPlayer(),
                    gameHistory.getFinishedTime()
            })
        );
    }
}
