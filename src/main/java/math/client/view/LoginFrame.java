package math.client.view;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import math.client.controller.UserController;

/**
 *
 * @author dctho
 */
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private UserController userController;

    public LoginFrame(UserController userController) {
        this.userController = userController;

        // Thiết lập tiêu đề và kích thước cho frame
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Thiết lập layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Thêm các thành phần giao diện
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                userController.login(username, password);
            }
        });
        
        add(panel);
    }

    public void display() {
        setVisible(true);
    }
}
