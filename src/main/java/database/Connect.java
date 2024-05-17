package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connect {
    // Thay đổi thông tin kết nối để sử dụng với MariaDB
    String url = "jdbc:mariadb://localhost:3306/dbsDistribution";
    String username = "root";
    String password = "123";

    public Connect() {
        super();
    }

    public Connection con() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            // JOptionPane.showMessageDialog(null, "Thành công");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return con;
    }

    public Statement stmt() {
        Statement stmt = null;
        try {
            stmt = con().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return stmt;
    }

    public ResultSet resultSet(String sqlQuery) {
        ResultSet rs = null;
        try {
            rs = stmt().executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rs;
    }
}
