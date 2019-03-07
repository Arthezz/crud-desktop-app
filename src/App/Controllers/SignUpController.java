package App.Controllers;

import App.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    Main main = new Main();

    private double a,b;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField pf_password;

    @FXML
    void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/login.fxml"));

        LoginController.loadView(event, root, main);


    }

    @FXML
    void signup(MouseEvent event) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/user_tracker?useSSL=false&serverTimezone=UTC";
        String user = "admin";
        String pass = "admin";

    try {
/*
        String username = tf_username.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();
*/
        System.out.println("connecting to: " + jdbcUrl );

        Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

        System.out.println("successful");

        /*Statement statement = connection.createStatement();

        int status = ((Statement) statement).executeUpdate("insert into users (username,email,password) values()");

        connection = DbConnect.getInstance().getConnection();
        if(connection != null) {
            System.out.println("established");
        }*/

    }  catch (Exception e) {
    e.printStackTrace();
}
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
