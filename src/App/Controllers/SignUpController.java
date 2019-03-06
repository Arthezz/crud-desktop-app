package App.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField pf_password;

    @FXML
    void login(MouseEvent event2) {

    }

    @FXML
    void signup(MouseEvent event) {

        Connection connection = null;

    try {
        /*
        String username = tf_username.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();


        Statement statement = connection.createStatement();

        int status = ((Statement) statement).executeUpdate("insert into users (username,email,password) values()");
        */
        connection = DbConnect.getInstance().getConnection();
        if(connection != null) {
            System.out.println("established");
        }

    }  catch (Exception e) {
    e.printStackTrace();
}
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
