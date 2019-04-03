package App.Controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AppController {

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
        universalMethods.viewAdd(pane);
    }
    @FXML
    void btn_delete(MouseEvent event) throws IOException {
        universalMethods.viewDelete(pane);
    }
    @FXML
    void btn_browse(MouseEvent event) throws IOException {
        universalMethods.viewBrowse(pane);
    }
    @FXML
    void btn_modify(MouseEvent event) throws IOException {
        universalMethods.viewModify(pane);
    }

    @FXML
    public void btn_signOut(MouseEvent event) throws IOException {
        universalMethods.signOut(pane);
    }

    private UniversalMethods universalMethods = new UniversalMethods();

    @FXML
    private AnchorPane pane;
}
