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
 * Tests the methods from the CourseRepository class
 * @author Bohm Evelin
 * @version  %I%, %G%
 * @since 1.0
 */

class CourseRepositoryTest {
    CourseRepository courseRepo;
    Course algebra,oop,algebraGer;
    Teacher noah,lisa;

    /**
     * Initialization of data for test methods
     */
   @BeforeEach
    void init() throws FileNotFoundException {

        //reading data from the file (the file contains 3 Courses)
        List<Course>courseRepoList=new ArrayList<>();
        courseRepo=new CourseRepository(courseRepoList);

        List<Student>studentListOop=new ArrayList<>();
        List<Student>studentListAlgebra=new ArrayList<>();

        List<Course>courseListLisa=new ArrayList<>();
        List<Course>courseListNoah=new ArrayList<>();


        lisa=new Teacher("Lisa","David",122L,courseListLisa);
        noah=new Teacher("Noah","Williams",129L,courseListNoah);

        oop= new Course(203L,"OOP",lisa,1,studentListOop,3);
        algebra= new Course(204L,"Algebra",noah,2,studentListAlgebra,5);
        algebraGer= new Course(204L,"Algebra German",noah,2,studentListAlgebra,5);
    }

    /**
     * Tests the save methode
     */
   @Test
    void save() {


        Iterable<Course>courseListBeforeSave= courseRepo.findAll();
        long size=courseListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,3L);

        //adding to the repo
        courseRepo.save(oop);
        courseRepo.save(algebra);

        Iterable<Course>courseListAfterSave= courseRepo.findAll();
        long newSize=courseListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,5L);


    }
    /**
     * Tests the findOne methode
     */
    @Test
    void findOne() {

        courseRepo.save(oop);


        Long id=oop.getCourseID();
        Course findCourse= courseRepo.findOne(id);
        assertEquals(oop,findCourse);

        Long idFromFileReadCourse=200L;
        Course findDatabaseCourse= courseRepo.findOne(idFromFileReadCourse);
        assertEquals(200,findDatabaseCourse.getCourseID());

        Long algebraGerCourseID=algebraGer.getCourseID();
        Course findCourseAlgebra= courseRepo.findOne(algebraGerCourseID);
        assertNull(findCourseAlgebra);

    }
    /**
     * Tests the findAll methode
     */
   @Test
    void findAll() {

        courseRepo.save(oop);

        Iterable<Course>allCourses= courseRepo.findAll();
        long size=allCourses.spliterator().getExactSizeIfKnown();

        assertEquals(size,4L);


    }
    /**
     * Tests the delete methode
     */
    @Test
    void delete() {

        courseRepo.save(oop);
        courseRepo.save(algebra);

        Iterable<Course>courseList= courseRepo.findAll();
        long size=courseList.spliterator().getExactSizeIfKnown();
        assertEquals(size,5L);

        courseRepo.delete(algebra.getCourseID());

        Iterable<Course>newListOfCourses= courseRepo.findAll();
        long newSize=newListOfCourses.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,4L);




    }
    /**
     * Tests the update methode
     */
    @Test
    void update() {

        courseRepo.save(algebra);

        Course updatedCourse=courseRepo.update(algebraGer);
        assertNull(updatedCourse);

        Course CourseNotFound=courseRepo.update(oop);
        assertNotNull(CourseNotFound);



    }
}