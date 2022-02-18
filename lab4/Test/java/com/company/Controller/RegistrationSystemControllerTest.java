package com.company.Controller;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.Repository.CourseRepository;
import com.company.Repository.StudentRepository;
import com.company.Repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the methods from the RegistrationSystemController class
 * @author Bohm Evelin
 * @version  %I%, %G%
 * @since 1.0
 */

class RegistrationSystemControllerTest {
    RegistrationSystemController registrationSystem;
    StudentRepository studentRepo;
    CourseRepository courseRepo;
    TeacherRepository teacherRepo;
    Course algebra,oop,algebraGer;
    Teacher noah,lisa;
    Student arthur,samantha,ben;

    /**
     * Initialization of data for test methods
     */
   @BeforeEach
    void setUp() throws FileNotFoundException {

       //reading data from the file (the files contain 3 Courses/Students/Teachers)
       List<Course>courseRepoList=new ArrayList<>();
       courseRepo=new CourseRepository(courseRepoList);

       List<Student>studentRepoList=new ArrayList<>();
       studentRepo=new StudentRepository(studentRepoList);

       List<Teacher>teacherRepoList=new ArrayList<>();
       teacherRepo=new TeacherRepository(teacherRepoList);

       registrationSystem=new RegistrationSystemController(studentRepo,teacherRepo,courseRepo);

       List<Student>studentListOop=new ArrayList<>();
       List<Student>studentListAlgebra=new ArrayList<>();

       List<Course>courseListLisa=new ArrayList<>();
       List<Course>courseListNoah=new ArrayList<>();

       List<Course>courseListArthur=new ArrayList<>();
       List<Course>courseListSamantha=new ArrayList<>();
       List<Course>courseListBen=new ArrayList<>();


       lisa=new Teacher("Lisa","David",122L,courseListLisa);
       noah=new Teacher("Noah","Williams",129L,courseListNoah);

       oop= new Course(203L,"OOP",lisa,1,studentListOop,3);
       algebra= new Course(204L,"Algebra",noah,2,studentListAlgebra,5);

       arthur= new Student("Arthur","Lee",304L,0,courseListArthur);
       samantha= new Student("Samantha","Allan",305L,0,courseListSamantha);
       ben= new Student("Ben","Welly",306L,30,courseListBen);

       courseListNoah.add(algebra);
       courseListLisa.add(oop);

    }

    /**
     * tests the register methode
     */

    @Test
    void register() {

        registrationSystem.register(arthur,oop);
        List<Course>courseListArthur=arthur.getEnrolledCourses();
        assertEquals(arthur.getTotalCredits(),3);
        assertEquals(courseListArthur.get(0).getName(),"OOP");
        assertEquals(oop.getStudentsEnrolled().get(0).getID(),304L);

        registrationSystem.register(samantha,oop);
        List<Course>courseListSam=samantha.getEnrolledCourses();
        assertEquals(courseListSam.size(),0);
        assertEquals(samantha.getTotalCredits(),0);

        registrationSystem.register(ben,oop);
        List<Course>courseListBen=ben.getEnrolledCourses();
        assertEquals(courseListBen.size(),0);
    }

    /**
     * tests the retrieveCoursesWithFreePlaces methode
     */
    @Test
    void retrieveCoursesWithFreePlaces() {
        registrationSystem.register(arthur,oop);
        registrationSystem.register(arthur,algebra);
        registrationSystem.register(samantha,algebra);

        List<Course>listCourseWithFreePlaces=registrationSystem.retrieveCoursesWithFreePlaces();
        assertEquals(listCourseWithFreePlaces.size(),3);

    }

    /**
     * tests the retrieveStudentsEnrolledForACourse methode
     */
    @Test
    void retrieveStudentsEnrolledForACourse() {
        registrationSystem.register(arthur,oop);
        registrationSystem.register(arthur,algebra);
        registrationSystem.register(samantha,algebra);

        List<Student>listStudents=registrationSystem.retrieveStudentsEnrolledForACourse(oop);
        assertEquals(listStudents.size(),1);
        List<Student>studentList=registrationSystem.retrieveStudentsEnrolledForACourse(algebra);
        assertEquals(studentList.size(),2);

    }

    /**
     * tests the deleteCourseByTeacher metode
     */
    @Test
    void deleteCourseByTeacher() {

        registrationSystem.register(arthur,oop);
        registrationSystem.register(arthur,algebra);
        registrationSystem.register(samantha,algebra);
        assertEquals(arthur.getTotalCredits(),8);
        assertEquals(samantha.getTotalCredits(),5);
        List<Student>studentList=registrationSystem.retrieveStudentsEnrolledForACourse(algebra);
        Course course= registrationSystem.deleteCourseByTeacher(algebra,noah,studentList);
        assertEquals(arthur.getTotalCredits(),3);
        assertEquals(samantha.getTotalCredits(),0);


    }
}
