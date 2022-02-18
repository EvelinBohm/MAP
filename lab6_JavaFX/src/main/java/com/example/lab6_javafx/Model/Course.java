package com.example.lab6_javafx.Model;

import javafx.beans.property.*;

import java.util.List;
import java.util.Objects;

/**
 * Represents a course
 * @author Bohm Evelin
 * @version  14.12.2021
 * @since 1.0
 */
public class Course {

    private String name;
    private Person teacher;
    private int maxEnrollment;
    private  Long courseID;
    private List<Student> studentsEnrolled;
    private int credits;


    /**
     * Constructor (initialization of data)
     * @param courseID-must not be null
     * @param name-must not be null
     * @param teacher-must not be null
     * @param maxEnrollment-must not be null
     * @param studentsEnrolled-must not be null
     * @param credits-must not be null
     */
    public Course (Long courseID,String name,Person teacher,int maxEnrollment,List<Student>studentsEnrolled,int credits)

    {
        this.courseID=courseID;
        this.name=name;
        this.teacher=teacher;
        this.maxEnrollment=maxEnrollment;
        this.studentsEnrolled=studentsEnrolled;
        this.credits=credits;
    }
    public Course (){}

    /**
     * gets the number of enrolled students
     * @return int (number of enrolled students)
     */
    public int getNrOfEnrolledStudents()
    {
        return studentsEnrolled.size();
    }

    /**
     * gets the student name
     * @return  String (the student name)
     */
    public String getName() {
       return name;
    }

    /**
     * gets the teacher
     * @return Person(the teacher)
     */

    public Person getTeacher() {
        return teacher;
    }

    /**
     * gets the list of enrolled students
     * @return List of students
     */

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    /**
     * gets the maximum number for enrollments
     * @return int
     */
    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    /**
     * gets the course ID
     * @return Long (the ID)
     */
    public Long getCourseID() {
        return courseID;
    }

    /**
     * gets the credit number for the course
     * @return int
     */
    public int getCredits() {
        return credits;
    }

    /**
     * sets the name of the student
     * @param name-must be not null
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets(modifies) the teacher
     * @param teacher-must not be null
     */
    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    /**
     * sets the maximum number for enrollments
     * @param maxEnrollment-must be not null
     */
    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    /**
     * sets the credit number of the course
     * @param credits-must be not null
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * sets the list of enrolled students
     * @param studentsEnrolled-must not be null
     */
    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    /**
     * Converts the Course object to a String
     * @return String object
     */
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", maxEnrollment=" + maxEnrollment +
                ", courseID=" + courseID +
                ", credits=" + credits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseID, course.courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID);
    }

    public StringProperty nameProperty() {

       return new SimpleStringProperty((String) name);
    }
    public LongProperty courseIdProperty() {

        return new SimpleLongProperty((Long) courseID);
    }
    public IntegerProperty creditsProperty() {

        return new SimpleIntegerProperty((int) credits);
    }
    public boolean compareTo(Course course) {
        return this.getCourseID().equals(course.getCourseID());
    }

}

