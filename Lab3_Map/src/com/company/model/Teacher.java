package com.company.model;

import java.util.List;

/**
 * Represent a Teacher, who is also a person so the class inherits from the Person class
 * @author Bohm Evelin
 * @version 30.10.2021
 * @since 1.0
 */
public class Teacher extends Person {
    public List<Course>courses;
    private Long teacherID;

    /**
     * Constructor
     * @param firstName-must be not null
     * @param name-must not be null
     * @param teacherID-must not be null
     * @param courses-must not be null
     */
    public Teacher (String firstName,String name,Long teacherID,List<Course>courses)
    {
        super.setFirstName(firstName);
        super.setLastName(name);
        this.teacherID=teacherID;
        this.courses=courses;
    }
    public Teacher (){};
    /**
     * gets the list of courses
     * @return List of courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * sets the list of courses
     * @param courses-must not be null
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * gets the ID of the teacher
     * @return Long
     */
    public Long getTeacherID() {
        return teacherID;
    }

    /**
     * sets the teachers ID
     * @param teacherID-must be not null
     */
    public void setTeacherID(Long teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * Converts a class object to a String
     * @return String
     */
    @Override
    public String toString() {
        return "Teacher{"+ super.toString() +
                ", teacherID=" + teacherID +
                '}';
    }
}
