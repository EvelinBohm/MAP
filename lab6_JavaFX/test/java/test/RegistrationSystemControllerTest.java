package test;

import com.example.lab6_javafx.Controller.RegistrationSystemController;
import com.example.lab6_javafx.Exception.InputException;
import com.example.lab6_javafx.Exception.NullException;
import com.example.lab6_javafx.Model.Course;
import com.example.lab6_javafx.Model.Student;
import com.example.lab6_javafx.Model.Teacher;
import com.example.lab6_javafx.Repository.CourseRepository;
import com.example.lab6_javafx.Repository.StudentRepository;
import com.example.lab6_javafx.Repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        List<Course> courseListOfStudent=foundStudent.getEnrolledCourses();
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
    void testRetrieveCoursesWithFreePlaces() {
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
    void testGetStudentsEnrolledFor() {
        List<Student>expectedResult=new ArrayList<>();
        expectedResult.add(student4);

        List<Student>actualResult=registrationSystemController.getStudentsEnrolledFor(course4.getCourseID());

        for(Student student:actualResult)
        {
            assertTrue(expectedResult.contains(student));
        }
        assertEquals(actualResult.size(),1);
    }
    @Test
    void testFindStudent() {
        Student foundStudent=registrationSystemController.findStudent(student4.getID());
        Long actualStudentId=student4.getID();
        assertEquals(actualStudentId,foundStudent.getID());

    }
    @Test
    void testFindCourse() {
        Course foundCourse=registrationSystemController.findCourse(course1.getCourseID());
        Long actualCourseId=course1.getCourseID();
        assertEquals(actualCourseId,foundCourse.getCourseID());

    }
    @Test
    void testFindTeacher() {
        Teacher foundTeacher=registrationSystemController.findTeacher(teacher1.getID());
        Long actualTeacherId=teacher1.getID();
        assertEquals(actualTeacherId,foundTeacher.getID());

    }


}