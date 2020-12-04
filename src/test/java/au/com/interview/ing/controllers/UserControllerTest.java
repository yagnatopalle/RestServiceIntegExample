package au.com.interview.ing.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import au.com.interview.ing.model.ResponseData;
import au.com.interview.ing.util.ApplicationUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

	@LocalServerPort
	private int port;

	@Test
	void testGetUserByIdSuccessFromExample() {

		Response response = RestAssured.given().port(port).when().get("/userdetails/1232854");

		// validating status code
		Assertions.assertEquals(200, response.getStatusCode());

		/*
		 * validating mock data for empid '1234556'
		 *
		 * Address details Street = '12345 holling rd' City = 'SYDNEY' State = 'NSW'
		 * postcode = 2000
		 *
		 * User Details empId = 1232854 title = 'MR' firstname = 'test' lastname =
		 * 'tsetlast' gender = 'MALE'
		 *
		 */

		ResponseData responseObj = response.getBody().as(ResponseData.class);

		// check if object is populated
		Assertions.assertNotNull(responseObj);

		// validate user details
		Assertions.assertTrue(1232854 == responseObj.getEmpId());
		Assertions.assertTrue("MR".equalsIgnoreCase(responseObj.getTitle()));
		Assertions.assertTrue("test".equalsIgnoreCase(responseObj.getFirstname()));
		Assertions.assertTrue("tsetlast".equalsIgnoreCase(responseObj.getLastname()));
		Assertions.assertTrue("MALE".equalsIgnoreCase(responseObj.getGender()));

		// validate adddress details
		Assertions.assertTrue(2000 == responseObj.getAddress().getPostcode());
		Assertions.assertTrue("12345 holling rd".equalsIgnoreCase(responseObj.getAddress().getStreet()));
		Assertions.assertTrue("SYDNEY".equalsIgnoreCase(responseObj.getAddress().getCity()));
		Assertions.assertTrue("NSW".equalsIgnoreCase(responseObj.getAddress().getState()));
	}

	@Test
	void testGetUserByIdSuccessMockData() {

		Response response = RestAssured.given().port(port).when().get("/userdetails/1234556");

		// validating status code
		Assertions.assertEquals(200, response.getStatusCode());

		/*
		 * validating mock data for empid '1234556'
		 *
		 * Address details Street = '275 KENT ST' City = 'SYDNEY' State = 'NSW' postcode
		 * = 2000
		 *
		 * User Details empId = 1234556 title = 'MR' firstname = 'FN1' lastname = 'LN1'
		 * gender = 'MALE'
		 *
		 */

		ResponseData responseObj = response.getBody().as(ResponseData.class);

		// check if object is populated
		Assertions.assertNotNull(responseObj);

		// validate user details
		Assertions.assertTrue(1234556 == responseObj.getEmpId());
		Assertions.assertTrue("MR".equalsIgnoreCase(responseObj.getTitle()));
		Assertions.assertTrue("FN1".equalsIgnoreCase(responseObj.getFirstname()));
		Assertions.assertTrue("LN1".equalsIgnoreCase(responseObj.getLastname()));
		Assertions.assertTrue("MALE".equalsIgnoreCase(responseObj.getGender()));

		// validate adddress details
		Assertions.assertTrue(2000 == responseObj.getAddress().getPostcode());
		Assertions.assertTrue("275 KENT ST".equalsIgnoreCase(responseObj.getAddress().getStreet()));
		Assertions.assertTrue("SYDNEY".equalsIgnoreCase(responseObj.getAddress().getCity()));
		Assertions.assertTrue("NSW".equalsIgnoreCase(responseObj.getAddress().getState()));
	}

	@Test
	void testGetUserUnavailableId() {

		// random id
		Response response = RestAssured.given().port(port).when().get("/userdetails/123");

		// validating status code
		Assertions.assertEquals(404, response.getStatusCode());
		Assertions.assertTrue("Given Employee Id: 123 not found".equalsIgnoreCase(response.getBody().asString()));
	}

	@Test
	void testGetUserInvalidId() {

		// invalid id
		Response response = RestAssured.given().port(port).when().get("/userdetails/abc");

		// validating status code
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertTrue(ApplicationUtil.PATTERN_ERROR.equalsIgnoreCase(response.getBody().asString()));
	}
}
