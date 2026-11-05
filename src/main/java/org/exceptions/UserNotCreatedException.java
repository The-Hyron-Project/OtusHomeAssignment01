package org.exceptions;

public class UserNotCreatedException extends RuntimeException{

    public UserNotCreatedException(String exceptionData){
        super(String.format("User was not registered: %s", exceptionData));
    }
}
