import Controller.RegistrationSystemController;
import Model.Course;
import Model.Student;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Exception.NullException;
import Repository.TeacherRepository;
import View.View;
import Exception.InputException;
// Main class

public class Main {


    public static void main(String[] args) throws NullException, InputException {


        String dataBase="jdbc:mysql://localhost:3306/lab5_map";
        StudentRepository repository=new StudentRepository(dataBase);
        CourseRepository courseRepository=new CourseRepository(dataBase);
        TeacherRepository teacherRepository=new TeacherRepository(dataBase);
        RegistrationSystemController controller=new RegistrationSystemController(repository,teacherRepository,courseRepository);
       View view=new View(controller);
      view.mainMenu();

    }

    }