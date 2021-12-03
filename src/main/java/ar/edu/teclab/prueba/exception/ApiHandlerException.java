package ar.edu.teclab.prueba.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Alexis
 */
@RestControllerAdvice
public class ApiHandlerException extends ResponseEntityExceptionHandler {

    private Map<String,Object> response = new HashMap<>();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        response.clear();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField().concat(" ").concat(Objects.requireNonNull(err.getDefaultMessage())))
                .collect(Collectors.toList());
        response.put("timestamp", LocalDateTime.now());
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class, NotFoundException.class})
    public ResponseEntity<ErrorMessage> badRequestErrorResponse(Exception e, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(e,req.getRequestURI()),HttpStatus.BAD_REQUEST);
    }
}
