module DMA {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports App;
    opens App.Controllers;
}
