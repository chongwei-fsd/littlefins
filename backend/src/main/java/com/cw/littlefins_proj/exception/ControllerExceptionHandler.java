package com.cw.littlefins_proj.exception;

import com.cw.littlefins_proj.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import org.hibernate.annotations.NotFound;
import jakarta.validation.ConstraintViolationException;
//import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

// @ControllerAdvice surrounds the Controllers to apply some common logic (as an Exception Resolver)
// @ExceptionHandler is used across controllers to capture and respond to exceptions
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    // handle/trap errors when user signup leaves the password blank in AuthService
    @ExceptionHandler (PasswordBlankException.class)
    protected ResponseEntity<Object> handlePasswordBlankException(PasswordBlankException ex){

        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // handle/trap errors when user signin is incorrect in AuthService
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AuthenticationException ex)
    {
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    // handle/trap errors for all unspecified error(s) that are not captured by specific exception handlers below.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex) {

        UnspecifiedException unspecifiedException = new UnspecifiedException();

        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // handle/trap errors produced by @GetMapping methods (e.g. @PathVariable receives an invalid value or id)
    @ExceptionHandler (ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){

        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // handle/trap errors produced by TagController's @PostMapping("/save") where id/name provided is null or empty
    // supersede exception handler handleMethodArgumentNotValid as it save process does not require @Valid
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {

        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();

        List errors = new ArrayList();

        if (!violations.isEmpty()) {
            violations.forEach(violation -> {
                errors.add(violation.getMessage());
            });
        } else {
            errors.add("ConstraintViolationException occured.");
        }

        Collections.sort(errors);   // formats the listed errors by alphabetical order

        Map<String, List<String>> errorResponse = new HashMap<>();

        errorResponse.put("error", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    // handle/trap errors produced by key constraints (e.g. adding an email/tag name that already exists)
    // Note that new constraints are added to model tables' columns: "supplier.email" and "tag.email"
    // @Table(name="supplier", uniqueConstraints = {@UniqueConstraint(name ="email", columnNames = "email")})
    // @Table(name = "tag", uniqueConstraints = {@UniqueConstraint(name ="tag name", columnNames = "name")})
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

        Map<String, String> errorResponse = new HashMap<>();

        String cause = ex.getMostSpecificCause().getMessage();

        String constraintName = ((org.hibernate.exception.ConstraintViolationException)ex.getCause()).getConstraintName();
        constraintName = Utils.substringAtLastDelimiter(constraintName, ".");
        constraintName = Utils.capitalise(constraintName);

        if(ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException){
            // errorResponse.put("error", cause);
            if(cause.toLowerCase().contains("duplicate"))
                errorResponse.put("error", constraintName + " is already used.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        errorResponse.put("error", "Unknown Violation: " + cause);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // handles InvalidDataAccessApiUsageException, caused by an attempt to duplicate tags associated with a product
    @ExceptionHandler (InvalidDataAccessApiUsageException.class)
    public ResponseEntity<Object> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex){

        InvalidDataAccessException invalidDataAccessException = new InvalidDataAccessException(ex.getMessage());

        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", invalidDataAccessException.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);

    }

    // handles MethodArgumentTypeMismatchException, caused by passing an incorrect type to @PathVariable
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

        Map<String, String> errorResponse = new HashMap<>();

        String message = "Type mismatch, require ";
        message += Utils.substringAtLastDelimiter(ex.getRequiredType().getName(), ".") + " type.";

        MethodArgumentTypeException methodArgumentTypeException = new MethodArgumentTypeException(message);

        errorResponse.put("error", methodArgumentTypeException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // handles NoResourceFoundException - mainly invalid urls or missing @PathVariable
    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode httpStatusCode, WebRequest request) {

        NoResourceException noResourceException = new NoResourceException();

        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", noResourceException.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // handle/trap errors produced by @RequestBody annotation binding (e.g. no data sent in the request's body)
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders httpHeaders, HttpStatusCode httpStatusCode, WebRequest request){

        MessageNotReadableException messageNotReadableException = new MessageNotReadableException();

        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", messageNotReadableException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // handle/trap errors produced by @RequestParam and @RequestParam(required = true)
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode httpStatusCode, WebRequest request) {

        String message = Utils.capitalise(ex.getParameterName()) + " parameter is missing.";

        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // handle/trap errors produced by @Valid annotation bindings
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders httpHeaders, HttpStatusCode httpStatusCode, WebRequest request){

        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        Collections.sort(errors);   // formats the listed errors by alphabetical order

        Map<String, List<String>> errorResponse = new HashMap<>();

        errorResponse.put("error", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Note #1:
    // handle/trap errors produced by NoSuchElementException - caused by non-existing supplierid
    // this exception handler is NOT used. A targeted approach (try...catch) is applied for this exception
    // found within its ProductController:
    // @PutMapping(value = {"/{id}", "/{id}/supplier/{supplierid}"})

    //    @ExceptionHandler(NoSuchElementException.class)
    //    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
    //
    //        NoSuchElementExistsException noSuchElementExistsException = new NoSuchElementExistsException("test");
    //
    //        Map<String, String> errorResponse = new HashMap<>();
    //
    //        errorResponse.put("error", noSuchElementExistsException.getMessage());
    //
    //        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    //    }

}
