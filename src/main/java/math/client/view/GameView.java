package math.client.view;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.Box;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class GameView extends AbstractView {

    private JLabel questionLabel;
    private JLabel answerLabel;
    private JTextField answerField;
    private ButtonStyle quitButton;
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel rankLabel;
    private JLabel usernameLabel;
    private Long timeLeft;
    private Timer countdownTimer;
    private static final GameView instance = new GameView();

    public static GameView getInstance() {
        return instance;
    }

    private GameView() {
        super("Game Screen", 500, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    private void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        answerLabel = new JLabel();
        answerLabel.setFont(new Font("Roboto", Font.BOLD, 13));

        // Input field for answer
        answerField = new JTextField(20);
        answerField.setPreferredSize(new Dimension(250, 30));
        answerField.setFont(new Font("Roboto", Font.PLAIN, 15));

        quitButton = new ButtonStyle("Quit", 80, 30);

        timeLabel = new JLabel("Time left: 0:00");
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
        gbc.insets = new Insets(10, 0, 35, 0);
        panel.add(infoPanel, gbc);

        JTextArea gameRulesLabel = getGameRules();

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(gameRulesLabel, gbc);

        // Add question to the panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(questionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(answerLabel, gbc);

        // Add answer input field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(answerField, gbc);

        // Add Quit buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.add(quitButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 0, 0);
        panel.add(buttonPanel, gbc);

        add(panel);
    }

    private JTextArea getGameRules() {
        JTextArea gameRulesLabel = new JTextArea();
        gameRulesLabel.setFont(new Font("Roboto", Font.ITALIC, 12));
        gameRulesLabel.setText("Tìm phép tính 3 toán hạng có kết quả như trên. Sử dụng mảng chữ số. Không dùng số âm và dấu ngoặc");
        gameRulesLabel.setWrapStyleWord(true);
        gameRulesLabel.setLineWrap(true);
        gameRulesLabel.setEditable(false);
        gameRulesLabel.setOpaque(false);
        gameRulesLabel.setBackground(new Color(0, 0, 0, 0));
        gameRulesLabel.setPreferredSize(new Dimension(300, 45));

        return gameRulesLabel;
    }

    public void startCountdownTimer(Long time) {
        this.timeLeft = time;

        if (countdownTimer != null && countdownTimer.isRunning()) {
            countdownTimer.stop();
        }

        countdownTimer = new Timer(1000, event -> {
            timeLeft -= 1;
            updateTimeDisplay();

            if (timeLeft <= 0) {
                countdownTimer.stop();
            }
        });

        countdownTimer.start();
    }

    private void updateTimeDisplay() {
        long minutes = timeLeft / 60;
        long seconds = timeLeft % 60;
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

    public void hideAnswerLabel() {
        new Timer(3000, event -> answerLabel.setText("")).start();
    }

    public void setCorrectAnswer() {
        answerLabel.setForeground(Color.GREEN);
        answerLabel.setText("Câu trả lời đúng!");
        answerField.setText("");
    }

    public void setIncorrectAnswer() {
        answerLabel.setForeground(Color.RED);
        answerLabel.setText("Câu trả lời sai!");
        answerField.setText("");
    }
}
