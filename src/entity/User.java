package entity;

import impl.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String uname;
    private String upwd;

    public User(String uname, String upwd) {
        this.uname = uname;
        this.upwd = upwd;
    }

    public int checkIsValid() {
        if (uname.isEmpty() || upwd.isEmpty()) // is null
            return -1;
        Connection conn = JDBCUtils.getConnection();
        if (conn == null)
            return -2; // database connection failed
        String sql = "select * from MYUSER where UNAME='" + uname + "' and UPWD='" + upwd + "'";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            if (!res.next()) // find user failed
                return -3;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("login success!");
        JDBCUtils.release(conn, ps);
        return 0;
    }
}
