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

    private String columnName[] = {"学号", "姓名", "性别", "专业号"};

    public List<Student> doQuery(String input, searchType type) {
        Connection conn = JDBCUtils.getConnection();
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
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<>();
            do {
                Student s = new Student();
                s.sno = res.getString(1);
                s.sname = res.getString(2);
                s.sgender = res.getString(3);
                s.mno = res.getString(4);
                list.add(s);
            } while (res.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
