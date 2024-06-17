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

public class SMC {
    private String sname;
    private String cname;
    private String mname;

    public List<SMC> queryAll() {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return null;
        String sql = "select A.SNAME, B.CNAME, C.MNAME from STUDENT A, COLLEGE B, MAJOR C where exists(select * from SMC D where A.SNO=D.SNO and B.CNO=D.CNO and C.MNO=D.TARGETMNO)";
        List<SMC> list = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<SMC>();
            do {
                SMC smc = new SMC();
                smc.sname = res.getString(1);
                smc.cname = res.getString(2);
                smc.mname = res.getString(3);
                list.add(smc);
            } while (res.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("query 3 success!");
        JDBCUtils.release(conn, ps);
        return list;
    }

    public void showTotalQuery(List<SMC> list, Table table) {
        table.completeClean();
        if (list == null || list.isEmpty()) {
            showMessageDialog(null,"查询内容为空！","警告", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String column[] = {"姓名", "目标学校名", "目标专业名"};
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        for (String str : column)
            tableModel.addColumn(str);
        for (SMC item : list) {
            String[] arr = new String[3];
            arr[0] = item.sname;
            arr[1] = item.cname;
            arr[2] = item.mname;
            tableModel.addRow(arr);
        }
    }
}
