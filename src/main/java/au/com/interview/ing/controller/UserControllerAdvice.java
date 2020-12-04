package au.com.interview.ing.controller;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import au.com.interview.ing.util.ApplicationUtil;

@RestControllerAdvice
public class UserControllerAdvice {

	public static final Logger log = LoggerFactory.getLogger(UserControllerAdvice.class);

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<String> handle404Errors(ResponseStatusException rs) {

		return new ResponseEntity<>(rs.getReason(), rs.getStatus());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handle400Errors(ConstraintViolationException cve) {

		String errStr = cve.getMessage();

		if (errStr.contains(".empId:")) {

			errStr = ApplicationUtil.PATTERN_ERROR;
		} else {

			errStr = errStr.substring(errStr.indexOf(":"));
		}

		return new ResponseEntity<>(errStr, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle500Errors(Exception e) {

		log.error(e.getMessage());
		return new ResponseEntity<>("Something went wrong, see logs", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
