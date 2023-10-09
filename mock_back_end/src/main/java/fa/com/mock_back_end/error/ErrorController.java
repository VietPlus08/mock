package fa.com.mock_back_end.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> notFoundHandle(UsernameNotFoundException exception) {
	Map<String, String> errors = new HashMap<>();
	errors.put("loginError", exception.getMessage());
	return errors;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//	Map<String, String> errors = new HashMap<>();
//
//	ex.getBindingResult().getAllErrors().forEach(error -> {
//	    String fieldName = ((FieldError) error).getField();
//	    String errorMessage = error.getDefaultMessage();
//	    errors.put(fieldName, errorMessage);
//	});
//	return errors;
//    }
}
