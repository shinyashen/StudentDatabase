package entity;

import impl.JDBCUtils;
import ui.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class College extends Entity {
    private String cno;
    private String cname;
    private String[] columnName = {"学校编号", "学校名"};

    public List<College> doQuery(String input, searchType type) {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return null;
        String sql = null;
        switch (type) {
            case ALL:
                sql = "select * from COLLEGE order by CNO asc";
                break;
            case CNO:
                sql = "select * from COLLEGE where CNO='" + input + "'";
                break;
            default:
                break; // do nothing
        }
        List<College> list = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<>();
            do {
                College c = new College();
                c.cno = res.getString(1).trim();
                c.cname = res.getString(2).trim();
                list.add(c);
            } while (res.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, res);
        return list;
    }

    public void showCollegeQuery(List<College> list, Table table) {
        table.completeClean();
        if (isPrintListEmpty(list))
            return;
        table.addTableColumn(columnName);
        for (College item : list) {
            String[] arr = new String[2];
            arr[0] = item.cno;
            arr[1] = item.cname;
            table.getDefaultTableModel().addRow(arr);
        }
    }

    public int doEdit(String cno, String cname, int actionType) {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return -1;
        String sql = null;
        switch (actionType) {
            case 0:
                sql = "insert into COLLEGE values('" + cno + "','" + cname + "')";
                break;
            case 1:
                sql = "delete from COLLEGE where CNO='" + cno + "'";
                break;
            case 2:
                sql = "update COLLEGE set CNO='" + cno + "',CNAME='" + cname + "' where CNO='" + cno + "'";
                break;
            default:
                break; // do nothing
        }
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            if (ps.executeUpdate(sql) < 1) // empty
                return -2;
        } catch (SQLException e) {
            return -3;
        }
        JDBCUtils.release(conn, ps);
        return 0;
    }

    public String getCno() {
        return cno;
    }

    public String getCname() {
        return cname;
    }
}
