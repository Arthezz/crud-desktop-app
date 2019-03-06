package App.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

    private DbConnect() {

    }

    public static DbConnect getInstance() {

        return new DbConnect();
    }

    public Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost3306/web_employee_tracker","root","root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
