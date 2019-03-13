package App.Controllers;

import App.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ModifyController {


    @FXML
    void btn_close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void btn_minimize(MouseEvent event){
        universalMethods.minimize(event);
    }

    @FXML
    void btn_add(MouseEvent event) throws IOException {
        universalMethods.viewAdd(event);
    }
    @FXML
    void btn_delete(MouseEvent event) throws IOException {
        universalMethods.viewDelete(event);
    }
    @FXML
    void btn_browse(MouseEvent event) throws IOException {
        universalMethods.viewBrowse(event);
    }
    @FXML
    void btn_modify(MouseEvent event) throws IOException {
        universalMethods.viewModify(event);
    }
    @FXML
    public void btn_back(MouseEvent event) throws IOException {
        universalMethods.viewApp(event);
    }
    @FXML
    public void btn_signOut(MouseEvent event) throws IOException {
        universalMethods.signOut(event);
    }

    public void btn_update(MouseEvent event) {
    }

    UniversalMethods universalMethods = new UniversalMethods();
    Main main = new Main();

    @FXML
    public TextField firstName, lastName, email, city, street, salary;


}
