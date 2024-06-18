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
    private String columnName[] = {"学校编号", "学校名"};

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
            list = new ArrayList<>();
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
}
