package entity;

import impl.JDBCUtils;
import ui.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Major extends Entity {
    private String mno;
    private String mname;
    private String[] columnName = {"专业号", "专业名"};

    public List<Major> doQuery() {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return null;
        String sql = "select * from MAJOR order by MNO asc";
        List<Major> list = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<>();
            do {
                Major j = new Major();
                j.mno = res.getString(1);
                j.mname = res.getString(2);
                list.add(j);
            } while (res.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, res);
        return list;
    }

    public void showMajorQuery(List<Major> list, Table table) {
        table.completeClean();
        if (isPrintListEmpty(list))
            return;
        table.addTableColumn(columnName);
        for (Major item : list) {
            String[] arr = new String[2];
            arr[0] = item.mno;
            arr[1] = item.mname;
            table.getDefaultTableModel().addRow(arr);
        }
    }

    public int doEdit(String mno, String mname, int actionType) {
        Connection conn = JDBCUtils.getConnection();
        String sql = null;
        switch (actionType) {
            case 0:
                sql = "insert into MAJOR values('" + mno + "','" + mname + "')";
                System.out.println(sql);
                break;
            case 1:
                sql = "delete from MAJOR where MNO='" + mno + "'";
                break;
            case 2:
                sql = "update MAJOR set MNO='" + mno + "',MNAME='" + mname + "' where MNO='" + mno + "'";
                break;
            default:
                break; // do nothing
        }
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            if (ps.executeUpdate(sql) < 1) // empty
                return -1;
        } catch (SQLException e) {
            return -2;
        }
        JDBCUtils.release(conn, ps);
        return 0;
    }

    public String getMno() {
        return mno;
    }

    public String getMname() {
        return mname;
    }
}
