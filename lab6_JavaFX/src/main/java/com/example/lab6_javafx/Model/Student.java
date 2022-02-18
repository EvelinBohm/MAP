package com.example.lab6_javafx.Model;

import javafx.beans.property.*;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Student,who is also a person so the class inherits from the Person class
 * @author Bohm Evelin
 * @version  14.12.2021
 * @since 1.0
 */

public class Student extends Person {
    private int totalCredits;
    private List<Course> enrolledCourses;


    /**
     * Constructor
     * @param firstname-must not be null
     * @param name-must not be null
     * @param studentID-must not be null
     * @param totalCredits-must not be null
     * @param enrolledCourses-must not be null
     */
    public Student (String firstname,String name ,Long studentID,int totalCredits,List<Course>enrolledCourses)
    {
        super.setLastName(name);
        super.setFirstName(firstname);
        super.setID(studentID);
        this.totalCredits=totalCredits;
        this.enrolledCourses=enrolledCourses;
    }

    public Student (){}

    /**
     * Compares class objects
     * @param  o-must not be null
     * @return true- if equal or false -if not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getID(), student.getID());
    }


    @Override
    public int hashCode() {
        return Objects.hash(totalCredits, enrolledCourses);
    }

    /**
     * gets the number of total credits
     * @return int
     */
    public int getTotalCredits() {
        return totalCredits;
    }

    /**
     * gets the list of enrolled courses
     * @return List of courses
     */
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    /**
     * sets the list of enrolled courses
     * @param enrolledCourses-must be not null
     */
    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    /**
     * sets the total number of credits
     * @param totalCredits-must be not null
     */
    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    /**
     * Converts a class object to a String
     * @return String
     */
    @Override
    public String toString() {
        return "Student{" +super.toString()+
                "totalCredits=" + totalCredits +
                '}';
    }
    public boolean compareTo(Student student) {
        return this.getID().equals(student.getID());
    }

    public StringProperty lastNameProperty() {

        return new SimpleStringProperty((String) super.getLastName());
    }
    public StringProperty firstNameProperty() {

        return new SimpleStringProperty((String) super.getFirstName());
    }
    public LongProperty studIdProperty() {

        return new SimpleLongProperty((Long) super.getID());
    }
    public IntegerProperty creditsProperty() {

        return new SimpleIntegerProperty((int) totalCredits);
    }
}
