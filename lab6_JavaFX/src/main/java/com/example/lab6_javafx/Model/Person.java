package com.example.lab6_javafx.Model;

/**
 * Represents a Person
 * @author Bohm Evelin
 * @version 14.12.2021
 * @since 1.0
 */

public class Person {


    private String firstName;
    private String lastName;
    private Long ID;

    /**
     * Constructor (initialization of data)
     * @param firstName-must not be null
     * @param lastName-must not be null
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * default constructor
     */
    public Person(){}

    /**
     * gets the first name of a person
     * @return String (first name)
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * gets the last name of a person
     * @return String (last name)
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets first name
     * @param firstName-not null
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * sets the last name
     * @param lastName-must be not null
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * gets the ID
     * @return Long
     */
    public Long getID() {
        return ID;
    }
    /**
     * sets the ID
     * @param ID-must be not null
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * Converts the object to a string
     * @return String with the class attributes
     *
     */

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ID=" + ID +
                '}';
    }
}
