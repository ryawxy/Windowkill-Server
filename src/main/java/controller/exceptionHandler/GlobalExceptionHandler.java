package controller.exceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;

public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidTypeIdException.class,InvalidTypeIdException.class, JsonProcessingException.class})
    public void handleJsonException(InvalidTypeIdException invalidTypeIdException){
        System.out.println(invalidTypeIdException.getMessage());
    }
    @ExceptionHandler({RuntimeException.class})
    public void handleRuntimeException(RuntimeException runtimeException){
        System.out.println("Handling Runtime Exception: "+runtimeException.getMessage());
    }
    @ExceptionHandler({NullPointerException.class})
    public void handleNullPointerException(NullPointerException exception){
        System.out.println("Handling NullPointer Exception: "+exception.getMessage());
    }
}
