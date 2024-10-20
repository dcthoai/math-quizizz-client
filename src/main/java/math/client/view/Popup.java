package math.client.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Popup {

    public static void notify(String title, String message) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new EmptyBorder(20, 0, 0, 0));
        panel.add(new JLabel(message));

        dialog.add(panel);
        dialog.setSize(300, 140);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public static void input(String title, ActionListener callback) {
        JFrame jFrame = new JFrame();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 30));

        JButton confirmButton = new JButton("OK");
        confirmButton.setPreferredSize(new Dimension(88, 28));

        confirmButton.addActionListener(event -> {
            callback.actionPerformed(new ActionEvent(textField, ActionEvent.ACTION_PERFORMED, textField.getText()));
            jFrame.dispose();
        });

        panel.add(textField);
        panel.add(confirmButton);

        jFrame.add(panel);
        jFrame.setTitle(title);
        jFrame.setSize(350, 180);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public static void confirmDialog(String title, String message, ActionListener callback) {
        JFrame jFrame = new JFrame(title);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel(message);

        JButton confirmButton = new JButton("Xác nhận");
        JButton cancelButton = new JButton("Hủy");

        confirmButton.setPreferredSize(new Dimension(88, 28));
        cancelButton.setPreferredSize(new Dimension(88, 28));

        confirmButton.addActionListener(event -> {
            callback.actionPerformed(null);
            jFrame.dispose();
        });

        cancelButton.addActionListener(event -> {
            jFrame.dispose();
        });

        // Positioning the label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        // Create a sub-panel to hold the buttons side by side
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 25));
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Positioning the Button panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        jFrame.add(panel);
        jFrame.setTitle(title);
        jFrame.setSize(350, 180);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
