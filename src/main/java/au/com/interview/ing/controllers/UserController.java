package au.com.interview.ing.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.interview.ing.api.UserdetailsApi;
import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.UserDetails;



/**
 * Main controller class for the User Details API. The interface is generated
 * automatically by codegen plugin from swagger.yaml during compilation.
 * 
 * The pluging is being used to remove the boilerplate code like models and also
 * helps to ensure that the codebase is actually aligned with swagger spec. The
 * models and controller stubs are available in target/swagger/ path as soon as
 * the code is compiled. POM.xml takes care of generating required files and
 * adding them to buildpath.
 * 
 * @author Yagna
 *
 */
@RestController
public class UserController  implements UserdetailsApi  {

	public static final Logger log = LoggerFactory.getLogger(UserController.class);


	/**
	 * This code implements the GET method for the User Service. The Request mapping
	 * is done in interface with all required details (resource name, content-type
	 * etc).
	 * 
	 * @param empId
	 * @return {@link UserDetails}
	 */

	@Override public ResponseEntity<UserDetails> getUserById(@PathVariable("empId") Long empId) {

		log.debug("Entering UserController.getUserById() for Employee Id {}", empId);

		log.
		debug("Exiting UserController.getUserById() successfully for Employee Id {}",
				empId);
		return new ResponseEntity<>(HttpStatus.OK); }




	@Override public ResponseEntity<Void> updateUserById(@PathVariable("empId") Long empId, @Valid @RequestBody RequestData body) {

		log.debug("Entering UserController.getUserById() for Employee Id {}", empId);

		log.debug("Exiting UserController.getUserById() successfully for Employee Id {}",
				empId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); }

}
