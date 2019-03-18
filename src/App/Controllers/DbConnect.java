package App.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {

    private DbConnect() { }

    public static DbConnect getInstance() { return new DbConnect(); }

    public Connection getConnection(String jdbcUrl) {

        String user = "user46997";
        String pass = "8xe8fo660v8ji2ac";

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
