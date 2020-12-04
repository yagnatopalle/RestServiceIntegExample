package au.com.interview.ing.controller;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.interview.ing.dao.UserDAO;
import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.ResponseData;
import au.com.interview.ing.util.ApplicationUtil;

/**
 * Main controller class for the User Details API.
 *
 * @author Yagna
 *
 */
@RestController
@Validated
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final UserDAO userDao;

	public UserController(UserDAO userDao) {

		super();
		this.userDao = userDao;
	}

	/**
	 * GET method for the User Service.
	 *
	 * @param empId
	 * @return {@link ResponseData}
	 */
	@GetMapping(path = "/userdetails/{empId}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> getUserById(
			@PathVariable("empId") @Pattern(regexp = ApplicationUtil.PATTERN, message = ApplicationUtil.PATTERN_ERROR) String empId) {

		log.debug("UserController.getUserById() for Employee Id {}", empId);

		return ResponseEntity.ok(userDao.findUserByEmpId(Long.parseLong(empId)));
	}

	/**
	 * POST method for User service. Will update both User and Address details.
	 *
	 * @param empId
	 * @param {@link RequestData}
	 * @return
	 */
	@PutMapping(path = "/userdetails/{empId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateUserById(
			@PathVariable("empId") @Pattern(regexp = ApplicationUtil.PATTERN, message = ApplicationUtil.PATTERN_ERROR) String empId,
			@Valid @RequestBody RequestData body) {

		log.debug("UserController.updateUserById() for Employee Id {}", empId);

		userDao.updateUserByEmpid(Long.parseLong(empId), body);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
