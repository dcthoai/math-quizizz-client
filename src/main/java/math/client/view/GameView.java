package math.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameView extends AbstractView {

    private JLabel questionLabel;
    private JTextField answerField;
    private ButtonStyle submitButton, quitButton;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel rankLabel;
    private JLabel usernameLabel;
    private Timer countdownTimer;
    private int timeLeft = 300;
    private int score = 0;
    private int targetResult;
    private int[] numbers = new int[5];

    public GameView(int targetResult, String username) {
        super("Game Screen", 420, 400);
        this.targetResult = targetResult;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateRandomNumbers();
        generateView(username);
        startCountdownTimer();
    }

    private void generateRandomNumbers() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            numbers[i] = rand.nextInt(101);
        }
    }

    private void generateView(String username) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        StringBuilder questionText = new StringBuilder("Use these numbers: ");
        for (int number : numbers) {
            questionText.append(number).append(" ");
        }
        questionText.append("| Target: ").append(targetResult);

        questionLabel = new JLabel(questionText.toString());
        questionLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        // Input field for answer
        answerField = new JTextField(20);
        answerField.setPreferredSize(new Dimension(250, 30));
        answerField.setFont(new Font("Roboto", Font.PLAIN, 15));

        // Buttons
        submitButton = new ButtonStyle("Submit", 80, 30);
        quitButton = new ButtonStyle("Quit", 80, 30);

        timeLabel = new JLabel("Time left: 5:00");
        timeLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        // Score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        // Ranking label
        rankLabel = new JLabel("Rank: 1"); // Initialize ranking
        rankLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        // Username label
        usernameLabel = new JLabel("Player: " + username);
        usernameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        // Add username label to the top right corner
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(10, 10, 50, 10);
        panel.add(usernameLabel, gbc);

        // Add question to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(questionLabel, gbc);

        // Add answer input field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(answerField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(timeLabel, gbc);

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        infoPanel.add(scoreLabel);
        infoPanel.add(rankLabel);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(infoPanel, gbc);

        // Add Submit and Quit buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.add(submitButton);
        buttonPanel.add(quitButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evaluateAnswer();
            }
        });

        quitButton.addActionListener(e -> dispose());
    }

    private void startCountdownTimer() {
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                updateTimeDisplay();
                if (timeLeft <= 0) {
                    countdownTimer.stop();
                    JOptionPane.showMessageDialog(GameView.this, "Time's up!");
                }
            }
        });
        countdownTimer.start();
    }

    private void updateTimeDisplay() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        timeLabel.setText(String.format("Time left: %d:%02d", minutes, seconds));

        if (timeLeft < 31) {
            timeLabel.setForeground(Color.RED);
        } else {
            timeLabel.setForeground(Color.BLACK);
        }
    }

    // Check player's answer
    private void evaluateAnswer() {
        try {
            int playerAnswer = Integer.parseInt(answerField.getText());
            if (playerAnswer == targetResult) {
                score += 100; // Increase score
                scoreLabel.setText("Score: " + score);
                rankLabel.setText("Rank: " + calculateRank(score)); // Update ranking
                JOptionPane.showMessageDialog(this, "Correct! You achieved the result.");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect. Try again.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private int calculateRank(int score) {
        return 5;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView gameView = new GameView(50, "Player1");
            gameView.setVisible(true);
        });
    }
}
