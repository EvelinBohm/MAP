package com.company.repository;

import com.company.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseRepository implements the ICrudRepository interface for courses
 * @author Bohm Evelin
 * @version  %I%, %G%
 * @since 1.0
 */

public class CourseRepository implements ICrudRepository<Course> {

    private final List<Course> courseList=new ArrayList<>();

    /**
     * Overrides the findOne methode from the interface
     * searches for the course with the given id
     * @param courseID-must not be null
     * @return Course-if it's found or null -if the course does not exist
     */
    @Override
    public Course findOne(Long courseID)
    {

        for(Course course:courseList){
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
        return courseList;
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
        for (Course course:courseList)
        {
            if (findOne(newCourse.getCourseID())!=null)
            {
                return newCourse;
            }
        }
        courseList.add(newCourse);
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
            courseList.remove(course);
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
        for(Course course:courseList)
        {
            if (course.getCourseID().equals(newCourse.getCourseID()))
            {
                courseList.remove(course);
                courseList.add(newCourse);
                return null;
            }
        }
        return newCourse;
    }



}
