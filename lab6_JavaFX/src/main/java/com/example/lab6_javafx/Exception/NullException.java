package com.example.lab6_javafx.Exception;
/**
 * the NullException is an exception class
 * the class is used when a null exception occurs
 *
 * @author Bohm Evelin
 * @version  14.12.2021
 * @since 1.0
 */
public class NullException extends Throwable {
    public NullException(String s) {
        System.out.println(s);
    }
}
