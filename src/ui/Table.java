package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {
    public void completeClean() {
        DefaultTableModel tableModel = this.getDefaultTableModel();
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        tableModel.setColumnCount(0);
    }

    public void addTableColumn(String list[]) {
        DefaultTableModel tableModel = this.getDefaultTableModel();
        for (String str : list)
            tableModel.addColumn(str);
    }

    public DefaultTableModel getDefaultTableModel() {
        return (DefaultTableModel) this.getModel();
    }
}
