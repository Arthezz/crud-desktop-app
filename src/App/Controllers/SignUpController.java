package App.Controllers;

import App.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    Main main = new Main();

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField pf_password;

    @FXML
    void btn_close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void btn_minimize(MouseEvent event){

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/login.fxml"));

        LoginController.loadView(event, root, main);

    }

    @FXML
    void signup(MouseEvent event) {

    try {

        String username = tf_username.getText();
        String email = tf_email.getText();
        String password = pf_password.getText();

        Connection myConn = DbConnect.getInstance().getConnection();

        Statement statement = myConn.createStatement();

        int status = (statement).executeUpdate("insert into user (username,email,password)" + " values('"+ username +"', '"+ email +"', '" + password + "')");


        if(status > 0) {
            System.out.println("user registered");
        }

    }  catch (Exception e) {
    e.printStackTrace();
}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
