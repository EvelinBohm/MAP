package com.company.controller;

import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.CourseRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * the RegistrationSystemController class uses the methods from the ICrudRepository interface
 * to implement 3 main methods
 *
 * @author Bohm Evelin
 * @version  %I%, %G%
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

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

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
     * deletes a course (the course can be deleted just from a teacher)
     * @param course-must not be null
     * @param teacher-must not be null
     * @return course-if the course has been deleted or null if the course couldn't be found
     */
    public Course deleteCourseByTeacher(Course course, Teacher teacher)
    {
        List<Course>teachersCourses=teacher.getCourses();
        for (Course teachersCourse:teachersCourses)
        {

            if (teachersCourse.getCourseID().equals(course.getCourseID()))
            {
                courseRepository.delete(course.getCourseID());

                for (Student student:retrieveStudentsEnrolledForACourse(course))
                {
                    student.setTotalCredits(student.getTotalCredits() - course.getCredits());
                    List<Course>updatedList=student.getEnrolledCourses();
                    updatedList.remove(course);
                    student.setEnrolledCourses(updatedList);

                }
                course.getStudentsEnrolled().clear();
                return course;
            }

        }
        return null;

    }

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

    public List<Course> filterCoursesByCreditNr(int credits)
    {
        List<Course> courses=(List<Course>)getAllCourses();
        return courses.stream().filter(course -> course.getCredits()==credits).collect(Collectors.toList());
    }


}
