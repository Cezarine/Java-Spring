package med.voll.api.domain.ValidationError;

import org.springframework.validation.FieldError;

public record ValidationErrorDataDTO(String field, String message) {
    public ValidationErrorDataDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
