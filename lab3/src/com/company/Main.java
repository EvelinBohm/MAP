package com.company;

import com.company.Controller.RegistrationSystemController;
import com.company.Exception.InvalidInputException;
import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.View.View;
import com.company.Repository.CourseRepository;
import com.company.Repository.StudentRepository;
import com.company.Repository.TeacherRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {

    public static void main(String[] args) throws IOException, InvalidInputException {

        List<Course> courseList=new ArrayList<>();
        List<Student> students=new ArrayList<>();
        List<Teacher> teacher=new ArrayList<>();
        TeacherRepository teacherRepository = new TeacherRepository(teacher);
        StudentRepository studentRepository = new StudentRepository(students);
        CourseRepository courseRepository = new CourseRepository(courseList);


        RegistrationSystemController controller = new RegistrationSystemController(studentRepository,teacherRepository,courseRepository);

        View view=new View(controller);
        view.mainMenu();





   }
}
