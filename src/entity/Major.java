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

public class Major {
    private String mno;
    private String mname;
    private String columnName[] = {"专业号", "专业名"};

    public List<Major> doQuery() {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return null;
        String sql = "select * from MAJOR order by MNO asc";
        List<Major> list = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<Major>();
            do {
                Major j = new Major();
                j.mno = res.getString(1);
                j.mname = res.getString(2);
                list.add(j);
            } while (res.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps);
        return list;
    }

    public void showMajorQuery(List<Major> list, Table table) {
        table.completeClean();
        if (list == null || list.isEmpty()) {
            showMessageDialog(null,"查询内容为空！","警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        table.addTableColumn(columnName);
        for (Major item : list) {
            String[] arr = new String[2];
            arr[0] = item.mno;
            arr[1] = item.mname;
            table.getDefaultTableModel().addRow(arr);
        }
    }
}
