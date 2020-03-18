
/*
Purpose: creates a new object type called InvalidInputException. Handles the process
         of creating a new InvalidInputException and listing it's message. 
Author: Cole Holladay
Date: 10/13/19
*/
public class InvalidInputException extends Exception {
    private final String message;
    
    /*
    Purpose: Used to create a InvalidInputException with a specified String.
    Precondition: Object passed in must be a String datatype.
    Postcondition: Classes' instance variable, message, is assigned to the 
                   string value passed into the method.
    */
    InvalidInputException(String message){
        this.message = message;
    }
    /*
    Purpose: Retreives the Exception created.
    Precondition: InvalidInputException object must be declared and instantiated before
                  retrieving the exception value.
    Postcondition: Returns the value String value to the method.
    */
    public String getException(){
        return message;
    }
}
