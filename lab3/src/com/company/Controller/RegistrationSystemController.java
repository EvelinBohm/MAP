package com.company.Controller;

import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.ICrudRepository;

import java.util.ArrayList;
import java.util.List;

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
    private final ICrudRepository repository;

    /**
     * initializes the repository
     * @param repository-most not be null
     */
    public RegistrationSystemController(ICrudRepository repository) {
        this.repository = repository;
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
        return  repository.findAll();
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
                repository.delete(course.getCourseID());

                for (Student student:retrieveStudentsEnrolledForACourse(course))
                {
                    student.setTotalCredits(student.getTotalCredits() - course.getCredits());
                    List<Course>updatedList=student.getEnrolledCourses();
                    updatedList.remove(course);
                    student.setEnrolledCourses(updatedList);

                }
                return course;
            }

        }
        return null;

    }

}
