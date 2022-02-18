package com.company.Controller;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.Repository.CourseRepository;
import com.company.Repository.StudentRepository;
import com.company.Repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * the RegistrationSystemController class implements
 * 2 sort methods
 * 2 filter methods
 * a register method that registers a student to a course
 * a retrieveCoursesWithFreePlaces method that searches for courses with available places
 * a deleteCourseByTeacher method that delete a course
 *
 * @author Bohm Evelin
 * @version  16.11.2021
 * @since 1.0
 *
 */

public class RegistrationSystemController
{
    private  StudentRepository studentRepository;
    private  TeacherRepository teacherRepository;
    private  CourseRepository courseRepository;
    /**
     * initializes the repository
     * @param studentRepository-most not be null
     * @param teacherRepository-most not be null
     * @param courseRepository-most not be null
     */
    public RegistrationSystemController(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * gets the student repository
     * @return a student repository
     */
    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    /**
     * gets the teacher repository
     * @return a teacher repository
     */
    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    /**
     * gets the course repository
     * @return a course repository
     */
    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    /**
     * the methode registers a student to a curse, and adds the course to the students courses
     * @param student- student to be registered for a course , student must not be null
     * @param course-course to which the student wants to register, course must not be null
     * @return true- if the student was registered to the course or false - if there aren't places available for the course or if the student already has 30 credits
     */
    public boolean register( Student student,Course course)
    {

        if((course.getMaxEnrollment()-course.getNrOfEnrolledStudents())>0){
            if(student.getTotalCredits() + course.getCredits() <= 30) {
               course.addStudent(student);
                student.addCourse(course);
                return true;
            }
        }

        return false;
    }

    /**
     * the methode gets the List of courses
     * @return an iterable of courses
     */
    public Iterable<Course> getAllCourses()
    {
        return  courseRepository.findAll();
    }


    /**
     * finds all courses that still have available places
     * @return List of courses with free places
     */
    public List<Course> retrieveCoursesWithFreePlaces()
    {
        List<Course>couresWithAvailabelPlaces=new ArrayList<>();
        for(Course course:getAllCourses())
        {
            if(course.getMaxEnrollment()-course.getStudentsEnrolled().size()>0)
            {
                couresWithAvailabelPlaces.add(course);
            }

        }
        return couresWithAvailabelPlaces;

    }

    /**
     * the methode finds all courses to which a student is registered
     * @param course-must not be null
     * @return list of students from a course
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Course course)
    {

        return course.getStudentsEnrolled();

    }

    /**
     * deletes a course (the course can be deleted just by a teacher)
     * @param course-must not be null
     * @param teacher-must not be null
     * @return course-if the course has been deleted or null if the course couldn't be found
     */
    public Course deleteCourseByTeacher(Course course, Teacher teacher,List<Student> studentsEnrolledForACourse)
    {
        List<Course>teachersCourses=teacher.getCourses();
        for (Course teachersCourse:teachersCourses)
        {

            if (teachersCourse.getCourseID().equals(course.getCourseID()))
            {
                courseRepository.delete(course.getCourseID());

                for (Student student:studentsEnrolledForACourse)
                {
                    student.setTotalCredits(student.getTotalCredits() - course.getCredits());
                    student.delete(course);

                }
                course.getStudentsEnrolled().clear();
                return course;
            }

        }
        return null;

    }

    /**
     * sorts a list of students alphabetically after the students last name
     * @param students-must not be null
     * @return the sorted list of students
     */
    public List<Student> sortStudentsAlphabetically(List<Student>students)
    {
        List<Student> sortedList = new ArrayList<>(students);
        sortedList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return sortedList;
    }

    /**
     * sorts a list of courses alphabetically
     * @param courses-must not be null
     * @return the sorted list of courses
     */
    public List<Course> sortCoursesAlphabetically(List<Course> courses)
    {
        List<Course> sortedList = new ArrayList<>(courses);
        sortedList.sort(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return sortedList;
    }

    /**
     * filters a list of students after a credit score
     * @param credits-must be a positive number
     * @param studentList -must be not null
     * @return a list of students
     */
    public List<Student> filterStudentsByCreditNr(int credits,List<Student>studentList)
    {
        List<Student> filteredStudents=new ArrayList<>();
        for (Student student:studentList)
        {
            if (student.getTotalCredits()==credits)
                filteredStudents.add(student);
        }
        return filteredStudents;
    }
    /**
     * filters a list of courses after a credit score
     * @param credits-must be a positive number
     * @return a list of courses
     */
    public List<Course> filterCoursesByCreditNr(int credits)
    {
        List<Course> courses=(List<Course>)getAllCourses();
        return courses.stream().filter(course -> course.getCredits()==credits).collect(Collectors.toList());
    }


}
