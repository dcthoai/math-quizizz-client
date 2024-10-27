package math.client.view;

import math.client.controller.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class GameOverView extends AbstractView implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    private static final GameOverView instance = new GameOverView();

    public static GameOverView getInstance() {
        return instance;
    }

    private GameOverView() {
        super("Game Over", 450, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateView(0, 0, 0, 0, new String[1][1], false);
    }

    private void generateView(int timeTaken, int correctAnswers, int score, int rank, String[][] leaderboard, boolean hasWon) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Title based on win/loss
        String title = hasWon ? "You Win!" : "You Lose!";
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setForeground(hasWon ? Color.GREEN : Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        // Time taken
        JLabel timeLabel = new JLabel("Time Taken: " + formatTime(timeTaken));
        timeLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 1;
        panel.add(timeLabel, gbc);

        // Correct answers
        JLabel correctAnswersLabel = new JLabel("Correct Answers: " + correctAnswers);
        correctAnswersLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 2;
        panel.add(correctAnswersLabel, gbc);

        // Score
        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 3;
        panel.add(scoreLabel, gbc);

        // Rank
        JLabel rankLabel = new JLabel("Rank: " + rank);
        rankLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        gbc.gridy = 4;
        panel.add(rankLabel, gbc);

        // Leaderboard table
        String[] columnNames = {"Rank", "Username", "Score"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (String[] entry : leaderboard) {
            model.addRow(entry);
        }
        JTable leaderboardTable = new JTable(model);
        leaderboardTable.setFillsViewportHeight(true);
        leaderboardTable.setDefaultEditor(Object.class, null);
        leaderboardTable.setBorder(null);
        leaderboardTable.setFocusable(false);
        leaderboardTable.setRowHeight(30);

        leaderboardTable.setFont(new Font("Roboto", Font.PLAIN, 15));
        leaderboardTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 13));
        leaderboardTable.setBackground(new Color(255, 255, 255));
        leaderboardTable.getTableHeader().setBackground(Color.LIGHT_GRAY);

        leaderboardTable.getColumnModel().getColumn(0).setMinWidth(60);
        leaderboardTable.getColumnModel().getColumn(0).setMaxWidth(150);
        leaderboardTable.getColumnModel().getColumn(1).setMinWidth(60);
        leaderboardTable.getColumnModel().getColumn(1).setMaxWidth(150);
        leaderboardTable.getColumnModel().getColumn(2).setMinWidth(60);
        leaderboardTable.getColumnModel().getColumn(2).setMaxWidth(150);

        leaderboardTable.getTableHeader().setReorderingAllowed(false);
        leaderboardTable.getColumnModel().getColumn(0).setResizable(false);
        leaderboardTable.getColumnModel().getColumn(1).setResizable(false);
        leaderboardTable.getColumnModel().getColumn(2).setResizable(false);

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setPreferredSize(new Dimension(400, 174));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(scrollPane, gbc);

        ButtonStyle backButton = new ButtonStyle("Back to Home", 150, 30);
        backButton.addActionListener(e -> {
            dispose();
        });
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        panel.add(backButton, gbc);
        // Add the panel to the frame
        add(panel);
        pack();
    }

    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public void run() {
        log.info("Initialize game over view successfully");
        open();
    }
}
