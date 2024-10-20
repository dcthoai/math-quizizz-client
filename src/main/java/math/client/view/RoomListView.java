package math.client.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomListView extends AbstractView {
    private List<String> roomList; // Danh sách phòng
    private DefaultTableModel tableModel;

    public RoomListView() {
        super("Room List", 300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        generateView();
    }

    public void generateView() {
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

        tableModel = new DefaultTableModel(data, columnNames);
        JTable roomTable = new JTable(tableModel);
        roomTable.setFillsViewportHeight(true);
        roomTable.setDefaultEditor(Object.class, null); // Ngăn chỉnh sửa nội dung
        roomTable.setBorder(null);
        roomTable.setRowHeight(30);
        roomTable.setFocusable(false);

        roomTable.setFont(new Font("Roboto", Font.PLAIN, 15));
        roomTable.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 13));
        roomTable.setBackground(new Color(255, 255, 255));
        roomTable.getTableHeader().setBackground(Color.LIGHT_GRAY);

        // Thiết lập độ rộng cột
        roomTable.getColumnModel().getColumn(0).setMinWidth(60);
        roomTable.getColumnModel().getColumn(0).setMaxWidth(150);
        roomTable.getColumnModel().getColumn(1).setMinWidth(60);
        roomTable.getColumnModel().getColumn(1).setMaxWidth(150);

        roomTable.getTableHeader().setReorderingAllowed(false);
        roomTable.getColumnModel().getColumn(0).setResizable(false);
        roomTable.getColumnModel().getColumn(1).setResizable(false);

        roomTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = roomTable.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    String roomId = (String) roomTable.getValueAt(row, 0);
                    System.out.println("Click on " + roomId);
                    // Thêm logic để xử lý sự kiện click vào phòng (VD: tham gia phòng)
                }
            }
        });

        // Căn giữa nội dung trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            roomTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(roomTable);

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

    public void updateRoomList(List<String> roomList) {
        this.roomList = roomList;
        tableModel.setRowCount(0);
        // Cập nhật dữ liệu vào bảng
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RoomListView view = new RoomListView();
                view.setVisible(true);
            }
        });
    }
}
