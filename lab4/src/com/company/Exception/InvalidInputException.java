package com.company.Exception;

/**
 * the InvalidInputException is an exception class
 * the class is used when an invalid value was introduced
 *
 * @author Bohm Evelin
 * @version  16.11.2021
 * @since 1.0
 */
public class InvalidInputException extends  Exception{
    public String errorMessage;
    public InvalidInputException(String errorMessage)
    {
       this.errorMessage= errorMessage;
    }
    @Override
    public String getMessage(){
        return errorMessage;
    }
}
