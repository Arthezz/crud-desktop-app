package App.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AppController {

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
}
