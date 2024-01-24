package com.SIMS.exception;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);


  /**
   * Handle NoHandlerFoundException.
   *
   * @param ex      exception
   * @param headers request headers
   * @param status  response status
   * @param request request
   * @return response entity
   */
  @NotNull
  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ApiError apiError = new ApiError(BAD_REQUEST);
    apiError.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(),
        ex.getRequestURL()));
    log.error(Arrays.toString(ex.getStackTrace()));
    return buildResponseEntity(apiError);
  }

  /**
   * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
   *
   * @param ex the DataIntegrityViolationException
   * @return the ApiError object
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                             WebRequest request) {
    log.error(Arrays.toString(ex.getStackTrace()));
    return buildResponseEntity(
        new ApiError(HttpStatus.CONFLICT, "Database error: " + ex.getMessage(), null));
  }

  /**
   * Handle Exception, handle generic Exception.class
   *
   * @param ex the Exception
   * @return the ApiError object
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      MethodArgumentTypeMismatchException ex,
      WebRequest request) {
    ApiError apiError = new ApiError(BAD_REQUEST);
    apiError.setMessage(
        String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
            ex.getName(), ex.getValue(),
            Objects.requireNonNull(ex.getRequiredType()).getSimpleName()));
    log.error(Arrays.toString(ex.getStackTrace()));
    return buildResponseEntity(apiError);
  }

  /**
   * Handles EntityNotFoundException. Created to encapsulate errors with more detail than
   * jakarta.persistence.EntityNotFoundException.
   *
   * @param ex the EntityNotFoundException
   * @return the ApiError object
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFound(
      EntityNotFoundException ex) {
    ApiError apiError = new ApiError(NOT_FOUND);
    apiError.setMessage(ex.getMessage());
    log.error(Arrays.toString(ex.getStackTrace()));
    return buildResponseEntity(apiError);
  }

  /**
   * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
   *
   * @param ex the DataIntegrityViolationException
   * @return the ApiError object
   */
  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<Object> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
    log.error(Arrays.toString(ex.getStackTrace()));
    return buildResponseEntity(
        new ApiError(HttpStatus.CONFLICT, ex.getMessage(), ex.getData()));
  }

  /**
   * Handles BadRequestException. Created to indicate bad client request.
   *
   * @param ex the BadRequestException
   * @return the ApiError object
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object> badRequestException(
          BadRequestException ex) {
    ApiError apiError = new ApiError(BAD_REQUEST);
    apiError.setMessage(ex.getMessage());
    log.error(Arrays.toString(ex.getStackTrace()));
    return buildResponseEntity(apiError);
  }

  /**
   * Handle ForbiddenException, inspects the cause for unauthorized user actions.
   *
   * @param ex the ForbiddenException
   * @return the ApiError object
   */
  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
    log.error(Arrays.toString(ex.getStackTrace()));
    return buildResponseEntity(
        new ApiError(FORBIDDEN, ex.getMessage(), null));
  }

  @ExceptionHandler(SignInException.class)
  public ResponseEntity<Object> handleSignInException(SignInException ex) {
    log.error(Arrays.toString(ex.getStackTrace()));
    return buildResponseEntity(
            new ApiError(UNAUTHORIZED, ex.getMessage(), null)
    );
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getCode());
  }

}
