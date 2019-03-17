package App.Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {

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
    public void deleteEmployee(MouseEvent event) {

        deletedProperlyThumb.setVisible(false);
        deletedProperly.setVisible(false);

        try {
        Statement statement = myConn.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from employee where binary first_name" +
                " LIKE '" + firstName.getText() + '%' + "' AND binary last_name LIKE '" + lastName.getText() + '%' + "'" +
                "AND binary email LIKE '" + email.getText() + '%' + "'AND binary city LIKE '" + city.getText() + '%' + "'AND" +
                " binary street LIKE '" + street.getText() + '%' + "'AND salary LIKE '" + salary.getText() + '%' + "'");
            System.out.println(!resultSet.next());
        if (!resultSet.next()) {
            int status = (statement).executeUpdate("DELETE FROM employee where binary first_name" +
                    " LIKE '" + firstName.getText() + '%' + "' AND binary last_name LIKE '" + lastName.getText() + '%' + "'" +
                    "AND binary email LIKE '" + email.getText() + '%' + "'AND binary city LIKE '" + city.getText() + '%' + "'AND" +
                    " binary street LIKE '" + street.getText() + '%' + "'AND salary LIKE '" + salary.getText() + '%' + "'");

            if (status > 0) {
                deletedProperlyThumb.setVisible(true);
                deletedProperly.setVisible(true);
                warnTooMany.setVisible(false);
                universalMethods.clearAllFields(firstName, lastName, email, city, street, salary);
                oblist.clear();
            }

        }else warnTooMany.setVisible(true);

        } catch (Exception e) { e.printStackTrace(); }
    }
    @FXML
    public void searchEmployee(KeyEvent keyEvent) throws SQLException {

        deletedProperlyThumb.setVisible(false);
        deletedProperly.setVisible(false);
        warnTooMany.setVisible(false);

        oblist.clear();

        Statement statement = myConn.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from employee where binary first_name" +
                " LIKE '" + firstName.getText() + '%' + "' AND binary last_name LIKE '" + lastName.getText() + '%' + "'" +
                "AND binary email LIKE '" + email.getText() + '%' + "'AND binary city LIKE '" + city.getText() + '%' + "'AND" +
                        " binary street LIKE '" + street.getText() + '%' + "'AND salary LIKE '" + salary.getText() + '%' + "'");

        while (resultSet.next()){
            oblist.add(new ModelTable(resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"),
                    resultSet.getString("city"), resultSet.getString("street"), resultSet.getString("salary")));

            col_FName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            col_LName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_City.setCellValueFactory(new PropertyValueFactory<>("city"));
            col_Street.setCellValueFactory(new PropertyValueFactory<>("street"));
            col_Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

            table.setItems(oblist);
        }

    }

    @FXML
    public void getEmployeeData(MouseEvent event) {
        // Maybe someday
    }

    //Filling table
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Statement statement = myConn.createStatement();
            ResultSet rs = statement.executeQuery("select * from employee ");

            while (rs.next()){
                oblist.add(new ModelTable(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"),
                        rs.getString("city"), rs.getString("street"), rs.getString("salary")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        col_FName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_LName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_City.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_Street.setCellValueFactory(new PropertyValueFactory<>("street"));
        col_Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        table.setItems(oblist);
    }

    private UniversalMethods universalMethods = new UniversalMethods();

    private ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    private String jdbcUrl = "jdbc:mysql://localhost:3306/employee_tracker?useSSL=false&serverTimezone=UTC";
    private Connection myConn = DbConnect.getInstance().getConnection(jdbcUrl);

    @FXML
    private Text warnTooMany, deletedProperly;

    @FXML
    private FontAwesomeIconView deletedProperlyThumb;

    @FXML
    private TextField firstName, lastName, email, city, street, salary;

    @FXML
    private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable, String> col_FName;

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
