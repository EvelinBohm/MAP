package Repository;
import Exception.NullException;
import Model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherRepositoryTest {
    TeacherRepository teacherRepository;
    Teacher teacher1,teacher2,newTeacher;
    @BeforeEach
    void setUp() {

        teacher1=new Teacher("Noah","Hall",100L,null);
        teacher2=new Teacher("Amanda","Morgan",101L,null);
        newTeacher=new Teacher("TeacherFirstName","TeacherLastName",102L,null);

        teacherRepository=new TeacherRepository("jdbc:mysql://localhost:3306/lab5_map_test");

    }

    @Test
    void testFindOne() {
        /*searching for exiting teacher*/
        try{
            Teacher foundTeacher1=teacherRepository.findOne(teacher1.getID());
            assertEquals(teacher1,foundTeacher1);
        } catch (NullException e) {
            e.printStackTrace();
        }

        /*searching for non-exiting teacher */
        try {
            Teacher notSavedTeacher = new Teacher("TeacherFirstName", "TeacherLastName",105L,null);
            Teacher notFoundTeacher=teacherRepository.findOne(notSavedTeacher.getID());
            assertNull(notFoundTeacher);
        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFindAll() {
        /* creating the expected result*/
        List<Teacher> expectedResult= new ArrayList<>();
        expectedResult.add(teacher1);
        expectedResult.add(teacher2);

        List<Teacher>foundSTeacherList=(List<Teacher>) teacherRepository.findAll();

        /*testing if the expected result contains the actual List*/
        for (Teacher teacher:foundSTeacherList)
        {
            assertTrue(expectedResult.contains(teacher));
        }

    }

    @Test
    void testSave() {

        /* checking and saving the amount of data read */
        Iterable<Teacher>TeacherListBeforeSave= teacherRepository.findAll();
        long size=TeacherListBeforeSave.spliterator().getExactSizeIfKnown();
        assertEquals(size,2L);

        try{
            teacherRepository.save(newTeacher);}
        catch (NullException e)
        {e.printStackTrace();}

        /* comparing the old size of the read data to new size */
        Iterable<Teacher>teacherListAfterSave= teacherRepository.findAll();
        long newSize=teacherListAfterSave.spliterator().getExactSizeIfKnown();
        assertEquals(newSize,size+1);

        /* undoing changes */
        try{
            teacherRepository.delete(newTeacher.getID());}
        catch (NullException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    void testDelete() {
        /* saving new teacher */
        try{
            teacherRepository.save(newTeacher);
        }
        catch (NullException e)
        {
            e.printStackTrace();
        }

        /* deleting new teacher */
        try{
            assertEquals(newTeacher,teacherRepository.delete(newTeacher.getID()));}
        catch (NullException e)
        {
            e.printStackTrace();
        }
        /* trying to delete non-exiting teacher */
        try{
            assertNull(teacherRepository.delete(5L));}
        catch (NullException e)
        {
            e.printStackTrace();
        }

        /* searching for deleted teacher, to make sure delete was successful*/
        try{
            assertNull(teacherRepository.findOne(newTeacher.getID()));}
        catch (NullException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    void testUpdate() {
        /* checking data before update*/
        try{
            Teacher findTeacherToUpdate=teacherRepository.findOne(teacher1.getID());
            assertEquals(findTeacherToUpdate.getFirstName(),"Noah");
        } catch (NullException e) {
            e.printStackTrace();
        }

        /* searching for teacher and updating data */
        try{
            teacher1.setFirstName("Liam");
            teacherRepository.update(teacher1);
            try{
                Teacher findUpdatedTeacher=teacherRepository.findOne(teacher1.getID());
                assertEquals(findUpdatedTeacher.getFirstName(),"Liam");}
            catch (NullException e)
            {
                e.printStackTrace();
            }

        } catch (NullException e) {
            e.printStackTrace();
        }

        /* undoing changes*/
        try{
            teacher1.setFirstName("Noah");
            teacherRepository.update(teacher1);
        } catch (NullException e) {
            e.printStackTrace();
        }

    }
}