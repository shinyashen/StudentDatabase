package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {
    public void completeClean() {
        DefaultTableModel tableModel = (DefaultTableModel) this.getModel();
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        tableModel.setColumnCount(0);
    }
}
