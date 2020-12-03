package au.com.interview.ing.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.ResponseData;

/**
 * Main controller class for the User Details API.
 *
 * @author Yagna
 *
 */
@RestController
public class UserController {

	public static final Logger log = LoggerFactory.getLogger(UserController.class);

	/**
	 * This code implements the GET method for the User Service.
	 *
	 * @param empId
	 * @return {@link UserDetails}
	 */
	@GetMapping(path = "/userdetails/{empId}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> getUserById(@PathVariable("empId") Long empId) {

		log.debug("Entering UserController.getUserById() for Employee Id {}", empId);

		log.debug("Exiting UserController.getUserById() successfully for Employee Id {}", empId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/userdetails/{empId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateUserById(@PathVariable("empId") Long empId,
			@Valid @RequestBody RequestData body) {

		log.debug("Entering UserController.getUserById() for Employee Id {}", empId);

		log.debug("Exiting UserController.getUserById() successfully for Employee Id {}", empId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
