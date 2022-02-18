package Controller;
import Exception.InputException;
import Exception.NullException;
import Model.Course;
import Model.Student;
import Model.Teacher;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * tests the methods from the RegistrationSystemControllerTest class
 *
 * @author Bohm Evelin
 * @version  07.12.2021
 * @since 1.0
 */
class RegistrationSystemControllerTest {


    StudentRepository studentRepository;
    Student student1,student2,student3,student4,newStudent;
    TeacherRepository teacherRepository;
    Teacher teacher1,teacher2,newTeacher;
    CourseRepository courseRepository;
    Course course1,course2,course3,course3UpdatedVersion,course4;
    RegistrationSystemController registrationSystemController;

    @BeforeEach
    void setUp() {
        course1= new Course(1L,"Database",null,5,null,6);
        course2= new Course(2L,"Statistics",null,2,null,5);
        course3= new Course(3L,"Advanced programing methods",null,20,null,5);
        course3UpdatedVersion= new Course(3L,"Advanced programing methods",null,15,null,5);
        course4= new Course(4L,"OOP",null,1,null,30);

        student1=new Student("Emma","Collin",500L,11,null);
        student2=new Student("Emily","Davis",501L,5,null);
        student3=new Student("Joshua","Walker",502L,11,null);
        student4=new Student("Liam","Walker",507L,30,null);

        newStudent=new Student("studentFirstName","studentLastName",503L,12,null);
        teacher1=new Teacher("Noah","Hall",100L,null);
        teacher2=new Teacher("Amanda","Morgan",101L,null);
        newTeacher=new Teacher("TeacherFirstName","TeacherLastName",102L,null);

        teacherRepository=new TeacherRepository("jdbc:mysql://localhost:3306/lab5_map_test");
        studentRepository=new StudentRepository("jdbc:mysql://localhost:3306/lab5_map_test");
        courseRepository=new CourseRepository("jdbc:mysql://localhost:3306/lab5_map_test");

        registrationSystemController=new RegistrationSystemController(studentRepository,teacherRepository,courseRepository);
    }

    @Test
    void testRegister() {
        /* checking data before registration */
        Student findStudent=registrationSystemController.findStudent(student2.getID());
        assertEquals(findStudent.getTotalCredits(),5);


        Student foundStudent=registrationSystemController.findStudent(student2.getID());
        List<Course>courseListOfStudent=foundStudent.getEnrolledCourses();
        Course foundCourse=registrationSystemController.findCourse(course1.getCourseID());
        List<Student> studentsRegisteredToCourse=foundCourse.getStudentsEnrolled();

        /*the student is registered to a single course and the course contains a 2 students */
        assertEquals(studentsRegisteredToCourse.size(),2);
        assertEquals(courseListOfStudent.size(),1);

        /* register existing student */
        try{
            boolean successfullyRegisteredStudent=registrationSystemController.register(foundStudent,foundCourse);
            assertTrue(successfullyRegisteredStudent);
            Student findStudentAfterRegistration=registrationSystemController.findStudent(student2.getID());
            //the credits score of the student has been successfully updated
            assertEquals(findStudentAfterRegistration.getTotalCredits(),11);
            //the students course Lists contains the new enrollment and the student list of the course contains the new student enrolled
            foundCourse=registrationSystemController.findCourse(course1.getCourseID());
            assertEquals(foundCourse.getStudentsEnrolled().size(),3);
            assertEquals(findStudentAfterRegistration.getEnrolledCourses().size(),2);

        } catch (InputException | NullException e) {
            e.printStackTrace();
        }

        /*register a non-existing student*/
        boolean caughtException = false;
        try{
            Student notSavedStudent = new Student("studentFirstName", "studentLastName", 506L, 12, null);
            boolean failedRegistration=registrationSystemController.register(notSavedStudent,course3);
            assertFalse(failedRegistration);
        } catch (InputException | NullException e) {
            caughtException=true;
        }
        assertTrue(caughtException);
        caughtException=false;

        /*register a non-existing course*/
        try{
            Course notSavedCourse = new Course(8L,"newCourse",null,5,null,6);
            boolean failedRegistration=registrationSystemController.register(student3,notSavedCourse);
            assertFalse(failedRegistration);
        } catch (InputException e) {
            caughtException=true;
        } catch (NullException e) {
            e.printStackTrace();
        }
        assertTrue(caughtException);

        /*trying to enroll to a course with no available places*/
        foundCourse=registrationSystemController.findCourse(course2.getCourseID());
        studentsRegisteredToCourse=foundCourse.getStudentsEnrolled();
        foundStudent=registrationSystemController.findStudent(student3.getID());
        courseListOfStudent=foundStudent.getEnrolledCourses();

        assertEquals(studentsRegisteredToCourse.size(),2);
        assertEquals(courseListOfStudent.size(),2);

        try{
            boolean failedRegistration=registrationSystemController.register(student3,course2);

            assertFalse(failedRegistration);
            assertEquals(studentsRegisteredToCourse.size(),2);
            assertEquals(courseListOfStudent.size(),2);

        } catch (InputException | NullException e) {
            e.printStackTrace();
        }

        /*trying to enroll to a student with 30 credits*/
        Student studentWith30Credits=new Student("Liam","Walker",507L,30,null);
        try{
            boolean failedRegistration=registrationSystemController.register(studentWith30Credits,course2);
            assertFalse(failedRegistration);
        } catch (InputException | NullException e) {
            e.printStackTrace();
        }
        //undoing changes
        try{
        registrationSystemController.getStudentRepository().delete(student2.getID());
        findStudent.setTotalCredits(0);
        registrationSystemController.getStudentRepository().save(findStudent);

        } catch (NullException e) {
            e.printStackTrace();
        }
        try {
        Course registeredCourse=registrationSystemController.findCourse(course3.getCourseID());
        registrationSystemController.register(findStudent,registeredCourse);
        } catch (InputException | NullException e) {
            e.printStackTrace();
        }


    }

    @Test
    void TestRetrieveCoursesWithFreePlaces() {
        List<Course>expectedResult=new ArrayList<>();
        expectedResult.add(course1);
        expectedResult.add(course3);

        List<Course>actualResult=registrationSystemController.retrieveCoursesWithFreePlaces();

        for(Course course:actualResult)
        {
            assertTrue(expectedResult.contains(course));
        }
        assertEquals(actualResult.size(),2);
    }

    @Test
    void testDeleteCourseByTeacher() {
        /* checking data before update */

        Teacher foundTeacher=registrationSystemController.findTeacher(teacher2.getID());
        Student foundStudent=registrationSystemController.findStudent(student4.getID());
        List<Course>courseListStudent=foundStudent.getEnrolledCourses();
        Course foundCourse=registrationSystemController.findCourse(course4.getCourseID());
        List<Student> studentList=foundCourse.getStudentsEnrolled();
        //saving course to undo changes
        Course savedCourse= registrationSystemController.findCourse(course4.getCourseID());

        assertEquals(courseListStudent.size(),1);
        assertEquals(studentList.size(),1);

        try
        {
            boolean deletedCourse=registrationSystemController.deleteCourseByTeacher(foundCourse,foundTeacher);
            assertTrue(deletedCourse);
        } catch (InputException | NullException e) {
            e.printStackTrace();
        }


        foundStudent=registrationSystemController.findStudent(student4.getID());
        courseListStudent=foundStudent.getEnrolledCourses();

        assertEquals(courseListStudent.size(),0);
        assertNull(registrationSystemController.findCourse(course4.getCourseID()));

        /* trying to delete a course that is not taught by the given teacher */
        try
        {
            foundCourse=registrationSystemController.findCourse(course3.getCourseID());
            foundTeacher=registrationSystemController.findTeacher(teacher1.getID());
            boolean deletedCourse=registrationSystemController.deleteCourseByTeacher(foundCourse,foundTeacher);
            assertFalse(deletedCourse);

        } catch (InputException | NullException e) {
            e.printStackTrace();
        }

        /* redoing changes*/
        try
        {

            registrationSystemController.getCourseRepository().save(savedCourse);
        } catch (NullException e) {
            e.printStackTrace();
        }


        try{
             foundTeacher=registrationSystemController.findTeacher(teacher2.getID());
             foundStudent=registrationSystemController.findStudent(student4.getID());
             registrationSystemController.register(foundStudent,savedCourse);
        } catch (InputException | NullException e) {
            e.printStackTrace();
        }



    }

    @Test
    void sortStudentsAlphabetically() {
        List<Student> sortedStudents = registrationSystemController.sortStudentsAlphabetically();
        /* creating the expected result */
        List<Student> expectedStudentsList = new ArrayList<>();
        expectedStudentsList.add(student1);
        expectedStudentsList.add(student2);
        expectedStudentsList.add(student3);
        expectedStudentsList.add(student4);
        assertArrayEquals(expectedStudentsList.toArray(), sortedStudents.toArray());
    }

    @Test
    void sortCoursesAlphabetically() {
        List<Course> courseList =registrationSystemController.sortCoursesAlphabetically();
        /* creating the expected result */
        List<Course> expectedCourseList = new ArrayList<>();
        expectedCourseList.add(course3);
        expectedCourseList.add(course1);
        expectedCourseList.add(course4);
        expectedCourseList.add(course2);
        assertArrayEquals(expectedCourseList.toArray(), courseList.toArray());
    }


    @Test
    void filterStudentsByCreditNr() {
        List<Student> filteredStudents = registrationSystemController.filterStudentsByCreditNr(11);
        /* building the expected result */
        List<Student> expectedStudentsList = new ArrayList<>();
        expectedStudentsList.add(student1);
        expectedStudentsList.add(student3);
        assertArrayEquals(expectedStudentsList.toArray(), filteredStudents.toArray());
    }

    @Test
    void filterCoursesByCreditNr() {
        List<Course> filteredCourses = registrationSystemController.filterCoursesByCreditNr(6);
        /* building the expected result */
        List<Course> expectedCoursesList = new ArrayList<>();
        expectedCoursesList.add(course1);
        assertArrayEquals(expectedCoursesList.toArray(), filteredCourses.toArray());

    }

}