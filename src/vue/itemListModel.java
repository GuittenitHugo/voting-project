package vue;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class itemListModel extends DefaultTableModel {

    public itemListModel() {
    }

    public itemListModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public itemListModel(Vector<?> columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public itemListModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public itemListModel(Vector<? extends Vector> data, Vector<?> columnNames) {
        super(data, columnNames);
    }

    public itemListModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
