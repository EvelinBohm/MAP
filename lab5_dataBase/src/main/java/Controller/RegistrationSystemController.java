package Controller;
import Exception.InputException;
import Model.Course;
import Model.Person;
import Model.Student;
import Model.Teacher;
import Repository.CourseRepository;
import Repository.StudentRepository;
import Repository.TeacherRepository;
import Exception.NullException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * the RegistrationSystemController class has the following functionalities
 * sorting students by their last name,sorting courses by name
 * filter student/courses after credit
 * enrolling a student to a course
 * retrieving courses with available places
 * deleting a course from the teachers and students course list,updating the credit score of a student after the course was deleted
 * deleting the enrollment for the deleted course
 * getting all students enrolled for a course,getting all courses,finding a course,finding a student,finding a teacher,
 * saving the data to database
 *
 * @author Bohm Evelin
 * @version  07.12.2021
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
     * sorts a list of students alphabetically after the students last name
     * @return the sorted list of students
     */
    public List<Student> sortStudentsAlphabetically() {
        List<Student> sortedList = (List<Student>) studentRepository.findAll();
        sortedList.sort(
                Comparator.comparing(Person::getLastName));
        return sortedList;
    }

    /**
     * sorts a list of courses alphabetically
     * @return the sorted list of courses
     */
    public List<Course> sortCoursesAlphabetically() {
        List<Course> sortedList =(List<Course>) courseRepository.findAll();
        sortedList.sort(
                Comparator.comparing(Course::getName));
        return sortedList;
    }

    /**
     * filters a list of students after a credit score
     *
     * @param credits-must be a positive number
     * @return a list of students
     */
    public List<Student> filterStudentsByCreditNr(int credits) {
        List<Student> filteredStudents=(List<Student>)studentRepository.findAll();
        filteredStudents = filteredStudents
                .stream()
                .filter(student -> student.getTotalCredits() == credits)
                .collect(Collectors.toList());

        return filteredStudents;
    }

    /**
     * filters a list of courses after a credit score
     * @param credits-must be a positive number
     * @return a list of courses
     */
    public List<Course> filterCoursesByCreditNr(int credits) {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        return courses.stream().filter(course -> course.getCredits() == credits).collect(Collectors.toList());
    }

    /**
     * deletes a course (the course can be deleted just by a teacher)
     *
     * @param course-must  not be null
     * @param teacher-must not be null
     * @return course-if the course has been deleted or null if the course couldn't be found
     */
    public boolean deleteCourseByTeacher(Course course, Teacher teacher) throws InputException, NullException {

        Course foundCourse = courseRepository.findOne(course.getCourseID());
        Teacher foundTeacher = teacherRepository.findOne(teacher.getID());
        if (foundCourse == null || foundTeacher == null)
            throw new InputException("Non-existing id!");

        List<Course>courseListTeacher=foundTeacher.getCourses();
        boolean courseFound=courseListTeacher
                .stream()
                .anyMatch(course1-> course1.compareTo(course));

        if (!courseFound)
            return false;
        updateStudentCredits(course);
        courseRepository.delete(course.getCourseID());

        return true;

    }


    /**
     * gets all enrolled students for a course
     * @param course-Course Object
     * @return list of students enrolled for a course
     */
    public List<Student> getStudentsEnrolledFor(Course course) {
       try{
        if (courseRepository.findOne(course.getCourseID()) != null) {
            return course.getStudentsEnrolled();
        }
       } catch (NullException e) {
           e.printStackTrace();
       }

        return null;
    }

    /**
     * updates the credit score of a student
     */
    public void updateStudentCredits(Course course) {
         getAllStudents()
                 .stream()
                 .forEach(student -> {
                      if(student.getEnrolledCourses().contains(course)){
                          student.setTotalCredits(student.getTotalCredits()-course.getCredits());

                      try{
                         studentRepository.update(student);

                      } catch (NullException e) {
                         e.printStackTrace();
                      }
                 }});

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
     * retrieves all courses to which a student is enrolled
     * @param student-Student Object, must be not null
     * @return list of courses
     */
    public List<Course>getCoursesOfStudent(Student student)
    {
        try{
            if (studentRepository.findOne(student.getID()) != null) {
                return student.getEnrolledCourses();
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
     * retrieves all students
     * @return list of students
     */
    public List<Student>getAllStudents()
    {
        return (List<Student>)studentRepository.findAll();
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