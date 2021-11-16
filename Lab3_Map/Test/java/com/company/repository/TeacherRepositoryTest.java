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
 * Tests the methods from the TeacherRepository class
 * @author Bohm Evelin
 * @version 30.10.2021
 * @since 1.0
 */
class TeacherRepositoryTest {
    TeacherRepository TeacherRepo;
    Course map,bd,stat;
    Teacher Peter,Linda,Linda2;
    Student Timi,Mia,Sam;

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

        Peter=new Teacher("Peter","Mitchell",123L,courseListPeter);
        Linda=new Teacher("Linda","Wood",127L,courseListLinda);
        Linda2=new Teacher("Linda","Wood Allan",127L,courseListLinda);

        map= new Course(125L,"map",Linda,1,studentsMapList,3);
        bd= new Course(123L,"bd",Peter,2,studentsBdList,5);
        stat= new Course(126L,"stat",Peter,6,studentsStatList,6);

        courseListLinda.add(map);
        courseListPeter.add(bd);
        courseListPeter.add(map);
        List<Teacher>teacherList=new ArrayList<>();

        TeacherRepo=new TeacherRepository(teacherList);


    }
    /**
     * Tests the findOne methode
     */
    @Test
    void findOne() {

        //adding to the repo
        TeacherRepo.save(Linda);

        Long id=Linda.getTeacherID();
        Teacher findTeacher= TeacherRepo.findOne(id);
        assertEquals(Linda,findTeacher);

        Long IdPeter= Peter.getTeacherID();
        Teacher findTeacherInRepo= TeacherRepo.findOne(IdPeter);
        assertNull(findTeacherInRepo);
    }
    /**
     * Tests the findAll methode
     */
    @Test
    void findAll() {
        TeacherRepo.save(Linda);
        TeacherRepo.save(Peter);

        Iterable<Teacher>teacherList= TeacherRepo.findAll();
        long size=teacherList.spliterator().getExactSizeIfKnown();
        assertEquals(size,2L);
    }
    /**
     * Tests the save methode
     */
    @Test
    void save() {
        Iterable<Teacher>teacherListBeforeSave= TeacherRepo.findAll();
        long size=teacherListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,0L);

        //adding to the repo
        TeacherRepo.save(Linda);
        TeacherRepo.save(Peter);

        Iterable<Teacher>teachersListAfterSave= TeacherRepo.findAll();
        long newSize=teachersListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,2L);
    }
    /**
     * Tests the delete methode
     */
    @Test
    void delete() {

        TeacherRepo.save(Linda);
        TeacherRepo.save(Peter);
        Iterable<Teacher>teachersListAfterSave= TeacherRepo.findAll();
        long size=teachersListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,2L);

        TeacherRepo.delete(Linda.getTeacherID());

        Iterable<Teacher>teachersListAfterdelete= TeacherRepo.findAll();
        long newSize=teachersListAfterdelete.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,1L);
    }
    /**
     * Tests the update methode
     */
    @Test
    void update() {

        TeacherRepo.save(Linda);

        Teacher updateLinda=TeacherRepo.update(Linda2);
        assertNull(updateLinda);

        Teacher TeacherNotFound=TeacherRepo.update(Peter);
        assertNotNull(TeacherNotFound);
    }
}