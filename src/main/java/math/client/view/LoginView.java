package math.client.view;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author dcthoai
 */
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
        super("Đăng nhập", 500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // Positioning the Username label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 15, 10, 15);  // Padding
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(userLabel, gbc);

        // Positioning the User text field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(username, gbc);

        // Positioning the Password label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.WEST;
        panel.add(passwordLabel, gbc);

        // Positioning the Password text field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(password, gbc);

        // Create a sub-panel to hold the buttons side by side
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        // Positioning the Button panel
        gbc.gridx = 0;
        gbc.gridy = 2;
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

