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
 * Tests the methods from the TeacherRepository class
 * @author Bohm Evelin
 * @version  %I%, %G%
 * @since 1.0
 */

class TeacherRepositoryTest {
    TeacherRepository teacherRepo;
    Course algebra,oop;
    Teacher noah,lisa,updatedTeacher;
    Student arthur,samantha;

    /**
     * Initialization of data for test methods
     */
    @BeforeEach
    void setUp() throws FileNotFoundException {


        List<Teacher>teacherRepoList=new ArrayList<>();
         teacherRepo=new TeacherRepository(teacherRepoList);


        List<Student>studentListOop=new ArrayList<>();
        List<Student>studentListAlgebra=new ArrayList<>();

        List<Course>courseListLisa=new ArrayList<>();
        List<Course>courseListNoah=new ArrayList<>();

        List<Course>courseListArthur=new ArrayList<>();
        List<Course>courseListSamantha=new ArrayList<>();


        lisa=new Teacher("Lisa","David",122L,courseListLisa);
        noah=new Teacher("Noah","Williams",129L,courseListNoah);
        updatedTeacher=new Teacher("Noah James","Williams",129L,courseListNoah);


        oop= new Course(203L,"OOP",lisa,1,studentListOop,3);
        algebra= new Course(204L,"Algebra",noah,2,studentListAlgebra,5);


        arthur= new Student("Arthur","Lee",304L,5,courseListArthur);
        samantha= new Student("Samantha","Allan",305L,6,courseListSamantha);



    }
    /**
     * Tests the findOne methode
     */
    @Test
    void findOne() {

        //adding to the repo
        teacherRepo.save(noah);

        Long id=noah.getID();
        Teacher findTeacher= teacherRepo.findOne(id);
        assertEquals(noah,findTeacher);

        Long teacherIdFromFile =127L;
        Teacher findTeacherFromFile= teacherRepo.findOne(teacherIdFromFile);
        assertEquals(teacherIdFromFile,findTeacherFromFile.getID());

        Long IdPeter= lisa.getID();
        Teacher findTeacherInRepo= teacherRepo.findOne(IdPeter);
        assertNull(findTeacherInRepo);
    }
    /**
     * Tests the findAll methode
     */
    @Test
    void findAll() {
        teacherRepo.save(noah);

        Iterable<Teacher>teacherList= teacherRepo.findAll();
        long size=teacherList.spliterator().getExactSizeIfKnown();
        assertEquals(size,4L);
    }
    /**
     * Tests the save methode
     */
    @Test
    void save() {
        Iterable<Teacher>teacherListBeforeSave= teacherRepo.findAll();
        long size=teacherListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,3L);

        //adding to the repo
        teacherRepo.save(lisa);
        teacherRepo.save(noah);

        Iterable<Teacher>teachersListAfterSave= teacherRepo.findAll();
        long newSize=teachersListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,5L);
    }
    /**
     * Tests the delete methode
     */
    @Test
    void delete() {

        teacherRepo.save(lisa);
        Iterable<Teacher>teachersListAfterSave= teacherRepo.findAll();
        long size=teachersListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,4L);

        teacherRepo.delete(lisa.getID());

        Iterable<Teacher>teachersListAfterDelete= teacherRepo.findAll();
        long newSize=teachersListAfterDelete.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,3L);

        long teacherIdReadFromFile=127L;
        teacherRepo.delete(teacherIdReadFromFile);

        Iterable<Teacher>teachersListAfterDeleteOfIdReadFromFile= teacherRepo.findAll();
        long sizeAfterFinalDelete=teachersListAfterDeleteOfIdReadFromFile.spliterator().getExactSizeIfKnown();
        assertEquals(sizeAfterFinalDelete,2L);
    }
    /**
     * Tests the update methode
     */
    @Test
    void update() {

        teacherRepo.save(noah);

        Teacher updateLinda=teacherRepo.update(updatedTeacher);
        assertNull(updateLinda);

        Teacher TeacherNotFound=teacherRepo.update(lisa);
        assertNotNull(TeacherNotFound);
    }
}

