package com.example.lab6_javafx;
import com.example.lab6_javafx.Controller.RegistrationSystemController;
import com.example.lab6_javafx.Model.Course;
import com.example.lab6_javafx.Model.Student;
import com.example.lab6_javafx.Repository.CourseRepository;
import com.example.lab6_javafx.Repository.StudentRepository;
import com.example.lab6_javafx.Repository.TeacherRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * the TeacherController class manages the teacher view
 * functionalities:
 * it checks if the input is valid
 * shows students registered to a course
 *
 * @author Bohm Evelin
 * @version  14.12.2021
 * @since 1.0
 */
public class TeacherController implements Initializable {

    private final StudentRepository studentRepository=new StudentRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final CourseRepository courseRepository=new CourseRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final TeacherRepository teacherRepository=new TeacherRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final RegistrationSystemController controller=new RegistrationSystemController(studentRepository,teacherRepository,courseRepository);

    @FXML
    private TextField searchCourseIdTextField;

    @FXML
    private TableView<Student> tableViewStudent;

    @FXML
    private TableColumn<Student,String> lastName;
    @FXML
    private TableColumn<Student,String> firstName;
    @FXML
    private TableColumn<Student,Long> studID;
    @FXML
    private TableColumn<Student,Integer> credits;
    private static Long id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * opens teacher view
     * @throws IOException
     */
    public void openTeacherView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("teacher-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * shows students enrolled for entered course
     */
    public void showEnrolledStudents()
    {

        ObservableList<Student> list= FXCollections.observableArrayList();
        Course foundCourse=checkIfCourseExists();
        if (foundCourse!=null && foundCourse.getTeacher().getID().equals(id) ){
        list.addAll(controller.getStudentsEnrolledFor(foundCourse.getCourseID()));
        lastName.setCellValueFactory(cellData-> cellData.getValue().lastNameProperty());
        firstName.setCellValueFactory(cellData->cellData.getValue().firstNameProperty());
        studID.setCellValueFactory(cellData-> cellData.getValue().studIdProperty().asObject());
        credits.setCellValueFactory(cellData->cellData.getValue().creditsProperty().asObject());
        tableViewStudent.setItems(list);}
        else{

            searchCourseIdTextField.setText("");
        }
    }
    /**
     * verifies if Course exists in database
     * @return Course object-if course was found,null if course was not found
     */
    private Course checkIfCourseExists()
    {
        if (isNumeric(searchCourseIdTextField.getText())){
            return controller.findCourse(Long.parseLong(searchCourseIdTextField.getText()));
        }
        return null;
    }
    /**
     * check if input is a number
     * @param strNum-input
     * @return true-if strNum is a number,false-if strNum is not a number
     */
    public  boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * saves entered ID in main view
     * @param id entered Id in main view
     */
    public void setSavedTeacherID(Long id)
    {
        this.id=id;
    }
}