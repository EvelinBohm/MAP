package com.company.Controller;

import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.CourseRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    RegistrationSystemController RegistrationSystem;
    StudentRepository StudentRepo;
    CourseRepository CourseRepo;
    TeacherRepository TeacherRepo;
    Course map,bd,stat;
    Teacher Peter,Linda;
    Student Timi,Mia,Sam;

    /**
     * Initialization of data for test methods
     */
    @BeforeEach
    void setUp() {
        StudentRepo=new StudentRepository();
        TeacherRepo=new TeacherRepository();
        CourseRepo=new CourseRepository();
        RegistrationSystem=new RegistrationSystemController(CourseRepo);

        List<Student> studentsMapList=new ArrayList<>();
        List<Student>studentsBdList=new ArrayList<>();
        List<Student>studentsStatList=new ArrayList<>();

        List<Course>courseListPeter=new ArrayList<>();
        List<Course>courseListLinda=new ArrayList<>();

        List<Course>timisList=new ArrayList<>();
        List<Course>miasList=new ArrayList<>();
        List<Course>samsList=new ArrayList<>();

        Timi= new Student("Timothée","Chalamet",200L,0,timisList);
        Mia= new Student("Mia","Clarke",201L,0,miasList);
        Sam= new Student("Samuel","Watson",202L,0,samsList);

        Peter=new Teacher("Peter","Mitchell",123L,courseListPeter);
        Linda=new Teacher("Linda","Wood",127L,courseListLinda);


        map= new Course(125L,"map",Linda,1,studentsMapList,3);
        bd= new Course(123L,"bd",Peter,2,studentsBdList,5);
        stat= new Course(126L,"stat",Peter,4,studentsStatList,29);
        courseListPeter.add(bd);
        courseListPeter.add(stat);
        courseListLinda.add(map);

        CourseRepo.save(map);
        CourseRepo.save(bd);
        CourseRepo.save(stat);

        StudentRepo.save(Timi);
        StudentRepo.save(Mia);
        StudentRepo.save(Sam);

        TeacherRepo.save(Linda);
        TeacherRepo.save(Peter);

    }

    /**
     * tests the register methode
     */
    @Test
    void register() {

        RegistrationSystem.register(Mia,map);
        List<Course>courseListMia=Mia.getEnrolledCourses();
        assertEquals(courseListMia.get(0).getName(),"map");

        RegistrationSystem.register(Mia,bd);
        RegistrationSystem.register(Mia,stat);
        List<Course>courseListMia2=Mia.getEnrolledCourses();
        assertEquals(courseListMia2.size(),2);

        RegistrationSystem.register(Timi,bd);
        List<Course>courseListTimi=Timi.getEnrolledCourses();
        assertEquals(courseListTimi.size(),1);

        RegistrationSystem.register(Sam,bd);
        List<Course>courseListSam=Sam.getEnrolledCourses();
        assertEquals(courseListSam.size(),0);


    }

    /**
     * tests the retrieveCoursesWithFreePlaces methode
     */
    @Test
    void retrieveCoursesWithFreePlaces() {
        RegistrationSystem.register(Mia,map);
        RegistrationSystem.register(Mia,bd);
        RegistrationSystem.register(Timi,bd);

        List<Course>listCourseWithFreePlaces=RegistrationSystem.retrieveCoursesWithFreePlaces();
        assertEquals(listCourseWithFreePlaces.get(0).getName(),"stat");
        assertEquals(listCourseWithFreePlaces.size(),1);

    }

    /**
     * tests the retrieveStudentsEnrolledForACourse methode
     */
    @Test
    void retrieveStudentsEnrolledForACourse() {
        RegistrationSystem.register(Mia,map);
        RegistrationSystem.register(Mia,bd);
        RegistrationSystem.register(Timi,bd);

        List<Student>listStudents=RegistrationSystem.retrieveStudentsEnrolledForACourse(bd);
        assertEquals(listStudents.size(),2);
        List<Student>studentList=RegistrationSystem.retrieveStudentsEnrolledForACourse(stat);
        assertEquals(studentList.size(),0);

    }

    /**
     * tests the deleteCourseByTeacher metode
     */
    @Test
    void deleteCourseByTeacher() {
        RegistrationSystem.register(Mia,map);
        RegistrationSystem.register(Mia,bd);
        RegistrationSystem.register(Timi,bd);
        assertEquals(Mia.getTotalCredits(),8);
        assertEquals(Timi.getTotalCredits(),5);

        Course course= RegistrationSystem.deleteCourseByTeacher(bd,Peter);
        assertEquals(Mia.getTotalCredits(),3);
        assertEquals(Timi.getTotalCredits(),0);


    }
}