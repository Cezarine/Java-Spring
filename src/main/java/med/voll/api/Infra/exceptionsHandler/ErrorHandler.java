package med.voll.api.Infra.exceptionsHandler;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidationError.ValidationErrorDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> HandleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorDataDTO>> HandleErro400(MethodArgumentNotValidException exception){
        List<FieldError> ex = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(ex.stream().map(ValidationErrorDataDTO::new).toList());
    }
}
