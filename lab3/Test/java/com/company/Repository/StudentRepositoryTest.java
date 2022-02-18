package com.company.Repository;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the methods from the StudentRepository class
 * @author Bohm Evelin
 * @version  %I%, %G%
 * @since 1.0
 */

class StudentRepositoryTest {
    StudentRepository studentRepo;
    Course algebra,oop;
    Teacher noah,lisa;
    Student arthur,samantha,updatedStudent;


    /**
     * Initialization of data for test methods
     */
    @BeforeEach
    void setUp() throws FileNotFoundException {
        //reading data from the file (the file contains 3 Students)
        List<Student>studentRepoList=new ArrayList<>();
        studentRepo=new StudentRepository(studentRepoList);

        List<Student>studentListOop=new ArrayList<>();
        List<Student>studentListAlgebra=new ArrayList<>();

        List<Course>courseListLisa=new ArrayList<>();
        List<Course>courseListNoah=new ArrayList<>();

        List<Course>courseListArthur=new ArrayList<>();
        List<Course>courseListSamantha=new ArrayList<>();
        List<Course>courseListUpdatedStud=new ArrayList<>();


        lisa=new Teacher("Lisa","David",122L,courseListLisa);
        noah=new Teacher("Noah","Williams",129L,courseListNoah);


        oop= new Course(203L,"OOP",lisa,1,studentListOop,3);
        algebra= new Course(204L,"Algebra",noah,2,studentListAlgebra,5);


        arthur= new Student("Arthur","Lee",304L,5,courseListArthur);
        samantha= new Student("Samantha","Allan",305L,6,courseListSamantha);
        updatedStudent= new Student("Anna Mia","Clarke",302L,7,courseListUpdatedStud);

    }
    /**
     * Tests the findOne methode
     */
    @Test
    void findOne() {

        //adding to the repo
        studentRepo.save(arthur);

        Long id=arthur.getID();
        Student findStudent= studentRepo.findOne(id);
        assertEquals(arthur,findStudent);

        Long studentIdFromFile=301L;
        Student findStudentFromFile= studentRepo.findOne(studentIdFromFile);
        assertEquals(studentIdFromFile,findStudentFromFile.getID());

        Long IdSam= samantha.getID();
        Student findStudentSam= studentRepo.findOne(IdSam);
        assertNull(findStudentSam);

    }
    /**
     * Tests the findAll methode
     */
    @Test
    void findAll() {
        studentRepo.save(arthur);
        studentRepo.save(samantha);

        Iterable<Student>allStudents= studentRepo.findAll();
        long size=allStudents.spliterator().getExactSizeIfKnown();

        assertEquals(size,5L);

    }
    /**
     * Tests the save methode
     */
    @Test
    void save() {


        Iterable<Student>studentListBeforeSave= studentRepo.findAll();
        long size=studentListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,3L);

        //adding to the repo
        studentRepo.save(arthur);
        studentRepo.save(samantha);

        Iterable<Student>studentListAfterSave= studentRepo.findAll();
        long newSize=studentListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,5L);
    }
    /**
     * Tests the delete methode
     */
    @Test
    void delete() {

        studentRepo.save(arthur);

        Iterable<Student>studentList= studentRepo.findAll();
        long size=studentList.spliterator().getExactSizeIfKnown();
        assertEquals(size,4L);

        studentRepo.delete(arthur.getID());

        Iterable<Student>newStudentList= studentRepo.findAll();
        long newSize=newStudentList.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,3L);

        long studentFromFile=301L;
        studentRepo.delete(studentFromFile);

        Iterable<Student>finalStudentList= studentRepo.findAll();
        long sizeAfterDelete=newStudentList.spliterator().getExactSizeIfKnown();
        assertEquals(sizeAfterDelete,2L);

    }
    /**
     * Tests the update methode
     */
    @Test
    void update() {


        Student updateMia=studentRepo.update(updatedStudent);
        assertNull(updateMia);

        Student studentNotFound=studentRepo.update(arthur);
        assertNotNull(studentNotFound);
    }
}

