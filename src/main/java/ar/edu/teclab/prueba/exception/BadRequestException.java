package ar.edu.teclab.prueba.exception;

/**
 * @author Alexis
 */
public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
