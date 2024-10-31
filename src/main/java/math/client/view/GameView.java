package math.client.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.Timer;
import javax.swing.Box;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends AbstractView {

    private JLabel questionLabel;
    private JTextField answerField;
    private ButtonStyle quitButton;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel rankLabel;
    private JLabel usernameLabel;
    private Timer countdownTimer;
    private int timeLeft = 300;
    private int score = 0;
    private int targetResult;
    private int[] numbers = new int[5];
    private static final Logger log = LoggerFactory.getLogger(GameView.class);
    private static final GameView instance = new GameView();

    public static GameView getInstance() {
        return instance;
    }

    private GameView() {
        super("Game Screen", 500, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        generateView();
//        startCountdownTimer();
    }

    private void generateView() {
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

        quitButton = new ButtonStyle("Quit", 80, 30);

        timeLabel = new JLabel("Time left: 5:00");
        timeLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        // Score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        // Ranking label
        rankLabel = new JLabel("Rank: 0"); // Initialize ranking
        rankLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        // Username label
        usernameLabel = new JLabel("Player: ");
        usernameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        // Add username label to the top left corner
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(5, 0, 0, 0);
        panel.add(timeLabel, gbc);

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        infoPanel.add(scoreLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        infoPanel.add(rankLabel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 0, 50, 0);
        panel.add(infoPanel, gbc);

        // Add question to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(questionLabel, gbc);

        // Add answer input field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(answerField, gbc);

        // Add Quit buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.add(quitButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(40, 0, 0, 0);
        panel.add(buttonPanel, gbc);

        add(panel);
    }

//    private void startCountdownTimer() {
//        countdownTimer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                timeLeft--;
//                updateTimeDisplay();
//                if (timeLeft <= 0) {
//                    countdownTimer.stop();
//                    JOptionPane.showMessageDialog(GameView.this, "Time's up!");
//                }
//            }
//        });
//        countdownTimer.start();
//    }

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

    public JLabel getQuestionLabel() {
        return questionLabel;
    }

    public JTextField getAnswerField() {
        return answerField;
    }

    public ButtonStyle getQuitButton() {
        return quitButton;
    }

    public void setUsernameLabel(String username) {
        usernameLabel.setText("Player: " + username);
    }

    public void setScoreLabel(String score) {
        scoreLabel.setText("Score: " + score);
    }

    public void setRankLabel(String rank) {
        rankLabel.setText("Rank: " + rank);
    }
}
