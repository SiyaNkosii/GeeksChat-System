package usermanagement.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundExeption(RuntimeException ex){
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler({ResourceAlreadyExistException.class})
    public  ResponseEntity<?> handleResouceAlreadyExistException(RuntimeException ex){
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.CONFLICT.value());
        apiError.setStatus(HttpStatus.CONFLICT);
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?>handleIllegalArgumentException(RuntimeException ex){
        ApiError apiError = new ApiError();
        apiError.setCode(HttpStatus.CONFLICT.value());
        apiError.setStatus(HttpStatus.CONFLICT);
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
}
