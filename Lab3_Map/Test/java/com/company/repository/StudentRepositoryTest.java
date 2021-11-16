package com.company.repository;

import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the methods from the StudentRepository class
 * @author Bohm Evelin
 * @version  30.10.2021
 * @since 1.0
 */

class StudentRepositoryTest {
    StudentRepository StudentRepo;
    Course map,bd,stat;
    Teacher Peter,Linda;
    Student Timi,Mia,Sam,Anna_Mia;


    /**
     * Initialization of data for test methods
     */
    @BeforeEach
    void setUp() {

        List<Student> studentsMapList=new ArrayList<>();
        List<Student>studentsBdList=new ArrayList<>();
        List<Student>studentsStatList=new ArrayList<>();

        List<Course>courseListPeter=new ArrayList<>();
        List<Course>courseListLinda=new ArrayList<>();

        List<Course>timisList=new ArrayList<>();
        List<Course>miasList=new ArrayList<>();
        List<Course>samsList=new ArrayList<>();

        Timi= new Student("Timothée","Chalamet",200L,5,timisList);
        Mia= new Student("Mia","Clarke",201L,6,miasList);
        Sam= new Student("Samuel","Watson",202L,5,samsList);
        Anna_Mia= new Student("Anna Mia","Clarke",201L,7,miasList);

        Peter=new Teacher("Peter","Mitchell",123L,courseListPeter);
        Linda=new Teacher("Linda","Wood",127L,courseListLinda);

        map= new Course(125L,"map",Linda,1,studentsMapList,3);
        bd= new Course(123L,"bd",Peter,2,studentsBdList,5);
        stat= new Course(126L,"stat",Peter,6,studentsStatList,6);

        List<Student>studentList=new ArrayList<>();

        StudentRepo=new StudentRepository(studentList);

    }
    /**
     * Tests the findOne methode
     */
    @Test
    void findOne() {

        //adding to the repo
        StudentRepo.save(Timi);
        StudentRepo.save(Mia);

        Long id=Timi.getStudentID();
        Student findStudent= StudentRepo.findOne(id);
        assertEquals(Timi,findStudent);

        Long IdSam= Sam.getStudentID();
        Student findStudentSam= StudentRepo.findOne(IdSam);
        assertNull(findStudentSam);

    }
    /**
     * Tests the findAll methode
     */
    @Test
    void findAll() {
        StudentRepo.save(Timi);
        StudentRepo.save(Mia);

        Iterable<Student>allStudents= StudentRepo.findAll();
        long size=allStudents.spliterator().getExactSizeIfKnown();

        assertEquals(size,2L);

    }
    /**
     * Tests the save methode
     */
    @Test
    void save() {


        Iterable<Student>studentListBeforeSave= StudentRepo.findAll();
        long size=studentListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,0L);

        //adding to the repo
        StudentRepo.save(Timi);
        StudentRepo.save(Mia);

        Iterable<Student>studentListAfterSave= StudentRepo.findAll();
        long newSize=studentListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,2L);
    }
    /**
     * Tests the delete methode
     */
    @Test
    void delete() {

        StudentRepo.save(Timi);
        StudentRepo.save(Mia);

        Iterable<Student>studentList= StudentRepo.findAll();
        long size=studentList.spliterator().getExactSizeIfKnown();
        assertEquals(size,2L);

        StudentRepo.delete(Mia.getStudentID());

        Iterable<Student>newStudentList= StudentRepo.findAll();
        long newSize=newStudentList.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,1L);
    }
    /**
     * Tests the update methode
     */
    @Test
    void update() {

        StudentRepo.save(Timi);
        StudentRepo.save(Mia);

        Student updateMia=StudentRepo.update(Anna_Mia);
        assertNull(updateMia);

        Student studentNotFound=StudentRepo.update(Sam);
        assertNotNull(studentNotFound);
    }
}