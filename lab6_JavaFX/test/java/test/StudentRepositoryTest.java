package test;

import com.example.lab6_javafx.Exception.NullException;
import com.example.lab6_javafx.Model.Student;
import com.example.lab6_javafx.Repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {
    StudentRepository studentRepository;
    Student student1,student2,student3,student4,newStudent;
    @BeforeEach
    void setUp() {

        student1=new Student("Emma","Collin",500L,11,null);
        student2=new Student("Emily","Davis",501L,5,null);
        student3=new Student("Joshua","Walker",502L,11,null);
        student4=new Student("Liam","Walker",507L,30,null);

        newStudent=new Student("studentFirstName","studentLastName",503L,12,null);


        studentRepository=new StudentRepository("jdbc:mysql://localhost:3306/lab5_map_test");
    }

    @Test
    void testFindOne() {

        /*searching for exiting student*/
        try{
            Student foundStudent1=studentRepository.findOne(student1.getID());
            assertEquals(student1,foundStudent1);
        } catch (NullException e) {
            e.printStackTrace();
        }

        /*searching for non-exiting student */
        try {
            Student notSavedStudent = new Student("studentFirstName", "studentLastName", 506L, 12, null);
            Student notFoundStudent=studentRepository.findOne(notSavedStudent.getID());
            assertNull(notFoundStudent);
        } catch (NullException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testFindAll() {

        /* creating the expected result*/
        List<Student> expectedResult= new ArrayList<>();
        expectedResult.add(student1);
        expectedResult.add(student2);
        expectedResult.add(student3);
        expectedResult.add(student4);

        List<Student>foundStudent=(List<Student>) studentRepository.findAll();

        /*testing if the expected result contains the actual List*/
        for (Student student:foundStudent)
        {
            assertTrue(expectedResult.contains(student));
        }

    }

    @Test
    void testSave() {

        /* checking and saving the amount of data read */
        Iterable<Student>studentListBeforeSave= studentRepository.findAll();
        long size=studentListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,4L);

        try{
            studentRepository.save(newStudent);}
        catch (NullException e)
        {e.printStackTrace();}

        /* comparing the old size of the read data to new size */
        Iterable<Student>studentListAfterSave= studentRepository.findAll();
        long newSize=studentListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,size+1);

        /* undoing changes */
        try{
            studentRepository.delete(newStudent.getID());}
        catch (NullException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    void testDelete() {

        /* saving new student */
        try{
            studentRepository.save(newStudent);
        }
        catch (NullException e)
        {
            e.printStackTrace();
        }

        /* deleting new student */
        try{
            assertEquals(newStudent,studentRepository.delete(newStudent.getID()));}
        catch (NullException e)
        {
            e.printStackTrace();
        }
        /* trying to delete non-exiting student */
        try{
            assertNull(studentRepository.delete(5L));}
        catch (NullException e)
        {
            e.printStackTrace();
        }

        /* searching for deleted student, to make sure delete was successful*/
        try{
            assertNull(studentRepository.findOne(newStudent.getID()));}
        catch (NullException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    void update() {

        /* checking data before update*/
        try{
            Student findStudentToUpdate=studentRepository.findOne(student1.getID());
            assertEquals(findStudentToUpdate.getTotalCredits(),11);
        } catch (NullException e) {
            e.printStackTrace();
        }

        /* searching for student and updating data */
        try{
            student1.setTotalCredits(12);
            studentRepository.update(student1);
            try{
                Student findUpdatedStudent=studentRepository.findOne(student1.getID());
                assertEquals(findUpdatedStudent.getTotalCredits(),12);}
            catch (NullException e)
            {
                e.printStackTrace();
            }

        } catch (NullException e) {
            e.printStackTrace();
        }

        /* undoing changes*/
        try{
            student1.setTotalCredits(11);
            studentRepository.update(student1);
        } catch (NullException e) {
            e.printStackTrace();
        }

    }

}