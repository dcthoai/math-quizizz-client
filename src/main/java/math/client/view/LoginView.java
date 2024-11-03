package math.client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class LoginView extends AbstractView {

    private JButton loginButton;
    private JButton registerButton;
    private JTextField username;
    private JPasswordField password;
    private static final LoginView instance = new LoginView();

    public static LoginView getInstance() {
        return instance;
    }

    private LoginView() {
        super("Math Quizzes Game", 500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    private void generateView() {
        // Create a container with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        username = new JTextField(20);
        password = new JPasswordField(20);

        username.setPreferredSize(new Dimension(250, 30));
        password.setPreferredSize(new Dimension(250, 30));

        loginButton = new JButton("Đăng nhập");
        registerButton = new JButton("Đăng ký");

        loginButton.setPreferredSize(new Dimension(100, 30));
        registerButton.setPreferredSize(new Dimension(100, 30));

        JLabel title = new JLabel("Đăng nhập");
        title.setFont(new Font("Roboto", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(title, gbc);

        // Positioning the Username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 15, 10, 15);  // Padding
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userLabel, gbc);

        // Positioning the User text field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(username, gbc);

        // Positioning the Password label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.WEST;
        panel.add(passwordLabel, gbc);

        // Positioning the Password text field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(password, gbc);

        // Create a sub-panel to hold the buttons side by side
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        // Positioning the Button panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Add the panel to the frame
        add(panel);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return String.valueOf(password.getPassword());
    }
}

