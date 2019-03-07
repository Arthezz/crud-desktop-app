package App.Controllers;

import App.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//import javax.xml.crypto.dsig.dom.DOMValidateContext;
import java.io.IOException;
import java.net.URL;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Main main = new Main();

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    @FXML
    void login(MouseEvent event) {

    }

    @FXML
    void signup(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/signup.fxml"));

        loadView(event, root, main);
/*
        Connection connection = DbConnect.getInstance().getConnection();
    try {
        String username = tf_username.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();


        Statement statement = connection.createStatement();

        int status = ((Statement) statement).executeUpdate("insert into users (username,email,password) values()");
    }  catch (SQLException e) {
    e.printStackTrace();
}*/
    }

    static void loadView(MouseEvent event, Parent root, Main main) {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

        main.handle(root,stage);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
