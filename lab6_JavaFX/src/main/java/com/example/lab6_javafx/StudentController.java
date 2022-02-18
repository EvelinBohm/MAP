package com.example.lab6_javafx;

import com.example.lab6_javafx.Controller.RegistrationSystemController;
import com.example.lab6_javafx.Exception.InputException;
import com.example.lab6_javafx.Exception.NullException;
import com.example.lab6_javafx.Model.Course;
import com.example.lab6_javafx.Model.Student;
import com.example.lab6_javafx.Repository.CourseRepository;
import com.example.lab6_javafx.Repository.StudentRepository;
import com.example.lab6_javafx.Repository.TeacherRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * the StudentController class manages the student view
 * functionalities:
 * it checks if the input is valid
 * registers a student
 * shows courses with available places
 * shows the data of a student
 *
 * @author Bohm Evelin
 * @version  14.12.2021
 * @since 1.0
 */
public class StudentController  implements Initializable {
    private final StudentRepository studentRepository=new StudentRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final CourseRepository courseRepository=new CourseRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final TeacherRepository teacherRepository=new TeacherRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final RegistrationSystemController controller=new RegistrationSystemController(studentRepository,teacherRepository,courseRepository);
    private static  Long savedStudID;

    @FXML
    private Pane registerPane;
    @FXML
    private Pane creditPane;
    @FXML
    private Label messageLabelRegister;
    @FXML
    private TextField studentIDTextField;
    @FXML
    private TextField studentIdTextFieldCredit;

    @FXML
    private TextField courseIDTextField;

    @FXML
    private TableView<Course> tableViewCourse;

    @FXML
    private TableColumn<Course,String> Course;

    @FXML
    private TableColumn<Course,Long> CourseId;

    @FXML
    private TableColumn<Course,Integer> Credits;
    @FXML
    private TableView<Student> tableViewCredit;

    @FXML
    private TableColumn<Student, Integer> CreditsStud;

    @FXML
    private TableColumn<Student, Long> ID;

    @FXML
    private TableColumn<Student, String> Name;

    /**
     * changes message after data was introduced
     * @param searchObject
     */
    private  void changeMessage(Object searchObject)
    {
        if(searchObject==null)
            messageLabelRegister.setText("Wrong data.Please try again");
        else
            messageLabelRegister.setText("Entered data was found");

    }

    /**
     *open student window
     * @throws IOException
     */
    public void openStudentView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("student-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Welcome!");
         StudentController studentController=fxmlLoader.getController();
        studentController.studentIdTextFieldCredit.setText(savedStudID.toString());
        studentController.studentIDTextField.setText(savedStudID.toString());
        studentController.studentIDTextField.setEditable(false);
        studentController.studentIdTextFieldCredit.setEditable(false);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * shows the register Window
     * @param event-pressed button
     */
    @FXML
    public void registerButtonAction(ActionEvent event){
        messageLabelRegister.setText("");
        creditPane.setVisible(false);
        registerPane.setVisible(true);

    }

    /**
     * registers a student after button was pressed and changes messages after registration
     * @param event-pressed button
     * @throws InputException
     * @throws NullException
     */
    @FXML
    public void registerStudent(ActionEvent event) throws InputException, NullException {

        Student student=checkIfStudent();
        changeMessage(student);
        Course course=checkIfCourseExists();
        changeMessage(course);
        if(student !=null && course!=null)
            if(controller.register(student,course)){
                messageLabelRegister.setText("Successfully registered");
                messageLabelRegister.setVisible(true);
                courseIDTextField.setText("");
            }

            else{
                messageLabelRegister.setText("Something went wrong");
                messageLabelRegister.setVisible(true);
                courseIDTextField.setText("");}

    }

    /**
     * shows the credit view for the student
     */
    public void setCreditPane()
    {
        registerPane.setVisible(false);
        creditPane.setVisible(true);

    }

    /**
     * shows the student info
     * @param event-button pressed
     */
    public void showCreditNr(ActionEvent event) {

        ObservableList<Student> list= FXCollections.observableArrayList();
        if (isNumeric(studentIdTextFieldCredit.getText())){
            Student foundStudent=controller.findStudent(Long.parseLong(studentIdTextFieldCredit.getText()));
            if (foundStudent!=null)
                list.add(foundStudent);
        }
        Name.setCellValueFactory(cellData-> cellData.getValue().lastNameProperty());
        ID.setCellValueFactory(cellData->cellData.getValue().studIdProperty().asObject());
        CreditsStud.setCellValueFactory(cellData-> cellData.getValue().creditsProperty().asObject());

        tableViewCredit.setItems(list);

    }

    /**
     * shows courses
     * @param event-pressed button
     */
    public void showCoursesButton(ActionEvent event){
        ObservableList<Course> list= FXCollections.observableArrayList();
        list.addAll(controller.retrieveCoursesWithFreePlaces());
        Course.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        CourseId.setCellValueFactory(cellData->cellData.getValue().courseIdProperty().asObject());
        Credits.setCellValueFactory(cellData-> cellData.getValue().creditsProperty().asObject());

        tableViewCourse.setItems(list);

    }

    /**
     * verifies if Student exists in database
     * @return Student object-if student was found,null if student was not found
     */
    private  Student checkIfStudent()
    {
        if (isNumeric(studentIDTextField.getText())){
            return controller.findStudent(Long.parseLong(studentIDTextField.getText()));
        }
        return null;
    }
    /**
     * verifies if Course exists in database
     * @return Course object-if course was found,null if course was not found
     */
    private  Course checkIfCourseExists()
    {
        if (isNumeric(courseIDTextField.getText())){
            return controller.findCourse(Long.parseLong(courseIDTextField.getText()));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * saves entered ID in main view
     * @param id entered Id in main view
     */
    public void setSavedStudID(Long id)
    {
        this.savedStudID=id;
    }

}
