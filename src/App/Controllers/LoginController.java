package App.Controllers;

import App.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    void btn_close(MouseEvent event) { System.exit(0); }

    @FXML
    void btn_minimize(MouseEvent event){
        universalMethods.minimize(event);
    }

    @FXML
    public void enterPressed(KeyEvent e) throws IOException, SQLException {
        if (e.getCode().equals(KeyCode.ENTER))
        {
            login();
        }
    }
    @FXML
    void login() throws IOException, SQLException {

        String jdbcUrl = "jdbc:mysql://db4free.net:3306/cruddesktopapp";

        String username = tf_username.getText();
        String password = pf_password.getText();

        Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);

        Statement statement = myConn.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from user where binary username" +
                " =  '" + username + "' and binary password = '" + password + "'" );

        if (resultSet.next()) {
            universalMethods.loadView("app", main, pane);
        }else {
            warnUsrPswd.setVisible(true);
        }
    }

    @FXML
    void signup(MouseEvent event) throws IOException {
        universalMethods.loadView("signUp", main, pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    Main main = new Main();
    UniversalMethods universalMethods = new UniversalMethods();

    @FXML
    private TextField tf_username;

    @FXML
    private Text warnUsrPswd;

    @FXML
    private PasswordField pf_password;

    @FXML
    private AnchorPane pane;

}
