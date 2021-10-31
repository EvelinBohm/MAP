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
 * Tests the methods from the CourseRepository class
 * @author Bohm Evelin
 * @version  %I%, %G%
 * @since 1.0
 */
class CourseRepositoryTest {
    CourseRepository CourseRepo;
    Course map,bd,stat,bd_ger;
    Teacher Peter,Linda;

    /**
     * Initialization of data for test methods
     */
    @BeforeEach
    void init(){

        CourseRepo=new CourseRepository();

        List<Student>studentsMapList=new ArrayList<>();
        List<Student>studentsBdList=new ArrayList<>();
        List<Student>studentsStatList=new ArrayList<>();

        List<Course>courseListPeter=new ArrayList<>();
        List<Course>courseListLinda=new ArrayList<>();


        Peter=new Teacher("Peter","Mitchell",123L,courseListPeter);
        Linda=new Teacher("Linda","Wood",127L,courseListLinda);

        map= new Course(125L,"map",Linda,1,studentsMapList,3);
        bd= new Course(123L,"bd",Peter,2,studentsBdList,5);
        bd_ger= new Course(123L,"bd",Peter,4,studentsBdList,7);
        stat= new Course(126L,"stat",Peter,6,studentsStatList,6);

    }

    /**
     * Tests the save methode
     */
    @Test
    void save() {

        //the repo is empty at the begining
        Iterable<Course>courseListBeforeSave= CourseRepo.findAll();
        long size=courseListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,0L);

        //adding to the repo
        CourseRepo.save(map);
        CourseRepo.save(bd);

        Iterable<Course>courseListAfterSave= CourseRepo.findAll();
        long newSize=courseListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,2L);


    }
    /**
     * Tests the findOne methode
     */
    @Test
    void findOne() {

        CourseRepo.save(map);
        CourseRepo.save(bd);

        Long id=map.getCourseID();
        Course findCourse= CourseRepo.findOne(id);
        assertEquals(map,findCourse);

        Long statID=stat.getCourseID();
        Course findCourseStat= CourseRepo.findOne(statID);
        assertNull(findCourseStat);

    }
    /**
     * Tests the findAll methode
     */
    @Test
    void findAll() {

        CourseRepo.save(map);
        CourseRepo.save(bd);

        Iterable<Course>allCourses= CourseRepo.findAll();
        long size=allCourses.spliterator().getExactSizeIfKnown();

        assertEquals(size,2L);


    }
    /**
     * Tests the delete methode
     */
    @Test
    void delete() {

        CourseRepo.save(map);
        CourseRepo.save(bd);

        Iterable<Course>courseList= CourseRepo.findAll();
        long size=courseList.spliterator().getExactSizeIfKnown();
        assertEquals(size,2L);

        CourseRepo.delete(bd.getCourseID());

        Iterable<Course>newListOfCourses= CourseRepo.findAll();
        long newSize=newListOfCourses.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,1L);




    }
    /**
     * Tests the update methode
     */
    @Test
    void update() {

        CourseRepo.save(map);
        CourseRepo.save(bd);

        Course updatedCourse=CourseRepo.update(bd_ger);
        assertNull(updatedCourse);

        Course CourseNotFound=CourseRepo.update(stat);
        assertNotNull(CourseNotFound);



    }
}