package com.company.repository;


import com.company.model.Course;
import java.util.List;

/**
 * CourseRepository extends the InMemoryRepository class
 * @author Bohm Evelin
 * @version  30.10.2021
 * @since 1.0
 */

public class CourseRepository extends InMemoryRepository<Course> {

    public CourseRepository(List<Course> repositoryList) {

        super(repositoryList);
    }



    /**
     * Overrides the findOne methode from the interface
     * searches for the course with the given id
     * @param courseID-must not be null
     * @return Course-if it's found or null -if the course does not exist
     */
    @Override
    public Course findOne(Long courseID)
    {

        for(Course course:repositoryList){
            if (course.getCourseID().equals(courseID))
                return course;
        }
        return null;
    }

    /**
     * Overrides the findAll methode from the interface
     * @return  Iterable of courses
     */
    @Override
    public Iterable<Course> findAll()
    {
        return repositoryList;
    }

    /**
     * Overrides the save methode from the interface
     * adds the course to the list of courses
     * @param newCourse-most be not null
     * @return course -if the course has been already saved or null -if the course was added successfully
     */
    @Override
    public Course save(Course newCourse)
    {
        for (Course course:repositoryList)
        {
            if (findOne(newCourse.getCourseID())!=null)
            {
                return newCourse;
            }
        }
        repositoryList.add(newCourse);
        return null;
    }

    /**
     * deletes the course with the given ID
     * @param id id must be not null
     * @return course -if it was deleted successfully or null if the course was not found
     */
    @Override
    public Course delete(Long id)
    {
        Course course=findOne(id);
        if (course!= null)
        {
            repositoryList.remove(course);
            return course;
        }
        return null;
    }

    /**
     * updates a course
     * @param newCourse-must be not null
     * @return null -if the course was updated successfully or newCourse -if the course was not found
     */
    @Override
    public Course update (Course newCourse)
    {
        for(Course course:repositoryList)
        {
            if (course.getCourseID().equals(newCourse.getCourseID()))
            {
                repositoryList.remove(course);
                repositoryList.add(newCourse);
                return null;
            }
        }
        return newCourse;
    }



}
