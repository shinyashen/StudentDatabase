package entity;

import impl.JDBCUtils;
import ui.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SMC extends Entity {
    private String sname;
    private String cname;
    private String mname;
    private String[] columnName = {"姓名", "目标学校名", "目标专业名"};

    public List<SMC> doQuery(String input, searchType type) {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return null;
        String sql = null;
        switch (type) {
            case ALL:
                sql = "select A.SNAME, B.CNAME, C.MNAME from STUDENT A, COLLEGE B, MAJOR C where exists(select * from SMC D where A.SNO=D.SNO and B.CNO=D.CNO and C.MNO=D.TARGETMNO) order by A.SNO asc";
                break;
            case SNO:
            case CNO:
            case TARGETMNO:
                sql = "select A.SNAME, B.CNAME, C.MNAME from STUDENT A, COLLEGE B, MAJOR C where exists(select * from SMC D where A.SNO=D.SNO and B.CNO=D.CNO and C.MNO=D.TARGETMNO and D." + type + "='" + input + "') order by A.SNO asc";
                break;
            case MNAME:
                sql = "select A.SNAME, B.CNAME, C.MNAME from STUDENT A, COLLEGE B, MAJOR C where exists(select * from SMC D where A.SNO=D.SNO and B.CNO=D.CNO and C.MNO=D.TARGETMNO) and C." + type + "='" + input + "' order by A.SNO asc";
                break;
            case SPECIFIC:
                sql = "select A.SNAME, B.CNAME, C.MNAME from STUDENT A, COLLEGE B, MAJOR C where exists(select * from SMC D where A.SNO=D.SNO and B.CNO=D.CNO and C.MNO=D.TARGETMNO and A.MNO<>D.TARGETMNO) order by A.SNO asc";
                break;
            case ALLNO:
                sql = "select * from SMC where SNO='" + input + "'";
                break;
            default:
                break; // do nothing
        }
        List<SMC> list = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
            if (!res.next()) // empty
                return null;
            list = new ArrayList<>();
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
        JDBCUtils.release(conn, ps, res);
        return list;
    }

    public void showTotalQuery(List<SMC> list, Table table) {
        table.completeClean();
        if (isPrintListEmpty(list))
            return;
        table.addTableColumn(columnName);
        for (SMC item : list) {
            String[] arr = new String[3];
            arr[0] = item.sname;
            arr[1] = item.cname;
            arr[2] = item.mname;
            table.getDefaultTableModel().addRow(arr);
        }
    }

    public int doEdit(String sno, String cno, String mno, int actionType) {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return -1;
        String sql = null;
        switch (actionType) {
            case 0:
                sql = "insert into SMC values('" + sno + "','" + cno + "','" + mno + "')";
                System.out.println(sql);
                break;
            case 1:
                sql = "delete from SMC where SNO='" + sno + "'";
                break;
            case 2:
                sql = "update SMC set SNO='" + sno + "',CNO='" + cno + "',TARGETMNO='" + mno + "' where SNO='" + sno + "'";
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

    public String getSname() {
        return sname;
    }

    public String getCname() {
        return cname;
    }

    public String getMname() {
        return mname;
    }
}
