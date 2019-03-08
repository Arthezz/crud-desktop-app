package App.Controllers;

import App.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController {

    public TextField firstName, lastName, email, city, street, salary;
    
    LoginController loginController = new LoginController();
    Main main = new Main();

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
    void btn_add(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/addEmployee.fxml"));

        loginController.loadView(event, root, main);
    }
    @FXML
    void btn_delete(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/deleteEmployee.fxml"));

        loginController.loadView(event, root, main);
    }
    @FXML
    void btn_browse(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/readDatabase.fxml"));

        loginController.loadView(event, root, main);
    }
    @FXML
    void btn_modify(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/modifyEmployee.fxml"));

        loginController.loadView(event, root, main);
    }

    public void newEmployee(MouseEvent event) {

    }

    public void btn_back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/app.fxml"));

        loginController.loadView(event, root, main);
    }
}
