package App.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {

    private DbConnect() {

    }

    public static DbConnect getInstance() {

        return new DbConnect();
    }

    public Connection getConnection() {

        String jdbcUrl = "jdbc:mysql://localhost:3306/user_tracker?useSSL=false&serverTimezone=UTC";
        String user = "admin";
        String pass = "admin";

        Connection myConn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConn = DriverManager.getConnection(jdbcUrl, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myConn;
    }
}
