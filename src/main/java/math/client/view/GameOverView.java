package math.client.view;

import javax.swing.JButton;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import math.client.dto.response.User;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.Map;

public class GameOverView extends AbstractView {

    private static final GameOverView instance = new GameOverView();
    private JLabel title;
    private JLabel correctAnswers;
    private JLabel totalPoint;
    private JLabel rank;
    private DefaultTableModel ranksTable;
    private JButton backButton;
    private boolean isWin = false;

    public static GameOverView getInstance() {
        return instance;
    }

    private GameOverView() {
        super("Game Over", 480, 440);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    private void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title based on win/loss
        title = new JLabel();
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        title.setForeground(isWin ? Color.GREEN : Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        // Correct answers
        correctAnswers = new JLabel("Correct Answers: " + correctAnswers);
        correctAnswers.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        panel.add(correctAnswers, gbc);

        // Score
        totalPoint = new JLabel();
        totalPoint.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 10, 10); // Padding
        panel.add(totalPoint, gbc);

        // Rank
        rank = new JLabel();
        rank.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 10, 10); // Padding
        panel.add(rank, gbc);

        // Leaderboard table
        String[] columnNames = {"Xếp hạng", "Tên người chơi", "Điểm số"};
        ranksTable = new DefaultTableModel(columnNames, 0);
        JTable rankingTable = new JTable(ranksTable);
        rankingTable.setFillsViewportHeight(true);
        rankingTable.setDefaultEditor(Object.class, null);
        rankingTable.setBorder(null);
        rankingTable.setFocusable(false);
        rankingTable.setRowHeight(25);

        rankingTable.setFont(new Font("Roboto", Font.BOLD, 12));
        rankingTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 13));
        rankingTable.setBackground(new Color(255, 255, 255));
        rankingTable.getTableHeader().setBackground(Color.LIGHT_GRAY);

        rankingTable.getColumnModel().getColumn(0).setMinWidth(100);
        rankingTable.getColumnModel().getColumn(0).setMaxWidth(150);
        rankingTable.getColumnModel().getColumn(1).setMinWidth(100);
        rankingTable.getColumnModel().getColumn(1).setMaxWidth(200);
        rankingTable.getColumnModel().getColumn(2).setMinWidth(100);
        rankingTable.getColumnModel().getColumn(2).setMaxWidth(150);

        rankingTable.getTableHeader().setReorderingAllowed(false);
        rankingTable.getColumnModel().getColumn(0).setResizable(false);
        rankingTable.getColumnModel().getColumn(1).setResizable(false);
        rankingTable.getColumnModel().getColumn(2).setResizable(false);

        // Center table content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        rankingTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        rankingTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        rankingTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(rankingTable);
        scrollPane.setPreferredSize(new Dimension(420, 150));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(scrollPane, gbc);

        backButton = new ButtonStyle("Quay lại", 150, 30);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(backButton, gbc);

        // Add the panel to the frame
        add(panel);
    }

    public void updateUserResult(User user) {
        isWin = user.getCurrentRank() == 1;
        title.setText(isWin ? "You win" : "You lose");
        title.setForeground(isWin ? Color.GREEN : Color.RED);
        rank.setText("Thứ hạng của bạn: " + user.getCurrentRank());
        totalPoint.setText("Điểm số: " + user.getCurrentPoint());
        correctAnswers.setText("Trả lời đúng: " + user.getCorrectAnswers());
    }

    public void updateRankingResult(Map<String, Integer> ranking) {
        ranksTable.setRowCount(0);
        int[] rank = {1}; // Because in lambda expression must be final or effectively final

        ranking.forEach((username, point) -> {
            String[] newRow = { String.valueOf(rank[0]), username, String.valueOf(point) };
            ranksTable.addRow(newRow);
            rank[0] += 1;
        });

        ranksTable.fireTableDataChanged();
    }

    public JButton getBackButton() {
        return backButton;
    }
}
