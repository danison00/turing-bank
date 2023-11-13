package dan.turingbank.infra;


import dan.turingbank.model.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptions {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> exception(RuntimeException e){

        ErrorResponse error = new ErrorResponse("400", e.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> exception(UsernameNotFoundException e){

        ErrorResponse error = new ErrorResponse("400", e.getMessage());

        return ResponseEntity.badRequest().body(error);

    }
    
    
}
