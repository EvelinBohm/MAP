package com.company;

import com.company.Controller.RegistrationSystemController;
import com.company.FileReader.ReadCoursesFromCSVFile;
import com.company.FileReader.ReadStudentsFromCSVFile;
import com.company.FileReader.ReadTeachersFromCSVFile;
import com.company.View.View;
import com.company.Repository.CourseRepository;
import com.company.Repository.StudentRepository;
import com.company.Repository.TeacherRepository;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

       /* ReadCoursesFromCSVFile r=new ReadCoursesFromCSVFile();
        ReadTeachersFromCSVFile r2=new ReadTeachersFromCSVFile();
        ReadStudentsFromCSVFile r3=new ReadStudentsFromCSVFile();
        List<Course>l=r.readDataFromFile();
        List<Teacher>l2=r2.readDataFromFile();
        List<Student>l3=r3.readDataFromFile();
        System.out.println("list:");
        //System.out.println(l.toString());
        System.out.println("list2:");
        //System.out.println(l2.toString());
        List<Course>l4=l2.get(0).getCourses();
       // System.out.println(l4.get(0).getCourseID());
        System.out.println("list3:");
        //System.out.println(l3.toString());
        CourseRepository c=new CourseRepository();
        c.setCourseList(l);
        RegistrationSystemController rs=new RegistrationSystemController(c);
        List<Course>timisList=new ArrayList<>();
        Student Timi= new Student("Timothée","Chalamet",305L,0,timisList);
        Course ma=l.get(0);
        rs.register(Timi,ma);*/
        ReadCoursesFromCSVFile readCoursesFromCSVFile=new ReadCoursesFromCSVFile();
        ReadTeachersFromCSVFile readTeachersFromCSVFile=new ReadTeachersFromCSVFile();
        ReadStudentsFromCSVFile readStudentsFromCSVFile=new ReadStudentsFromCSVFile();

        TeacherRepository teacherRepository = new TeacherRepository();
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();

        studentRepository.setStudentList();
        courseRepository.setCourseList();
        teacherRepository.setTeacherList(readTeachersFromCSVFile.readDataFromFile());

        RegistrationSystemController controller = new RegistrationSystemController(studentRepository,teacherRepository,courseRepository);


        View view=new View(controller);
        view.showMenuStundent();




   }
}
