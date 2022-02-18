package  View;
import Colors.ConsoleColor;
import Controller.RegistrationSystemController;
import Exception.InputException;
import Exception.NullException;

import Model.Course;
import Model.Student;
import Model.Teacher;


import java.util.List;
import java.util.Scanner;

/**
 * class View uses the RegistrationSystemController
 * provides a menu for students and another menu for teachers,each menu has its specific actions
 * provides Input validation methods, stores currently logged in student or teacher
 *
 * @author Bohm Evelin
 * @version  07.12.2021
 * @since 1.0
 */
public class View{
    private final RegistrationSystemController registrationSystemController;
    private final Scanner in;
    private Long loggedStudentId;
    private Long loggedTeacherId;

    public View(RegistrationSystemController registrationSystemController) {
        this.registrationSystemController = registrationSystemController;
        in=new Scanner(System.in);
        loggedStudentId=null;
        loggedTeacherId=null;


    }


    /**
     * shows the user the main Menu
     */
    public  void mainMenu() {
        System.out.println(ConsoleColor.PURPLE+"""
                               Press 1 for student
                               Press 2 for teacher
                               Press 3 to quit
                               """ +ConsoleColor.RESET);
        System.out.println("Please enter you chosen option:");
        int option=checkIfInputIsInt();
        while ( option!=3)
        {
            if (option==1)
            {

                if (logInStudent()){

                    System.out.println(ConsoleColor.BLUE_UNDERLINED+"Student Menu"+ConsoleColor.RESET);
                    Student loggedInStudent=registrationSystemController.findStudent(loggedStudentId);
                    System.out.println("Welcome: "+ConsoleColor.BLUE+loggedInStudent.getLastName()+" "+loggedInStudent.getFirstName()+ConsoleColor.RESET);
                    System.out.println("Your credit score is:"+ConsoleColor.BLUE+loggedInStudent.getTotalCredits()+ConsoleColor.RESET);
                    System.out.println("Your enrolled in the following courses: ");
                    List<Course> courseList=registrationSystemController.getCoursesOfStudent(loggedInStudent);
                    courseList.forEach(course -> System.out.println(ConsoleColor.BLUE+course.getName()));
                    System.out.print(ConsoleColor.RESET);

                    showMenuStudent();}
            }
            if (option==2)
            {
                if (logInTeacher()){

                    System.out.println(ConsoleColor.CYAN_UNDERLINED+"Teacher Menu"+ConsoleColor.RESET);
                    Teacher loggedInTeacher=registrationSystemController.findTeacher(loggedTeacherId);
                    System.out.println("Welcome "+ConsoleColor.CYAN+loggedInTeacher.getLastName()+" "+loggedInTeacher.getFirstName()+ ConsoleColor.RESET);
                    System.out.println("You are teaching the following courses: ");
                    List<Course> courseList=registrationSystemController.getAllCoursesOfTeacher(loggedInTeacher);
                    courseList.forEach(course -> System.out.println(ConsoleColor.CYAN +course.getName()+" ,ID:"+course.getCourseID()));
                    System.out.print(ConsoleColor.RESET);

                    showMenuTeacher();}
            }
            else while  (option>3)
            {
                System.out.println("Please enter a valid input");
                option=checkIfInputIsInt();
            }
            System.out.println(ConsoleColor.PURPLE +"""
                               Press 1 for student
                               Press 2 for teacher
                               Press 3 to quit
                               """ + ConsoleColor.RESET);
            System.out.println("Please enter you chosen option:");
            option=checkIfInputIsInt();
        }

    }

    private void showMenuStudent()
    {
        printMenu("student");
        int option=checkIfInputIsInt();
        while( option!=5){
            if (option==1){

                option1();
                printMenu("student");
                option=checkIfInputIsInt();
            }
            if (option==2){

                option2();
                printMenu("student");
                option=checkIfInputIsInt();
            }
            if (option==3)
            {
                option3();
                printMenu("student");
                option=checkIfInputIsInt();
            }
            if (option==4)
            {
                option4();
                printMenu("student");
                option=checkIfInputIsInt();
            }
            if (option==5)
                return;
            else while  ( option!= 1 && option!= 2 && option!= 3 && option!= 4 && option!= 5)
            {
                System.out.println("Input is not a valid option,please try again");
                printMenu("student");
                option=checkIfInputIsInt();

            }

        }

    }


    private void showMenuTeacher()
    {
        printMenu("teacher");
        int option=checkIfInputIsInt();

        while( option!=7){
            if (option==1){

                option6();
                printMenu("teacher");
                option=checkIfInputIsInt();
            }
            if (option==2){
                option7();
                printMenu("teacher");
                option=checkIfInputIsInt();
            }
            if (option==3)
            {
                option3();
                printMenu("teacher");
                option=checkIfInputIsInt();
            }
            if (option==4)
            {

                option4();
                printMenu("teacher");
                option=checkIfInputIsInt();

            }
            if (option==5) {

                option8();
                printMenu("teacher");
                option=checkIfInputIsInt();
            }
            if (option==6) {

                option9();
                printMenu("teacher");
                option=checkIfInputIsInt();
            }
            if (option==7) {
               return;
            }

            else while  ( option!= 1 && option!= 2 && option!= 3 && option!= 4 && option!= 5 && option!= 6 && option!= 7)
            {
                System.out.println("Input is not a valid option,please try again");
                printMenu("student");
                option=checkIfInputIsInt();

            }

        }

    }




    private void option1()
    {
      List<Course>allCourses=registrationSystemController.getAllCourses();
      System.out.println("All Courses");
      allCourses.forEach(course-> System.out.println(ConsoleColor.BLUE +course.getName()+" ,ID:"+course.getCourseID()));
      System.out.print(ConsoleColor.RESET);
      System.out.println("Enter courseID:");
      Long courseID = in.nextLong();

      Course enteredCourse= registrationSystemController.findCourse(courseID);
      if(enteredCourse==null){
          System.out.println("The entered courseID does not exist");
           return;}

      Student foundStudent=registrationSystemController.findStudent(loggedStudentId);
      try {
          boolean registered = registrationSystemController.register(foundStudent, enteredCourse);
          if (registered)
              System.out.println("You have been successfully registered\n");

      } catch (InputException e) {
          System.out.println("We couldn't register you, please check your credit score or if the course still has available places \n");

      } catch (NullException e) {
          e.printStackTrace();
      }

    }


    private void option2()
    {
        List<Course>coursesWithFreePlaces=registrationSystemController.retrieveCoursesWithFreePlaces();
        coursesWithFreePlaces.forEach(course -> System.out.println(ConsoleColor.BLUE +course.getName()));
        System.out.print(ConsoleColor.RESET);

    }

 private void option3()
 {
     List<Course>sortedCourseList=registrationSystemController.sortCoursesAlphabetically();
     sortedCourseList.forEach(course -> System.out.println(ConsoleColor.BLUE +course.getName()));
     System.out.print(ConsoleColor.RESET);
 }

    private void option4()
    {
        System.out.println("Please enter the filter criteria:");
        int credit=checkIfInputIsInt();

        List<Course>coursesWithFreePlaces=registrationSystemController.filterCoursesByCreditNr(credit);
        coursesWithFreePlaces.forEach(course-> System.out.println(ConsoleColor.BLUE+course.getName()));
        System.out.print(ConsoleColor.RESET);

    }

    private void option6()
    {
        System.out.println("Please enter the ID of the course you want to delete:");
        Long courseID=in.nextLong();
        Course foundCourse= registrationSystemController.findCourse(courseID);
        Teacher foundTeacher=registrationSystemController.findTeacher(loggedTeacherId);
        if(foundCourse!=null)
        {
            try
            {
                boolean deletedCourse=registrationSystemController.deleteCourseByTeacher(foundCourse,foundTeacher);
                if (deletedCourse)
                    System.out.println("Course deleted successfully");
                else
                    System.out.println("Something went wrong,please make sure input data is correct");
            } catch (InputException | NullException e) {
                e.printStackTrace();
            }
        }

    }

    private  void option7()
    {
        System.out.println("All Courses:");
        registrationSystemController.getAllCourses().forEach(course-> System.out.println(ConsoleColor.CYAN+course.getName()+" "+course.getCourseID()));
        System.out.print(ConsoleColor.RESET);
        System.out.println("Please enter the ID of the course");
        Long courseID=in.nextLong();
        Course foundCourse=registrationSystemController.findCourse(courseID);
        while (foundCourse==null)
        {
            courseID=in.nextLong();
            foundCourse=registrationSystemController.findCourse(courseID);
        }

        List<Student>studentsEnrolledFor=registrationSystemController.getStudentsEnrolledFor(foundCourse);
        studentsEnrolledFor.forEach(student -> System.out.println(ConsoleColor.CYAN+student.getFirstName()+" "+student.getLastName()+" ,"+student.getID()));
        System.out.print(ConsoleColor.RESET);

    }

    private void option8()
    {
        List<Student>soredList=registrationSystemController.sortStudentsAlphabetically();
        soredList.forEach(student -> System.out.println(ConsoleColor.CYAN+student.getFirstName()+" "+student.getLastName()+" ,"+student.getID()));
        System.out.print(ConsoleColor.RESET);
    }

    private void option9()
    {
        System.out.println("Please enter the filter criteria:");
        int credit=checkIfInputIsInt();

        List<Student>filterStudentsByCreditNr=registrationSystemController.filterStudentsByCreditNr(credit);
        filterStudentsByCreditNr.forEach(student -> System.out.println(ConsoleColor.CYAN+student.getFirstName()+" "+student.getLastName()+" ,"+student.getID()));
        System.out.print(ConsoleColor.RESET);
    }

private boolean logInStudent()
{
    System.out.println(ConsoleColor.PURPLE_UNDERLINED+"Log In"+ConsoleColor.RESET);
    System.out.println("Please enter you studentID:");

    Long studentID=in.nextLong();
    Student foundStudent=registrationSystemController.findStudent(studentID);
    if (foundStudent != null)
    {
        loggedStudentId=foundStudent.getID();
        System.out.println(ConsoleColor.BLUE+"Successfully logged in!"+ConsoleColor.RESET);
        return true;
    }
    System.out.println(ConsoleColor.RED+"Id couldn't be found"+ConsoleColor.RESET);
    return false;

}


    private boolean logInTeacher()
    {
        System.out.println(ConsoleColor.PURPLE_UNDERLINED+"Log In"+ConsoleColor.RESET);
        System.out.println("Please enter you teacherID:");

        Long teacherID=in.nextLong();
        Teacher foundTeacher=registrationSystemController.findTeacher(teacherID);
        if (foundTeacher != null)
        {
            loggedTeacherId=foundTeacher.getID();
            System.out.println(ConsoleColor.CYAN+"Successfully logged in!"+ConsoleColor.RESET);
            return true;
        }
        System.out.println(ConsoleColor.RED+"Id couldn't be found"+ConsoleColor.RESET);
        return false;

    }


    void printMenu(String menuType)
    {

        if (menuType.equals("student")) {
            System.out.println(ConsoleColor.RESET+"Your option are:");
            System.out.println(
                    """
                    Press 1 to register for a course
                    Press 2 to show courses with available places and the number of free place
                    Press 3 to sort courses alphabetically
                    Press 4 to filter courses  by credit number
                    Press 5 to go back
                    
                    Please enter your chosen option:
                     """);
        }
        else
        System.out.println(
                """
                Press 1 to delete course
                Press 2 to show students enrolled for a given course
                Press 3 to sort courses alphabetically
                Press 4 to filter courses  by credit number
                Press 5 to sort students alphabetically
                Press 6 to filter students by credit number
                Press 7 to go back
                
                Please enter your chosen option:
                 """);
    }



    /**
     * checks if the input is a number
     * @return int
     */
    public  int checkIfInputIsInt()

    {
        Scanner in = new Scanner(System.in);
        String number ;
        while (true) {
            number = in.next();
            try {
                int intInputValue = Integer.parseInt(number);
                break;
            } catch (NumberFormatException ex ) {
                System.out.println("Please enter a valid input");
            }}
        return Integer.parseInt(number);
    }

}