package entity;

import impl.JDBCUtils;
import ui.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class College {
    private String cno;
    private String cname;

    public List<College> doQuery() {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return null;
        String sql = "select * from COLLEGE order by CNO asc";
        List<College> list = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<College>();
            do {
                College c = new College();
                c.cno = res.getString(1);
                c.cname = res.getString(2);
                list.add(c);
            } while (res.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps);
        return list;
    }

    public void showCollegeQuery(List<College> list, Table table) {
        table.completeClean();
        if (list == null || list.isEmpty()) {
            showMessageDialog(null,"查询内容为空！","警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String column[] = {"学校编号", "学校名"};
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        for (String str : column)
            tableModel.addColumn(str);
        for (College item : list) {
            String[] arr = new String[2];
            arr[0] = item.cno;
            arr[1] = item.cname;
            tableModel.addRow(arr);
        }
    }
}
