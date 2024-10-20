package math.client.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GameOverView extends AbstractView {

    public GameOverView(int timeTaken, int correctAnswers, int score, int rank, String[][] leaderboard, boolean hasWon) {
        super("Game Over", 450, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateView(timeTaken, correctAnswers, score, rank, leaderboard, hasWon);
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

    public static void main(String[] args) {
        // Example data for demonstration
        String[][] leaderboard = {
                {"1", "Player1", "500"},
                {"2", "Player2", "450"},
                {"3", "Player3", "400"},
                {"4", "Player4", "350"},
                {"5", "You", "300"}
        };

        SwingUtilities.invokeLater(() -> {
            GameOverView gameOverView = new GameOverView(300, 10, 300, 5, leaderboard, false);
            gameOverView.setVisible(true);
        });
    }
}
