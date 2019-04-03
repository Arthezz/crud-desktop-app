package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    private double x,y;

    @Override
    public void start(Stage stage)throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Views/login.fxml"));

        stage.setScene(new Scene(root));
        stage.getScene().setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(true);


        handle(root, stage);

        stage.show();
    }

    //drag the frame
    public void handle(Parent root, Stage stage) {
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }


    public static void main(String[] args) { launch(args); }
}
