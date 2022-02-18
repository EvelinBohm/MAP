package com.company.View;

import com.company.Controller.RegistrationSystemController;
import com.company.Exception.InputMismatchException;
import com.company.Exception.InvalidInputException;
import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
/**
 * View represents the layer that interacts with the costumer, the class uses the RegistrationSystemController class
 *
 * @author Bohm Evelin
 * @version  16.11.2021
 * @since 1.0
 */
public class View {

    private RegistrationSystemController controller;

    public View(RegistrationSystemController controller) {
        this.controller = controller;
    }

    /**
     * shows the user the main Menu
     * @throws InvalidInputException
     * @throws IOException
     */
    public  void mainMenu() throws InvalidInputException, IOException {
        System.out.println("""
                               Press 1 for student
                               Press 2 for teacher
                               Press 3 to quit
                               """ );
        System.out.println("Please enter you chosen option:");
        String option=validateInput();
        while (!option.equals("3"))
        {
            if (option.equals("1"))
            {
                showMenuStudent();
            }
            if (option.equals("2"))
            {
                showMenuTeacher();
            }
            else while  (Integer.parseInt(option)>3)
            {
                System.out.println("Please enter you chosen option:");
                option=validateInput();
            }
            System.out.println("""
                               Press 1 for student
                               Press 2 for teacher
                               Press 3 to quit
                               """ );
            System.out.println("Please enter you chosen option:");
             option=validateInput();
        }

    }

    /**
     * the teacher menu
     * @throws InvalidInputException if any input is not valid
     */
    public void showMenuTeacher() throws InvalidInputException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Teacher ID:");
        Long teacherID=in.nextLong();
        printMenu("teacher");
        String option=validateInput();
        while (!option.equals("9")){
            if (option.equals("1"))
            {
                Teacher teacher=controller.getTeacherRepository().findOne(teacherID);
                for (Course course : teacher.getCourses())
                    for (Course course1:controller.getAllCourses()) {
                        if (course.getCourseID().equals(course1.getCourseID()))
                        {
                            System.out.println(course1.getName() + ",ID:" + course.getCourseID());
                        }
                    }
                System.out.println("Enter Course ID:");
                Long courseID=in.nextLong();

                List<Student> allStudents = (List<Student>) controller.getStudentRepository().findAll();
                List<Student> allStudentsInTheGivenCourse = new ArrayList<>();
                List<Course> courses=(List<Course>) controller.getCourseRepository().findAll();
                for (Student student : allStudents)
                    for (Course course : student.getEnrolledCourses())
                        for (Course course1:courses){
                            if (course1.getCourseID().equals(course.getCourseID()))
                            {
                                course.setName(course1.getName());
                                course.setCredits(course1.getCredits());
                                course.setMaxEnrollment(course1.getMaxEnrollment());
                                course.setTeacher(course1.getTeacher());

                            }
                         }
                Course foundCourse=controller.getCourseRepository().findOne(courseID);
                for (Student student : allStudents)
                {
                    for (Course c:student.getEnrolledCourses())
                    {
                        if (c.getCourseID().equals(foundCourse.getCourseID()))
                        {
                            allStudentsInTheGivenCourse.add(student);
                        }
                    }
                }
               Course course= controller.deleteCourseByTeacher(foundCourse,teacher,allStudentsInTheGivenCourse);
                if (course != null)
                {
                    System.out.println("You have successfully deleted "+course.getName());
                }
                else {
                    throw  new InvalidInputException("Invalid input");
                }

                printMenu("teacher");
                option=validateInput();

            }
            if (option.equals("2")) {

                List<Course>courseList= controller.retrieveCoursesWithFreePlaces();
                if (courseList.size()>0)
                {
                    System.out.println("List of courses:");
                    for (Course course : courseList) {
                        System.out.println(course.getName() + ",ID:" + course.getCourseID()+",number of available places: "+(course.getMaxEnrollment()-course.getNrOfEnrolledStudents()));
                    }
                }
                else
                    System.out.println("There aren't any courses with free places available");

                printMenu("teacher");
                option=validateInput();

            }

            if (option.equals("3")) {

                printCourse((List<Course>) controller.getAllCourses());
                System.out.println("Please enter the ID of the course:");
                Long courseID =in.nextLong();
                Course course=controller.getCourseRepository().findOne(courseID);
                try{
                    if (course==null)
                        throw  new InvalidInputException("The entered courseID does not exist");
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.println("List of Students:");
                List<Student> studentList = controller.retrieveStudentsEnrolledForACourse(course);
                if (studentList.size()>0)
                {
                    List<Student> allStudents = (List<Student>) controller.getStudentRepository().findAll();
                    for (Student student : studentList)
                        for (Student student1 : allStudents) {
                            if (Objects.equals(student1.getID(), student.getID()))
                                System.out.println(student1.getFirstName() + " " + student1.getLastName() + ",ID:" + student1.getID());
                        }

                }
                else
                    System.out.println("There aren't any student enrolled for the given course");

                printMenu("teacher");
                option=validateInput();}
            if (option.equals("4")) {

                System.out.println("List of Students:");
                List<Student>allStudents=(List<Student>)controller.getStudentRepository().findAll();
                List<Student> studentList = controller.sortStudentsAlphabetically(allStudents);
                for (Student student : studentList){
                    System.out.println(student.getLastName() + " " + student.getFirstName() + ",ID:" + student.getID());
                }
                printMenu("teacher");
                option=validateInput();

            }

            if (option.equals("5")) {

                List<Course>allCurses=(List<Course>)controller.getCourseRepository().findAll();
                List<Course> sortedList = controller.sortCoursesAlphabetically(allCurses);
                printCourse(sortedList);
                printMenu("teacher");
                option=validateInput();

            }
            if (option.equals("6")) {

                System.out.println("Enter the credit number:");
                int creditNr=checkIfInputIsInt();
                List<Student>allStudents=(List<Student>)controller.getStudentRepository().findAll();
                List<Student> filteredList = controller.filterStudentsByCreditNr(creditNr,allStudents);
                if (filteredList.size()>0)
                {
                    printStudents(filteredList);}
                else
                    System.out.println("There aren't any students with the given credit number");

                printMenu("teacher");
                option=validateInput();

            }
            if (option.equals("7")) {

                System.out.println("Enter the credit number:");
                int creditNr=checkIfInputIsInt();
                List<Course> filteredList = controller.filterCoursesByCreditNr(creditNr);
                if (filteredList.size()>0)
                {
                    printCourse(filteredList);}
                else
                    System.out.println("There aren't any students with the given credit number");

                printMenu("teacher");
                option=validateInput();

            }

            if (option.equals("8"))
            {
                return;
            }
            else while  (!option.equals("8") && !option.equals("7") && !option.equals("6") && !option.equals("5") && !option.equals("4")
                    && !option.equals("3")&& !option.equals("2") && !option.equals("1"))
            {
                System.out.println("Input is not a valid option,please try again");
                printMenu("teacher");
                option=validateInput();

            }

        }

    }

    /**
     * checks if the input is a number, methode is used to validate user option input
     * @return String representing the option chosen by the user
     */
    public  String validateInput()

    {
        Scanner in = new Scanner(System.in);
        String option ;
        while (true) {
            option = in.next();
            try {
                int intInputValue = Integer.parseInt(option);
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Input is not a valid option,please try again");
            }}
        return  option;
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
            } catch (InputMismatchException exception) {
                System.out.println("Please enter a valid number");
            }}
        return Integer.parseInt(number);
    }


    /**
     * the student menu
     * @throws IOException if any input is not valid
     */
    public void showMenuStudent() throws IOException {
       Scanner in = new Scanner(System.in);
        printMenu("student");
       String option;
       option=validateInput();

       while(!option.equals("9")) {
           if (option.equals("1")) {

               System.out.println("Please enter you studentID:");

               Long studentID =in.nextLong();
               Student student = controller.getStudentRepository().findOne(studentID);
               try{
                   if (student==null)
                   throw  new InvalidInputException("The entered studentID does not exist");
               } catch (InvalidInputException e) {
                   System.out.println(e.getMessage());
                   break;
               }

               printCourse((List<Course>) controller.getAllCourses());

               System.out.println("Please enter the ID of the course in which you want to participate:");
               Long courseID = in.nextLong();
               Course enteredCourse = controller.getCourseRepository().findOne(courseID);
               try{
                   if (enteredCourse==null)
                       throw  new InvalidInputException("The entered courseID does not exist");
               } catch (InvalidInputException e) {
                   System.out.println(e.getMessage());
                   break;
               }
               boolean registered = controller.register(student, enteredCourse);
               if (registered)
                   System.out.println("You have been successfully registered\n");
               else
                   System.out.println("We couldn't register you, please check your credit score or if the course still has available places \n");

               controller.getStudentRepository().writeToFile();
               controller.getCourseRepository().writeToFile();

               printMenu("student");
               option=validateInput();
           }
           if (option.equals("2")) {

               List<Course>courseList= controller.retrieveCoursesWithFreePlaces();
               if (courseList.size()>0)
               {
                   System.out.println("List of courses:");
                   for (Course course : courseList) {
                       System.out.println(course.getName() + ",ID:" + course.getCourseID()+",number of available places: "+(course.getMaxEnrollment()-course.getNrOfEnrolledStudents()));
                   }
               }
               else
                   System.out.println("There aren't any courses with free places available");

               printMenu("student");
               option=validateInput();

           }
           if (option.equals("3")) {

               printCourse((List<Course>) controller.getAllCourses());
               System.out.println("Please enter the ID of the course:");
               Long courseID =in.nextLong();
               Course course=controller.getCourseRepository().findOne(courseID);
               try{
                   if (course==null)
                       throw  new InvalidInputException("The entered courseID does not exist");
               } catch (InvalidInputException e) {
                   System.out.println(e.getMessage());
                   break;
               }
               System.out.println("List of Students:");
               List<Student> studentList =controller.retrieveStudentsEnrolledForACourse(course);
               if (studentList.size()>0)
               {
                   List<Student> allStudents = (List<Student>) controller.getStudentRepository().findAll();
                   for (Student student : studentList)
                       for (Student student1 : allStudents) {
                           if (Objects.equals(student1.getID(), student.getID()))
                               System.out.println(student1.getFirstName() + " " + student1.getLastName() + ",ID:" + student1.getID());
                       }

               }
               else
                   System.out.println("There aren't any student enrolled for the given course");

               printMenu("student");
               option=validateInput();

           }
           if (option.equals("4")) {

               System.out.println("List of Students:");
               List<Student>allStudents=(List<Student>)controller.getStudentRepository().findAll();
               List<Student> studentList =controller.sortStudentsAlphabetically(allStudents);
               for (Student student : studentList){
                           System.out.println(student.getLastName() + " " + student.getFirstName() + ",ID:" + student.getID());
                   }
               printMenu("student");
               option=validateInput();

           }

           if (option.equals("5")) {

               List<Course>allCurses=(List<Course>)controller.getCourseRepository().findAll();
               List<Course> sortedList =controller.sortCoursesAlphabetically(allCurses);
               printCourse(sortedList);
               printMenu("student");
               option=validateInput();

           }
           if (option.equals("6")) {

               System.out.println("Enter the credit number:");
               int creditNr=checkIfInputIsInt();
               List<Student>allStudents=(List<Student>)controller.getStudentRepository().findAll();
               List<Student> filteredList =controller.filterStudentsByCreditNr(creditNr,allStudents);
               if (filteredList.size()>0)
               {
               printStudents(filteredList);}
               else
                   System.out.println("There aren't any students with the given credit number");

               printMenu("student");
               option=validateInput();

           }
           if (option.equals("7")) {

               System.out.println("Enter the credit number:");
               int creditNr=checkIfInputIsInt();
               List<Course> filteredList =controller.filterCoursesByCreditNr(creditNr);
               if (filteredList.size()>0)
               {
                   printCourse(filteredList);}
               else
                   System.out.println("There aren't any students with the given credit number");

               printMenu("student");
               option=validateInput();

           }

           if (option.equals("8"))
           {
               return;
           }
           else while  (!option.equals("8") && !option.equals("7") && !option.equals("6") && !option.equals("5") && !option.equals("4")
                   && !option.equals("3")&& !option.equals("2") && !option.equals("1"))
           {
               System.out.println("Input is not a valid option,please try again");
               printMenu("student");
               option=validateInput();

           }

       }

   }

    /**
     * prints the menu for the chosen user
     * @param menuType String
     */
   void printMenu(String menuType)
   {
       String firstOption;
       if (menuType.equals("student"))
           firstOption="Press 1 to register for a course"+"\n";
       else
           firstOption="Press 1 to delete course"+"\n";
       System.out.println(firstOption+
                       """
                       Press 2 to show courses with available places and the nr of places
                       Press 3 to show students enrolled for a given course
                       Press 4 to sort students alphabetically
                       Press 5 to sort courses alphabetically
                       Press 6 to filter students by credit number
                       Press 7 to filter courses  by credit number
                       Press 8 to go back
                       
                       Please enter you chosen option: """);
   }

    /**
     * prints a list of courses
     * @param courseList a course list
     */
   void printCourse(List<Course>courseList){
       System.out.println("List of courses:");
       for (Course course : courseList) {
           System.out.println(course.getName() + ",ID:" + course.getCourseID());
       }

   }
    /**
     * prints a list of student
     * @param studentList a student list
     */
   void printStudents(List<Student>studentList)
   {
       System.out.println("List of Students:");
       for (Student student : studentList){
           System.out.println(student.getFirstName() + " " + student.getLastName() + ",ID:" + student.getID());
       }
   }

}
