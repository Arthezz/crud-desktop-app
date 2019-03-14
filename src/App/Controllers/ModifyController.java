package App.Controllers;

import App.Main;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    @FXML
    public void btn_update(MouseEvent event) {
        modifiedProperlyThumb.setVisible(false);
        modifiedProperly.setVisible(false);

        try {
            Statement statement = myConn.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from employee where binary first_name" +
                    " LIKE '" + firstName.getText() + '%' + "' AND binary last_name LIKE '" + lastName.getText() + '%' + "'" +
                    "AND binary email LIKE '" + email.getText() + '%' + "'AND binary city LIKE '" + city.getText() + '%' + "'AND" +
                    " binary street LIKE '" + street.getText() + '%' + "'AND salary LIKE '" + salary.getText() + '%' + "'");
            System.out.println(!resultSet.next());
            if (!resultSet.next()) {
                int status = (statement).executeUpdate("UPDATE FROM employee where binary first_name" +
                        " LIKE '" + firstName.getText() + '%' + "' AND binary last_name LIKE '" + lastName.getText() + '%' + "'" +
                        "AND binary email LIKE '" + email.getText() + '%' + "'AND binary city LIKE '" + city.getText() + '%' + "'AND" +
                        " binary street LIKE '" + street.getText() + '%' + "'AND salary LIKE '" + salary.getText() + '%' + "'");

                if (status > 0) {
                    modifiedProperlyThumb.setVisible(false);
                    modifiedProperly.setVisible(false);
                    warnTooMany.setVisible(false);
                    universalMethods.clearAllFields(firstName, lastName, email, city, street, salary);
                }

            }else warnTooMany.setVisible(true);

        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void searchEmployee(KeyEvent keyEvent) {
        try {
            readController.searchEmployee(keyEvent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ReadController readController= new ReadController();
    UniversalMethods universalMethods = new UniversalMethods();
    Main main = new Main();

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    String jdbcUrl = "jdbc:mysql://localhost:3306/employee_tracker?useSSL=false&serverTimezone=UTC";
    Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);

    @FXML
    public TextField firstName, lastName, email, city, street, salary,
            newFirstName, newLastName, newEmail, newCity, newStreet, newSalary;

    @FXML
    public Text warnFName, warnLName, warnEmail, warnCity, warnStreet, warnSalary, warnTooMany, modifiedProperly, empExists;

    @FXML
    public FontAwesomeIconView modifiedProperlyThumb;

    @FXML
    private TableView<ModelTable> table;

    @FXML
    public TableColumn<ModelTable, String> col_FName;

    @FXML
    private TableColumn<ModelTable, String> col_LName;

    @FXML
    private TableColumn<ModelTable, String> col_Email;

    @FXML
    private TableColumn<ModelTable, String> col_City;

    @FXML
    private TableColumn<ModelTable, String> col_Street;

    @FXML
    private TableColumn<ModelTable, String> col_Salary;

}
