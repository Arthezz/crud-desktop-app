package App.Controllers;

import App.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Text warnUsrPswd;

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
    void login(MouseEvent event) throws IOException, SQLException {

        String jdbcUrl = "jdbc:mysql://localhost:3306/user_tracker?useSSL=false&serverTimezone=UTC";

        String username = tf_username.getText();
        String password = pf_password.getText();

        Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);

        Statement statement = myConn.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from user where binary username" +
                " =  '" + username + "' and binary password = '" + password + "'" );

        if (resultSet.next()) {

            Parent root = FXMLLoader.load(getClass().getResource("/App/Views/app.fxml"));

            loadView(event, root, main);
        }else {
            warnUsrPswd.setVisible(true);
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
