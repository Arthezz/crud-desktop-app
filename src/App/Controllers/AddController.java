package App.Controllers;

import App.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
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

    public void newEmployee(MouseEvent event) {

        addedProperlyThumb.setVisible(false);
        addedProperly.setVisible(false);
        warnFillAll.setVisible(false);
        empExists.setVisible(false);

        boolean check;
        boolean empty;

        check = universalMethods.checkTextFields(firstName, lastName, email, city, street, salary,
                warnFName, warnLName, warnEmail, warnCity, warnStreet, warnSalary);

        empty = universalMethods.checkFulfill(firstName, lastName, email, city, street, salary, warnFillAll);

        if (check && !empty){

        try {

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
                    universalMethods.clearAllFields(firstName, lastName, email, city, street, salary);
                }

            }else empExists.setVisible(true);

        }  catch (Exception e) {
            e.printStackTrace();
        }}

    }

    //Constructors
    UniversalMethods universalMethods = new UniversalMethods();

    //Connecting to database
    String jdbcUrl = "jdbc:mysql://localhost:3306/employee_tracker?useSSL=false&serverTimezone=UTC";
    Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);


    @FXML
    public TextField firstName, lastName, email, city, street, salary;

    @FXML
    public Text warnFName, warnLName, warnEmail, warnCity, warnStreet, warnSalary, warnFillAll, addedProperly, empExists;

    @FXML
    public FontAwesomeIconView addedProperlyThumb;


}
