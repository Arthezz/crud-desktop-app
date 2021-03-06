package App.Controllers;

import App.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class UniversalMethods {

     void loadView(String view, Main main, AnchorPane pane) throws IOException {

         Stage stage = (Stage) pane.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/"+view+".fxml"));

        stage.setScene(new Scene(root));

        stage.getScene().setFill(Color.TRANSPARENT);

        main.handle(root,stage);

    }

    void clearAllFields(TextField firstName, TextField lastName, TextField email, TextField city, TextField street, TextField salary){
        firstName.clear();
        lastName.clear();
        email.clear();
        city.clear();
        street.clear();
        salary.clear();
    }

    // Buttons methods
    void minimize(MouseEvent event){

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }


    void viewAdd(AnchorPane pane) throws IOException {
        loadView("addEmployee", main, pane);
    }

    void viewDelete(AnchorPane pane) throws IOException {
        loadView("deleteEmployee", main, pane);
    }

    void viewBrowse(AnchorPane pane) throws IOException {
        loadView("readDatabase", main, pane);
    }

    void viewModify(AnchorPane pane) throws IOException {
        loadView("modifyEmployee", main, pane);
    }

    void viewApp(AnchorPane pane) throws IOException {
        loadView("app", main, pane);
    }

    void signOut(AnchorPane pane) throws IOException {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Chosen option will sign out you");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            loadView("login", main, pane);
        } else {
            // user chose CANCEL or closed the dialog
        }
    }

    //Restrictions
    boolean checkTextFields(TextField firstName, TextField lastName, TextField email, TextField city, TextField street, TextField salary,
                         Text warnFName, Text warnLName, Text warnEmail, Text warnCity, Text warnStreet, Text warnSalary) {

        boolean tmp = false;
        boolean checkFName = firstName.getText().chars().allMatch(Character::isLetter);
        boolean checkLName = lastName.getText().chars().allMatch(Character::isLetter);
        boolean checkCity = city.getText().chars().allMatch(Character::isLetter);
        boolean checkSalary = salary.getText().chars().allMatch(Character::isDigit);
        boolean checkEmail = isValidEmailAddress(email.getText());
        boolean checkStreet = street.getText().matches("[a-zA-Z0-9- ]*");

        if (!checkFName){
            warnFName.setVisible(true);
        }else warnFName.setVisible(false);

        if (!checkLName){
            warnLName.setVisible(true);
        }else warnLName.setVisible(false);

        if (!checkCity){
            warnCity.setVisible(true);
        }else warnCity.setVisible(false);

        if (!checkSalary){
            warnSalary.setVisible(true);
        }else warnSalary.setVisible(false);

        if (!checkEmail && !email.getText().isEmpty()){
            warnEmail.setVisible(true);
        }else warnEmail.setVisible(false);

        if (!checkStreet){
            warnStreet.setVisible(true);
        }else warnStreet.setVisible(false);

        if (checkFName && checkLName && checkCity && checkSalary && checkEmail && checkStreet) {
             tmp = true;
        }
        return tmp;
    }

    boolean checkFulfill(TextField firstName, TextField lastName, TextField email, TextField city, TextField street, TextField salary,
                         Text warnFillAll){
        boolean tmp = false;
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() ||city.getText().isEmpty() ||
                street.getText().isEmpty() || email.getText().isEmpty() || salary.getText().isEmpty()){
            warnFillAll.setVisible(true);
            tmp = true;
        }
        return tmp;
    }

    boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

     Main main = new Main();

}
