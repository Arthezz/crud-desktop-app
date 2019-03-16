package App.Controllers;

import App.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private FontAwesomeIconView addedProperly;

    @FXML
    private Text fillAll, emailExists, userExists;

    @FXML
    void btn_close(MouseEvent event) { System.exit(0); }

    @FXML
    void btn_minimize(MouseEvent event){ universalMethods.minimize(event); }

    @FXML
    void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/login.fxml"));

        universalMethods.loadView(event, root, main);

    }

    @FXML
    void signup(MouseEvent event) throws SQLException {

        addedProperly.setVisible(false);
        fillAll.setVisible(false);
        userExists.setVisible(false);
        emailExists.setVisible(false);

        boolean checkEmail = universalMethods.isValidEmailAddress(tf_email.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/user_tracker?useSSL=false&serverTimezone=UTC";

        Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);

        Statement statement = myConn.createStatement();

        ResultSet resultName = statement.executeQuery("select * from user where binary username" +
                " =  '" + tf_username.getText() + "'" );
        if (!resultName.next()) {
            
            ResultSet resultEmail = statement.executeQuery("select * from user where binary email" + " =  " +
                    "'" + tf_email.getText() + "'");

            if (!resultEmail.next()) {

                int status = (statement).executeUpdate("insert into user (username,email,password)" + "" +
                        "values('" + tf_username.getText() + "', '" + tf_email.getText() + "', '" + pf_password.getText() + "')");

                if (status > 0) {
                    addedProperly.setVisible(true);
                    fillAll.setVisible(false);
                    universalMethods.clearAllFields(tf_username, tf_email, pf_password, null,null,null); //Not sure if it's a good practice
                    System.out.println("user registered");
                }
            } else emailExists.setVisible(true);

        }else userExists.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    Main main = new Main();
    UniversalMethods universalMethods = new UniversalMethods();

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField pf_password;
}
