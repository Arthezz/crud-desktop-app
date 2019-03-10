package App.Controllers;

import App.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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

        addedProperlyThumb.setVisible(false);
        addedProperly.setVisible(false);
        warnFillAll.setVisible(false);
        empExists.setVisible(false);

        boolean checkFName = firstName.getText().chars().allMatch(Character::isLetter);
        boolean checkLName = lastName.getText().chars().allMatch(Character::isLetter);
        boolean checkCity = city.getText().chars().allMatch(Character::isLetter);
        boolean checkSalary = salary.getText().chars().allMatch(Character::isDigit);
        boolean checkEmail = isValidEmailAddress(email.getText());
        boolean checkStreet = salary.getText().matches("[a-zA-Z0-9]*");
        boolean empty = false;

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

        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() ||city.getText().isEmpty() ||
                street.getText().isEmpty() || email.getText().isEmpty() || salary.getText().isEmpty()){
            warnFillAll.setVisible(true);
            empty = true;

        }

        if (checkFName && checkLName && checkCity && checkSalary && checkEmail && checkStreet && !empty){

        try {
            Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);

            Statement statement = myConn.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from employee where binary first_name" +
                    " =  '" + firstName.getText() + "'AND binary last_name = '" + lastName.getText() + "'" +
                    "AND binary email = '" + email.getText() + "'AND binary city = '" + city.getText() + "'AND" +
                    " binary street = '" + street.getText() + "'AND salary = '" + salary.getText() + "'" );

            if (!resultSet.next()) {

                int status = (statement).executeUpdate("insert into employee (first_name,last_name,email,city,street,salary)" + " values('" + firstName.getText() + "', '" + lastName.getText() + "'," +
                        " '" + email.getText() + "', '" + city.getText() + "', '" + street.getText() + "', '" + salary.getText() + "')");

                if (status > 0) {
                    addedProperlyThumb.setVisible(true);
                    addedProperly.setVisible(true);
                    warnFillAll.setVisible(false);
                }

            }else empExists.setVisible(true);

        }  catch (Exception e) {
            e.printStackTrace();
        }}

    }

    public void btn_back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/App/Views/app.fxml"));

        loginController.loadView(event, root, main);
    }

    public void btn_signOut(MouseEvent event) throws IOException {
        appController.btn_signOut(event);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @FXML
    public TextField firstName, lastName, email, city, street, salary;

    @FXML
    public Text warnFName, warnLName, warnEmail, warnCity, warnStreet, warnSalary, warnFillAll, addedProperly, empExists;

    @FXML
    public FontAwesomeIconView addedProperlyThumb;

    LoginController loginController = new LoginController();
    AppController appController = new AppController();
    Main main = new Main();
}
