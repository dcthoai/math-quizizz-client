package math.client.view;

import math.client.dto.response.Room;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;

import java.util.List;

public class RoomListView extends AbstractView {

    private JTable rooms;
    private JButton searchRoomButton;
    private DefaultTableModel tableModel;
    private static final RoomListView instance = new RoomListView();

    public static RoomListView getInstance() {
        return instance;
    }

    private RoomListView() {
        super("Room List", 500, 400);
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
        gbc.insets = new Insets(15, 10, 10, 10);
        panel.add(labelRoomList, gbc);

        searchRoomButton = new ButtonStyle("Tìm phòng", 100, 30);
        searchRoomButton.setFont(new Font("Roboto", Font.BOLD, 13));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 0, 20);
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(searchRoomButton, gbc);

        String[] columnNames = {"ID Phòng", "Thành viên", "Trạng thái"};

        tableModel = new DefaultTableModel(null, columnNames);
        rooms = new JTable(tableModel);
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
        rooms.getColumnModel().getColumn(2).setMinWidth(60);
        rooms.getColumnModel().getColumn(2).setMaxWidth(250);

        rooms.getTableHeader().setReorderingAllowed(false);
        rooms.getColumnModel().getColumn(0).setResizable(false);
        rooms.getColumnModel().getColumn(1).setResizable(false);
        rooms.getColumnModel().getColumn(2).setResizable(false);

        // Center table content
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < columnNames.length; i++) {
            rooms.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(rooms);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(20, 20, 20, 20);
        panel.add(scrollPane, gbc);

        getContentPane().add(panel);
    }

    public JTable getRoomList() {
        return rooms;
    }

    public JButton getSearchRoomButton() {
        return searchRoomButton;
    }

    public void updateListRooms(List<Room> rooms) {
        tableModel.setRowCount(0);

        rooms.forEach(room -> {
            String status = room.getPlayingGame() ? "Đang trong trận" : "Đang chờ";
            String[] newRow = { room.getRoomID(), room.getUsers().size() + "/5", status };
            tableModel.addRow(newRow);
        });

        tableModel.fireTableDataChanged();
    }
}
