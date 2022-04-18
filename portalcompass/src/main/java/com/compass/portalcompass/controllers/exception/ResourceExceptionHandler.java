package com.compass.portalcompass.controllers.exception;

import java.net.ConnectException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compass.portalcompass.exception.BancoDeDadosExcecao;
import com.compass.portalcompass.exception.NaoEncontradoExcecao;

@RestControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(NaoEncontradoExcecao.class)
	public ResponseEntity<StandardError> resourceNoutFound(NaoEncontradoExcecao e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> handle(EntityNotFoundException e, HttpServletRequest request) {
		String error = "Entity not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(BancoDeDadosExcecao.class)
	public ResponseEntity<StandardError> database(BancoDeDadosExcecao e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	// Quando tenta cadastrar/atualizar passando dados em formato inválido.
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> httpNotReadable(HttpMessageNotReadableException e,
			HttpServletRequest request) {
		String error = "http message not readable";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getLocalizedMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	// Quando o cliente omite dados obrigatórios. Retorna uma lista de erros, para
	// cada campo inválido
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<StandardError>> handle(MethodArgumentNotValidException e, HttpServletRequest request) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<StandardError> errors = new ArrayList<>();

		fieldErrors.forEach(fe -> {
			String msg = String.format("Campo: %s. Causa: %s", fe.getField(), fe.getDefaultMessage());
			String error = "Invalid or omitted arguments";
			HttpStatus status = HttpStatus.BAD_REQUEST;
			errors.add(new StandardError(Instant.now(), status.value(), error, msg, request.getRequestURI()));
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<StandardError> handle(ConnectException e, HttpServletRequest request) {
		String error = "Connection error";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> handle(ConstraintViolationException e, HttpServletRequest request) {
		ConstraintViolation<?> cv = e.getConstraintViolations().iterator().next();
		String message = String.format("A propriedade '%s' %s", cv.getPropertyPath(), cv.getMessage());

		String error = "Constraint Violation";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<StandardError> handle(PropertyReferenceException e, HttpServletRequest request) {
		String error = "Property Reference Error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> handle(DataIntegrityViolationException e, HttpServletRequest request) {
		String msg = e.getCause().getLocalizedMessage() + ". Verif ";

		String error = "Data Integrity Violation";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getCause().getLocalizedMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<StandardError> handle(NullPointerException e, HttpServletRequest request) {
		String msg = e.getMessage();
		String error = "Null Pointer Exception";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, msg, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> handle(IllegalArgumentException e, HttpServletRequest request) {
		String msg = e.getMessage();
		String error = "Illegal Argument Exception";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, msg, request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}