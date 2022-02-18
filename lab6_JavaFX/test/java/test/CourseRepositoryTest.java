package test;

import com.example.lab6_javafx.Exception.NullException;
import com.example.lab6_javafx.Model.Course;
import com.example.lab6_javafx.Model.Teacher;
import com.example.lab6_javafx.Repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepositoryTest {

    CourseRepository courseRepo;
    Course course1,course2,course3,course3UpdatedVersion,course4;
    @BeforeEach
    void setUp() {


        course1= new Course(1L,"Database",null,5,null,6);
        course2= new Course(2L,"Statistics",null,2,null,5);
        course3= new Course(3L,"Advanced programing methods",null,20,null,5);
        course3UpdatedVersion= new Course(3L,"Advanced programing methods",null,15,null,5);
        course4= new Course(4L,"OOP",null,1,null,30);

        courseRepo=new CourseRepository("jdbc:mysql://localhost:3306/lab5_map_test");

    }

    @org.junit.jupiter.api.Test
    void testFindAll() {
        /* creating the expected result*/
        List<Course> expectedResult= new ArrayList<>();
        expectedResult.add(course1);
        expectedResult.add(course2);
        expectedResult.add(course3);
        expectedResult.add(course4);

        List<Course>foundCourses=(List<Course>) courseRepo.findAll();

        /*testing if the expected result contains the actual List*/
        for (Course course:foundCourses)
        {
            assertTrue(expectedResult.contains(course));
        }

        Iterable<Course>allCourses= courseRepo.findAll();
        long size=allCourses.spliterator().getExactSizeIfKnown();
        assertEquals(size,4L);

    }
    @org.junit.jupiter.api.Test
    void testFindOne() {

        /* searching for an existing course*/
        try
        {
            Course courseFound=courseRepo.findOne(1L);
            assertEquals(course1,courseFound);
        }
        catch (NullException e) {
            e.printStackTrace();
        }

        /* searching for non-existing course*/
        try
        {
            Course courseNotFound=courseRepo.findOne(6L);
            assertNull(courseNotFound);
        }
        catch (NullException e) {
            e.printStackTrace();
        }


    }

    @org.junit.jupiter.api.Test
    void testSave() {

        /* checking and saving the amount of data read */
        Iterable<Course>courseListBeforeSave= courseRepo.findAll();
        long size=courseListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,4L);

        Teacher teacher=new Teacher();
        teacher.setID(100L);
        teacher.setFirstName("Hanna");
        teacher.setLastName("New");
        Course newCourse=new Course(5L,"OOP2",teacher,5,null,6);

        try{
            courseRepo.save(newCourse);}
        catch (NullException e)
        {e.printStackTrace();}

        /* comparing the old size of the read data to new size */
        List<Course>courseListAfterSave=(List<Course>) courseRepo.findAll();
        long newSize=courseListAfterSave.size();
        assertEquals(newSize,size+1);

        /*undoing changes */
        try{
            courseRepo.delete(newCourse.getCourseID());}
        catch (NullException e)
        {
            e.printStackTrace();
        }


    }

    @org.junit.jupiter.api.Test
    void testDelete() {


        Teacher teacher=new Teacher();
        teacher.setID(100L);
        teacher.setFirstName("Hanna");
        teacher.setLastName("New");
        Course newCourse=new Course(5L,"OOP2",teacher,5,null,6);

        /* saving new course */
        try{
            courseRepo.save(newCourse);
        }
        catch (NullException e)
        {
            e.printStackTrace();
        }

        /* deleting new course */
        try{
            assertEquals(newCourse,courseRepo.delete(5L));}
        catch (NullException e)
        {
            e.printStackTrace();
        }
        /* trying to delete non-exiting course */
        try{
            assertNull(courseRepo.delete(6L));}
        catch (NullException e)
        {
            e.printStackTrace();
        }

        /* searching for deleted course, to make sure delete was successful*/
        try{
            assertNull(courseRepo.findOne(newCourse.getCourseID()));}
        catch (NullException e)
        {
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.Test
    void testUpdate() {
        /* checking data before update*/
        try{
            Course findCourseToUpdate=courseRepo.findOne(course3.getCourseID());
            assertEquals(findCourseToUpdate.getMaxEnrollment(),20);
        } catch (NullException e) {
            e.printStackTrace();
        }

        /* searching for course and updating it */
        try{
            courseRepo.update(course3UpdatedVersion);
            try{
                Course findUpdatedCourse=courseRepo.findOne(course3UpdatedVersion.getCourseID());
                assertEquals(findUpdatedCourse.getMaxEnrollment(),15);}
            catch (NullException e)
            {
                e.printStackTrace();
            }

        } catch (NullException e) {
            e.printStackTrace();
        }

        /* undoing changes*/
        try{
            courseRepo.update(course3);
        } catch (NullException e) {
            e.printStackTrace();
        }


    }


}