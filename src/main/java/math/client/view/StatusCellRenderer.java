package math.client.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class StatusCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 3) {
            if (value.equals("Online")) {
                cell.setForeground(new Color(0, 120, 0));
                cell.setFont(cell.getFont().deriveFont(Font.PLAIN));
            } else if (value.equals("Offline")) {
                cell.setForeground(Color.GRAY);
                cell.setFont(cell.getFont().deriveFont(Font.ITALIC));
            } else {
                cell.setForeground(Color.BLACK);
                cell.setFont(cell.getFont().deriveFont(Font.PLAIN));
            }
        } else {
            cell.setForeground(Color.BLACK);
            cell.setFont(cell.getFont().deriveFont(Font.PLAIN));
        }

        return cell;
    }
}
