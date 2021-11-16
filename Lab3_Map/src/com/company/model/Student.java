package com.company.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Student,who is also a person so the class inherits from the Person class
 * @author Bohm Evelin
 * @version 30.10.2021
 * @since 1.0
 */

public class Student extends Person {
    private Long studentID;
    private int totalCredits;
    private List<Course>enrolledCourses;


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
        this.studentID=studentID;
        this.totalCredits=totalCredits;
        this.enrolledCourses=enrolledCourses;
    }

    public Student (){};
    /**
     * Adds course to the list of enrolled courses
     * @param newCourse-must not be null
     */
    public void addCourse(Course newCourse)
    {
        //  if (!enrolledCourses.contains(newCourse))
        // {
        enrolledCourses.add(newCourse);
        int newTotalCredits=totalCredits+newCourse.getCredits();
        setTotalCredits(newTotalCredits);
        // return  true;
        // }
        // return false;

    }

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
        return totalCredits == student.totalCredits && Objects.equals(studentID, student.studentID) && Objects.equals(enrolledCourses, student.enrolledCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, totalCredits, enrolledCourses);
    }

    /**
     * gets the student ID
     * @return Long
     */
    public Long getStudentID() {
        return studentID;
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
     * sets the student ID
     * @param studentID-must be not null
     */
    public void setStudentID(Long studentID) {
        this.studentID = studentID;
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
                "studentID=" + studentID +
                ", totalCredits=" + totalCredits +
                '}';
    }
}
