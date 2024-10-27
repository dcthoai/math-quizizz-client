package math.client.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;

public class RoomListView extends AbstractView {

    private JTable rooms;
    private static final RoomListView instance = new RoomListView();

    public static RoomListView getInstance() {
        return instance;
    }

    private RoomListView() {
        super("Room List", 300, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    private void generateView() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel labelRoomList = new JLabel("Danh sách phòng trống");
        labelRoomList.setFont(new Font("Roboto", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panel.add(labelRoomList, gbc);

        //Test data
        String[] columnNames = {"ID Phòng", ""};

        Object[][] data = {
                {"Room 1", "3/5"},
                {"Room 2", "1/5"},
                {"Room 3", "5/5"},
                {"Room 4", "2/5"}
        };

        rooms = new JTable(new DefaultTableModel(data, columnNames));
        rooms.setFillsViewportHeight(true);
        rooms.setDefaultEditor(Object.class, null); // Ngăn chỉnh sửa nội dung
        rooms.setBorder(null);
        rooms.setRowHeight(30);
        rooms.setFocusable(false);

        rooms.setFont(new Font("Roboto", Font.PLAIN, 15));
        rooms.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 13));
        rooms.setBackground(new Color(255, 255, 255));
        rooms.getTableHeader().setBackground(Color.LIGHT_GRAY);

        rooms.getColumnModel().getColumn(0).setMinWidth(60);
        rooms.getColumnModel().getColumn(0).setMaxWidth(150);
        rooms.getColumnModel().getColumn(1).setMinWidth(60);
        rooms.getColumnModel().getColumn(1).setMaxWidth(150);

        rooms.getTableHeader().setReorderingAllowed(false);
        rooms.getColumnModel().getColumn(0).setResizable(false);
        rooms.getColumnModel().getColumn(1).setResizable(false);

        // Căn giữa nội dung trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            rooms.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(rooms);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(scrollPane, gbc);

        getContentPane().add(panel);
    }

    public JTable getRoomList() {
        return rooms;
    }
}
