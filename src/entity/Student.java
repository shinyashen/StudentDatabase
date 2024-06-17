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

public class Student {
    private String sno;
    private String sname;
    private String sgender;
    private String mno;

    public List<Student> queryAll() {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return null;
        String sql = "select * from STUDENT order by SNO asc";
        List<Student> list = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<Student>();
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
        System.out.println("query 0 success!");
        JDBCUtils.release(conn, ps);
        return list;
    }

    public List<Student> querySpecific(String sno) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from STUDENT where SNO = '" + sno + "'";
        List<Student> list = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<Student>();
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
        System.out.println("query 4 success!");
        return list;
    }

    public void showStudentQuery(List<Student> list, Table table) {
        table.completeClean();
        if (list == null || list.isEmpty()) {
            showMessageDialog(null,"查询内容为空！","警告",JOptionPane.WARNING_MESSAGE);
            return;
        }
        String column[] = {"学号", "姓名", "性别", "专业号"};
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        for (String str : column)
            tableModel.addColumn(str);
        for (Student item : list) {
            String[] arr = new String[4];
            arr[0] = item.sno;
            arr[1] = item.sname;
            arr[2] = (item.sgender.equals("M")) ? "男" : "女";
            arr[3] = item.mno;
            tableModel.addRow(arr);
        }
    }
}
