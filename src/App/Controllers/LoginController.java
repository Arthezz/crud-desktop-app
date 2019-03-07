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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Main main = new Main();

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    @FXML
    void login(MouseEvent event) throws IOException, SQLException {

        String username = tf_username.getText();
        String password = pf_password.getText();

        Connection myConn = DbConnect.getInstance().getConnection();

        Statement statement = myConn.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from user where username" +
                " =  '" + username + "' or email = '" + pf_password + "' and password = '" + password + "'" );

        if (resultSet.next()) {

            Parent root = FXMLLoader.load(getClass().getResource("/App/Views/app.fxml"));

            loadView(event, root, main);
        }
    }

    @FXML
    void signup(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/signup.fxml"));

        loadView(event, root, main);

    }

    static void loadView(MouseEvent event, Parent root, Main main) {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

        stage.getScene().setFill(Color.TRANSPARENT);

        main.handle(root,stage);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
