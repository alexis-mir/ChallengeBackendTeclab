package ar.edu.teclab.prueba.exception;

/**
 * @author Alexis
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException (String msg){
        super(msg);
    }
}
