package math.client.view;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public class FriendRequestComponent {

    private final JPanel panel = new JPanel();

    public FriendRequestComponent(String username) {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        JLabel usernameLabel = new JLabel(username);
        usernameLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(new ButtonStyle("Chấp nhận", 105, 25), gbc);

        gbc.gridx = 2;
        gbc.insets = new Insets(10, 10, 0, 10);
        panel.add(new ButtonStyle("Từ chối", 85, 25), gbc);

        panel.setBackground(Color.WHITE);
    }

    public JPanel getPanel() {
        return panel;
    }

    public static JList<JPanel> getListView(List<String> usernames) {
        DefaultListModel<JPanel> model = new DefaultListModel<>();

        for (String username : usernames) {
            FriendRequestComponent requestComponent = new FriendRequestComponent(username);
            model.addElement(requestComponent.getPanel());
        }

        JList<JPanel> listView = new JList<>(model);

        listView.setBorder(new EmptyBorder(0, 0, 15, 0));
        listView.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            value.setPreferredSize(new Dimension(list.getWidth(), value.getPreferredSize().height));
            return value;
        });

        return listView;
    }
}
