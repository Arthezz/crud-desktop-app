package App.Controllers;

import App.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class AddController {

    @FXML
    void btn_close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void btn_minimize(MouseEvent event){
        appController.btn_minimize(event);
    }

    @FXML
    void btn_add(MouseEvent event) throws IOException {
        appController.btn_add(event);
    }
    @FXML
    void btn_delete(MouseEvent event) throws IOException {
        appController.btn_delete(event);
    }
    @FXML
    void btn_browse(MouseEvent event) throws IOException {
        appController.btn_browse(event);
    }
    @FXML
    void btn_modify(MouseEvent event) throws IOException {
        appController.btn_modify(event);
    }

    public void newEmployee(MouseEvent event) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/employee_tracker?useSSL=false&serverTimezone=UTC";
        try {
            Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);

            Statement statement = myConn.createStatement();

            int status = (statement).executeUpdate("insert into employee (first_name,last_name,email,city,street,salary)" + " values('"+ firstName.getText()+"', '"+ lastName.getText() +"'," +
                    " '" + email.getText() + "', '" + city.getText() + "', '" + street.getText()  + "', '" + salary.getText() + "')");

            if(status > 0) {
                System.out.println("employee added");
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btn_back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/app.fxml"));

        loginController.loadView(event, root, main);
    }

    public void btn_signOut(MouseEvent event) throws IOException {
        appController.btn_signOut(event);
    }

    @FXML
    public TextField firstName, lastName, email, city, street, salary;

    @FXML
    public Text warnFName, warnLName, warnEmail, warnCity, warnStreet, warnSalary;

    LoginController loginController = new LoginController();
    AppController appController = new AppController();
    Main main = new Main();
}
