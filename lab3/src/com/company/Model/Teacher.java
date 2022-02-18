package com.company.Model;

import java.util.List;
import java.util.Objects;

/**
 * Represent a Teacher, who is also a person so the class inherits from the class Person
 */
public class Teacher extends Person {
    private List<Course>courses;

    /**
     * Constructor
     * @param firstName-must be not null
     * @param lastName-must not be null
     * @param teacherID-must not be null
     * @param courses-must not be null
     */
   public Teacher (String firstName,String lastName,Long teacherID,List<Course>courses)
    {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setID(teacherID);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(super.getID(), teacher.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(courses);
    }

    /**
     * Converts a class object to a String
     * @return String
     */
    @Override
    public String toString() {
        return "Teacher{"+ super.toString() +
                '}';
    }
}
