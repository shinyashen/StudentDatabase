package impl;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtils {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:ORACLE";
            String username = "shinya";
            String password = "shinya135002";
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            return null;
        }
        return conn;
    }

    public static void release(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(Connection conn, PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        release(conn);
    }

    public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        release(conn, ps);
    }
}
