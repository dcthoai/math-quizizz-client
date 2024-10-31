package math.client.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import java.util.Objects;

public class RegisterView extends AbstractView {

    private JButton registerButton;
    private JButton backButton;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField rePassword;
    private static final RegisterView instance = new RegisterView();

    public static RegisterView getInstance() {
        return instance;
    }

    private RegisterView() {
        super("Đăng ký", 500, 320);
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
        JLabel repeatPasswordLabel = new JLabel("Re-password:");

        username = new JTextField(20);
        password = new JPasswordField(20);
        rePassword = new JPasswordField(20);

        username.setPreferredSize(new Dimension(250, 30));
        password.setPreferredSize(new Dimension(250, 30));
        rePassword.setPreferredSize(new Dimension(250, 30));

        backButton = new JButton("Quay lại");
        registerButton = new JButton("Đăng ký");

        backButton.setPreferredSize(new Dimension(100, 30));
        registerButton.setPreferredSize(new Dimension(100, 30));

        // Positioning the Username label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 10, 15);  // Padding
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

        // Positioning the Re-password label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.WEST;
        panel.add(repeatPasswordLabel, gbc);

        // Positioning the Password text field
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(rePassword, gbc);

        // Create a sub-panel to hold the buttons side by side
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.add(backButton);
        buttonPanel.add(registerButton);

        // Positioning the Button panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Add the panel to the frame
        add(panel);
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return String.valueOf(password.getPassword());
    }

    public boolean validateRePassword() {
        return Objects.equals(getPassword(), String.valueOf(rePassword.getPassword()));
    }
}
