package math.client.view;

import javax.swing.JButton;
import math.client.dto.response.User;

import javax.swing.JLabel;
import javax.swing.JFrame;
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
    private JTable rankingTable;
    private JButton backButton;
    private boolean isWin = false;

    public static GameOverView getInstance() {
        return instance;
    }

    private GameOverView() {
        super("Game Over", 450, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateView();
    }

    private void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Title based on win/loss
        title = new JLabel();
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        title.setForeground(isWin ? Color.GREEN : Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        // Time taken
        JLabel timeLabel = new JLabel("Time Taken: ");
        timeLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 1;
        panel.add(timeLabel, gbc);

        // Correct answers
        correctAnswers = new JLabel("Correct Answers: " + correctAnswers);
        correctAnswers.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 2;
        panel.add(correctAnswers, gbc);

        // Score
        totalPoint = new JLabel();
        totalPoint.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 3;
        panel.add(totalPoint, gbc);

        // Rank
        rank = new JLabel();
        rank.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 4;
        panel.add(rank, gbc);

        // Leaderboard table
        String[] columnNames = {"Xếp hạng", "Tên người chơi", "Điểm số"};
        ranksTable = new DefaultTableModel(columnNames, 0);
        rankingTable = new JTable(ranksTable);
        rankingTable.setFillsViewportHeight(true);
        rankingTable.setDefaultEditor(Object.class, null);
        rankingTable.setBorder(null);
        rankingTable.setFocusable(false);
        rankingTable.setRowHeight(30);

        rankingTable.setFont(new Font("Roboto", Font.PLAIN, 15));
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

        JScrollPane scrollPane = new JScrollPane(rankingTable);
        scrollPane.setPreferredSize(new Dimension(400, 174));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(scrollPane, gbc);

        backButton = new ButtonStyle("Quay lại", 150, 30);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(backButton, gbc);

        // Add the panel to the frame
        add(panel);
    }

    public void updateUserResult(User user) {
        isWin = user.getCurrentRank() == 1;
        title.setText(isWin ? "You win!" : "You lose!");
        rank.setText(String.valueOf(user.getCurrentRank()));
        totalPoint.setText(String.valueOf(user.getCurrentPoint()));
    }

    public void updateRankingResult(Map<String, Integer> ranking) {
        ranksTable.setRowCount(0);
        int rank = 1;

        ranking.forEach((username, point) -> {
            String[] newRow = { String.valueOf(rank), username, String.valueOf(point) };
            ranksTable.addRow(newRow);
        });

        ranksTable.fireTableDataChanged();
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JTable getRankingTable() {
        return rankingTable;
    }
}
