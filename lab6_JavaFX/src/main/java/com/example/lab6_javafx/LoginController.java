package com.example.lab6_javafx;
import com.example.lab6_javafx.Controller.RegistrationSystemController;
import com.example.lab6_javafx.Model.Student;
import com.example.lab6_javafx.Model.Teacher;
import com.example.lab6_javafx.Repository.CourseRepository;
import com.example.lab6_javafx.Repository.StudentRepository;
import com.example.lab6_javafx.Repository.TeacherRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event. ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * the LoginController class manages the data introduced in the main window
 * functionalities:
 * checks if input data is valid, if it's valid it opens depending on the input data either the student or teacher window
 * if the data is not valid an error message is displayed
 *
 * @author Bohm Evelin
 * @version  14.12.2021
 * @since 1.0
 */
public class LoginController implements Initializable {


    private final StudentRepository studentRepository=new StudentRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final CourseRepository courseRepository=new CourseRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final TeacherRepository teacherRepository=new TeacherRepository("jdbc:mysql://localhost:3306/lab5_map");
    private final RegistrationSystemController controller=new RegistrationSystemController(studentRepository,teacherRepository,courseRepository);
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView logoImageView;
    @FXML
    private  ImageView miniLockImageView;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    public TextField idTextField;

    private Long savedLogId;

    /***
     * initialize the window with the images
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logoFile= new File("images/ubb_logo.png");
        Image logoImage= new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File lockFile= new File("images/lock.png");
        Image lockImage= new Image(lockFile.toURI().toString());
        miniLockImageView.setImage(lockImage);
        loginMessageLabel.setText("");
    }

    /**
     * opens window for Student or Teacher
     * @param event-button pressed
     * @throws IOException
     */
    public void loginButtonAction(ActionEvent event) throws IOException {

        if(!lastNameTextField.getText().isBlank() && !firstNameTextField.getText().isBlank() && !idTextField.getText().isBlank())
        {
            int logID=loggingIn();
           if (logID==1)
           {
               lastNameTextField.setText("");
               firstNameTextField.setText("");
               lastNameTextField.setText("");
               idTextField.setText("");
               StudentController studentController = new StudentController();
               studentController.setSavedStudID(savedLogId);
               studentController.openStudentView();


           }
            if (logID==2)
            {
                lastNameTextField.setText("");
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                idTextField.setText("");
                TeacherController teacherController = new TeacherController();
                teacherController.setSavedTeacherID(savedLogId);
                teacherController.openTeacherView();
            }
            if (logID==0){
                loginMessageLabel.setText("Login failed.");
                lastNameTextField.setText("");
                firstNameTextField.setText("");
                idTextField.setText("");
              ;}

        }
        else{
            lastNameTextField.setText("");
            firstNameTextField.setText("");
            idTextField.setText("");
            loginMessageLabel.setText("Login failed.");}
    }

    /**
     * check if loginId is valid
     * @return 1 if login data is a Student, 2 if login data is a Teacher
     */
    private int validateLogin() {

      String id=idTextField.getText();
      if (isNumeric(id)){
      Student foundStudent=controller.findStudent(Long.parseLong(idTextField.getText()));
      Teacher foundTeacher= controller.findTeacher(Long.parseLong(idTextField.getText()));
      if (foundStudent!=null && lastNameTextField.getText().equals(foundStudent.getLastName()) && firstNameTextField.getText().equals(foundStudent.getFirstName())){
          savedLogId= foundStudent.getID();
          loginMessageLabel.setText("successfully logged in");
          return 1;
      }
      if (foundTeacher!=null && lastNameTextField.getText().equals(foundTeacher.getLastName()) && firstNameTextField.getText().equals(foundTeacher.getFirstName()))
      {
          savedLogId= foundTeacher.getID();
          loginMessageLabel.setText("successfully logged in");
          return 2;}
    }
        loginMessageLabel.setText("successfully logged in");
        return 0;
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
     * changing message after login try
     * @return 0-if login data wasn't found, 1-if login data is a Student, 2-if login data is a Teacher
     */

    private int loggingIn()
    {
        int foundPerson=validateLogin();
        if (foundPerson==0){
         return 0;
        }
        if (foundPerson==1){
            return 1;
        }
        return 2;

    }
    

}