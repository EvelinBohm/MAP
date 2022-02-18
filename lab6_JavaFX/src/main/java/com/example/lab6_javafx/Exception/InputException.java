package com.example.lab6_javafx.Exception;
/**
 * the InputException is an exception class
 * the class is used when a wrong input was introduced
 *
 * @author Bohm Evelin
 * @version  14.12.2021
 * @since 1.0
 */
public class InputException extends Throwable {
    public InputException(String s) {
        System.out.println(s);
    }
}

