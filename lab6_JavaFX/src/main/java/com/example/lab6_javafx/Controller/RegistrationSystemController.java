package com.example.lab6_javafx.Controller;
import com.example.lab6_javafx.Exception.InputException;
import com.example.lab6_javafx.Exception.NullException;
import com.example.lab6_javafx.Model.Course;
import com.example.lab6_javafx.Model.Student;
import com.example.lab6_javafx.Model.Teacher;
import com.example.lab6_javafx.Repository.CourseRepository;
import com.example.lab6_javafx.Repository.StudentRepository;
import com.example.lab6_javafx.Repository.TeacherRepository;
import java.util.List;
import java.util.stream.Collectors;


/**
 * the RegistrationSystemController class has the following functionalities
 * enrolling a student to a course
 * retrieving courses with available places
 * getting all students enrolled for a course,getting all courses,finding a course,finding a student,finding a teacher
 *
 * @author Bohm Evelin
 * @version  14.12.2021
 * @since 1.0
 *
 */

public class RegistrationSystemController {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    /**
     * initializes the repository
     *
     * @param studentRepository-most not be null
     * @param teacherRepository-most not be null
     * @param courseRepository-most  not be null
     */
    public RegistrationSystemController(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }


    /**
     * the methode registers a student to a curse, and adds the course to the students courses
     *
     * @param student-student to be registered for a course , student must not be null
     * @param course-course to which the student wants to register, course must not be null
     * @return true- if the student was registered to the course or false - if there aren't places available for the course or if the student already has 30 credits
     */
    public boolean register(Student student, Course course) throws InputException, NullException {

        Student foundStudent = studentRepository.findOne(student.getID());
        Course foundCourse = courseRepository.findOne(course.getCourseID());

        if (foundStudent == null || foundCourse == null) {
            throw new InputException("Non-existing id!");
        }
        List<Student>studentsEnrolledForCourse=foundCourse.getStudentsEnrolled();
        //check if the course has available places
        if (studentsEnrolledForCourse.size()==course.getMaxEnrollment())
            return false;
        //check if student will have over 30 credits after enrollment
        if(student.getTotalCredits()+course.getCredits()>30)
            return false;

        //check if student is already enrolled
        boolean alreadyEnrolledStudent=studentsEnrolledForCourse
                .stream()
                .anyMatch(student1 ->student1.compareTo(student));

        if (alreadyEnrolledStudent)
            return false;
        //enrolling student
        int creditNr = foundStudent.getTotalCredits() + foundCourse.getCredits();
        foundStudent.setTotalCredits(creditNr);
        studentRepository.update(foundStudent);
        studentRepository.saveRegistration(student,foundCourse);
        return true;

    }


    /**
     * finds all courses that still have available places
     * @return List of courses with free places
     */
    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> couresWithAvailabelPlaces = (List<Course>) courseRepository.findAll();
        return couresWithAvailabelPlaces
                .stream()
                .filter(course -> (course.getMaxEnrollment() -course.getNrOfEnrolledStudents())>0)
                .collect(Collectors.toList());

    }

    /**
     * gets all enrolled students for a course
     * @param courseID-Course Object
     * @return list of students enrolled for a course
     */
    public List<Student> getStudentsEnrolledFor(Long courseID) {
       try{
           Course foundCourse=findCourse(courseID);
        if (foundCourse != null) {
            return foundCourse.getStudentsEnrolled();
        }} catch (Exception e) {
           e.printStackTrace();
       }

        return null;
    }
    /**
     * retrieves all courses of a teacher
     * @param teacher-Teacher object, must be not null
     * @return list of courses taught by a teacher
     */
    public List<Course>getAllCoursesOfTeacher(Teacher teacher)
    {
        try{
            if (teacherRepository.findOne(teacher.getID()) != null) {
                return teacher.getCourses();
            }
        } catch (NullException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * retrieves all courses
     * @return list of courses
     */
    public List<Course>getAllCourses()
    {
       return (List<Course>)courseRepository.findAll();
    }

    /**
     * searches for a course
     * @param ID-Long, id of searched course
     * @return Course object-if the course was found, null- if it couldn't be found
     */
    public Course findCourse(Long ID)
    {
        Course foundCourse;
        try
        {
            foundCourse=courseRepository.findOne(ID);
        } catch (NullException e) {
            return null;
        }
        return foundCourse;
    }


    /**
     * searches for a student
     * @param ID-Long, id of searched student
     * @return Student object-if the student was found, null- if the student couldn't be found
     */
    public Student findStudent(Long ID)
    {
        Student foundStudent;
       try
       {
            foundStudent=studentRepository.findOne(ID);

       } catch (NullException e) {
           return null;
       }
        return foundStudent;
    }
    /**
     * searches for a teacher
     * @param ID-Long, id of searched teacher
     * @return Teacher object-if the teacher was found, null- if the teacher couldn't be found
     */
    public Teacher findTeacher(Long ID)
    {
        Teacher foundTeacher;
        try
        {foundTeacher=teacherRepository.findOne(ID);

        } catch (NullException e) {
            return null;
        }
        return foundTeacher;
    }

    //setters
    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }
}