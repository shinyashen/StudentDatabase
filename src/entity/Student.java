package entity;

import impl.JDBCUtils;
import ui.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student extends Entity {
    private String sno;
    private String sname;
    private String sgender;
    private String mno;
    private String[] columnName = {"学号", "姓名", "性别", "专业号"};

    public List<Student> doQuery(String input, searchType type) {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return null;
        String sql = null;
        switch (type) {
            case ALL:
                sql = "select * from STUDENT order by SNO asc";
                break;
            case SNO:
                sql = "select * from STUDENT where SNO='" + input + "'";
                break;
            default:
                break; // do nothing
        }
        List<Student> list = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<>();
            do {
                Student s = new Student();
                s.sno = res.getString(1).trim();
                s.sname = res.getString(2).trim();
                s.sgender = res.getString(3).trim();
                s.mno = res.getString(4).trim();
                list.add(s);
            } while (res.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JDBCUtils.release(conn, ps, res);
        return list;
    }

    public void showStudentQuery(List<Student> list, Table table) {
        table.completeClean();
        if (isPrintListEmpty(list))
            return;
        table.addTableColumn(columnName);
        for (Student item : list) {
            String[] arr = new String[4];
            arr[0] = item.sno;
            arr[1] = item.sname;
            arr[2] = (item.sgender.equals("M")) ? "男" : "女";
            arr[3] = item.mno;
            table.getDefaultTableModel().addRow(arr);
        }
    }

    public int doEdit(String sno, String sname, String sgender, String mno, int actionType) {
        String transgender = sgender;
        if (!(transgender == null) && !transgender.isEmpty()) {
            if (transgender.equals("男"))
                transgender = "M";
            if (transgender.equals("女"))
                transgender = "F";
        }
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return -1;
        String sql = null;
        switch (actionType) {
            case 0:
                sql = "insert into STUDENT values('" + sno + "','" + sname + "','" + transgender + "','" + mno + "')";
                break;
            case 1:
                sql = "delete from STUDENT where SNO='" + sno + "'";
                break;
            case 2:
                sql = "update STUDENT set SNO='" + sno + "',SNAME='" + sname + "',SGENDER='" + transgender + "',MNO='" + mno + "' where SNO='" + sno + "'";
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

    public String getSno() {
        return sno;
    }

    public String getSname() {
        return sname;
    }

    public String getGender() {
        return sgender;
    }

    public String getMno() {
        return mno;
    }
}
