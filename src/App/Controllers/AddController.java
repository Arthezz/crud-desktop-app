package App.Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddController {

    //buttons
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
    public void btn_back(MouseEvent event) throws IOException {
        universalMethods.viewApp(pane);
    }
    @FXML
    public void btn_signOut(MouseEvent event) throws IOException {
        universalMethods.signOut(pane);
    }

    public void newEmployee(MouseEvent event) {

        addedProperlyThumb.setVisible(false);
        addedProperly.setVisible(false);
        warnFillAll.setVisible(false);
        empExists.setVisible(false);

        boolean check = universalMethods.checkTextFields(firstName, lastName, email, city, street, salary,
                warnFName, warnLName, warnEmail, warnCity, warnStreet, warnSalary);
        boolean empty = universalMethods.checkFulfill(firstName, lastName, email, city, street, salary, warnFillAll);

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

            }else {
                empExists.setVisible(true);
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }}

    }

    //Constructors
    private UniversalMethods universalMethods = new UniversalMethods();

    //Connecting to database
    private String jdbcUrl = "jdbc:mysql://db4free.net:3306/cruddesktopapp";
    private Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);


    @FXML
    private TextField firstName, lastName, email, city, street, salary;

    @FXML
    private Text warnFName, warnLName, warnEmail, warnCity, warnStreet, warnSalary, warnFillAll, addedProperly, empExists;

    @FXML
    private FontAwesomeIconView addedProperlyThumb;

    @FXML
    private AnchorPane pane;


}
