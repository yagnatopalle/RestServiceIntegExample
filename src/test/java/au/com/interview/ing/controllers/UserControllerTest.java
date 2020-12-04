package au.com.interview.ing.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import au.com.interview.ing.model.Address;
import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.ResponseData;
import au.com.interview.ing.util.ApplicationUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

	@LocalServerPort
	private int port;

	private RequestData createRequestData(String ln, String title, String street, Integer postCd) {

		RequestData req = new RequestData();
		Address address = new Address();

		req.setFirstname("Updated First Name");
		req.setLastname(Optional.ofNullable(ln).orElse("Updated Last Name"));
		req.setGender("Updated gender");
		req.setTitle(Optional.ofNullable(title).orElse("Updated Title"));

		address.setCity("Updated City");
		address.setPostcode(Optional.ofNullable(postCd).orElse(2000));
		address.setState("Updated State");
		address.setStreet(Optional.ofNullable(street).orElse("Updated Street"));

		req.setAddress(address);

		return req;
	}

	@Test
	void testGetUserByIdFromExample() {

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
	void testGetUserByIdMockData() {

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
	void testUpdateUserByIdMockData() {


		/*
		 * This test case will update the empId '588636421' with mock data.
		 *
		 * STEP - 1 calling GET and asserting to show that the data available and
		 * asserted
		 */

		Response response = RestAssured.given().port(port).when().get("/userdetails/588636421");

		ResponseData responseObj = response.getBody().as(ResponseData.class);

		Assertions.assertTrue(588636421 == responseObj.getEmpId());
		Assertions.assertTrue("MR".equalsIgnoreCase(responseObj.getTitle()));
		Assertions.assertTrue("FN2".equalsIgnoreCase(responseObj.getFirstname()));
		Assertions.assertTrue("LN2".equalsIgnoreCase(responseObj.getLastname()));
		Assertions.assertTrue("MALE".equalsIgnoreCase(responseObj.getGender()));
		Assertions.assertTrue(2000 == responseObj.getAddress().getPostcode());
		Assertions.assertTrue("9280 KENT ST".equalsIgnoreCase(responseObj.getAddress().getStreet()));
		Assertions.assertTrue("SYDNEY".equalsIgnoreCase(responseObj.getAddress().getCity()));
		Assertions.assertTrue("NSW".equalsIgnoreCase(responseObj.getAddress().getState()));

		/**
		 * STEP - 2 calling private method to create request body with some mock data
		 * along with some user provided data (Last name, title, street and post code).
		 */
		response = RestAssured
				.given()
				.port(port)
				.contentType(ContentType.JSON)
				.body(createRequestData("Another Last name", "MRS", "20 Park St", 3000))
				.when()
				.put("/userdetails/588636421");

		// validating status code, will be 204 for PUT
		Assertions.assertEquals(204, response.getStatusCode());

		/**
		 * STEP - 3 Calling GET again to assert the updated record for same empId.
		 *
		 * The function which creates the request will update the object with below data
		 *
		 * Address details City = 'Updated City' State = 'Updated State'
		 *
		 * User Details firstname = 'Updated First Name' gender = 'Updated gender'
		 */

		response = RestAssured.given().port(port).when().get("/userdetails/588636421");

		responseObj = response.getBody().as(ResponseData.class);

		Assertions.assertTrue(588636421 == responseObj.getEmpId());
		Assertions.assertTrue("MRS".equalsIgnoreCase(responseObj.getTitle()));
		Assertions.assertTrue("Updated First Name".equalsIgnoreCase(responseObj.getFirstname()));
		Assertions.assertTrue("Another Last name".equalsIgnoreCase(responseObj.getLastname()));
		Assertions.assertTrue("Updated gender".equalsIgnoreCase(responseObj.getGender()));
		Assertions.assertTrue(3000 == responseObj.getAddress().getPostcode());
		Assertions.assertTrue("20 Park St".equalsIgnoreCase(responseObj.getAddress().getStreet()));
		Assertions.assertTrue("Updated City".equalsIgnoreCase(responseObj.getAddress().getCity()));
		Assertions.assertTrue("Updated State".equalsIgnoreCase(responseObj.getAddress().getState()));
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

	@Test
	void testUpdateNoContentType() {

		// random id
		Response response = RestAssured.given().port(port).when().put("/userdetails/123");

		// validating status code
		Assertions.assertEquals(415, response.getStatusCode());
	}

	@Test
	void testUpdateIncorrectContentType() {

		// random id
		Response response = RestAssured.given().port(port).contentType(ContentType.TEXT).when().put("/userdetails/123");

		// validating status code
		Assertions.assertEquals(415, response.getStatusCode());
	}

	@Test
	void testUpdateUserUnavailableId() {

		// random id
		Response response = RestAssured.given().port(port).contentType(ContentType.JSON)
				.body(createRequestData(null, null, null, null)).when().put("/userdetails/123");

		// validating status code
		Assertions.assertEquals(404, response.getStatusCode());
		Assertions.assertTrue("Given Employee Id: 123 not found".equalsIgnoreCase(response.getBody().asString()));
	}

	@Test
	void testUpdateUserInvalidId() {

		// invalid id
		Response response = RestAssured.given().port(port).contentType(ContentType.JSON)
				.body(createRequestData(null, null, null, null)).when().put("/userdetails/abc");

		// validating status code
		Assertions.assertEquals(400, response.getStatusCode());
		Assertions.assertTrue(ApplicationUtil.PATTERN_ERROR.equalsIgnoreCase(response.getBody().asString()));
	}

	@Test
	void testUpdateUserLongPostCode() {

		// invalid id
		Response response = RestAssured.given().port(port).contentType(ContentType.JSON)
				.body(createRequestData(null, null, null, 12345)).when().put("/userdetails/123");

		// validating status code
		Assertions.assertEquals(400, response.getStatusCode());

		Arrays.asList(response.body().as(String[].class))
		.forEach(a -> assertEquals("Postcode must be a 4 digit number.", a));

	}

	@Test
	void testUpdateUserLongTitle() {

		// invalid id
		Response response = RestAssured.given().port(port).contentType(ContentType.JSON)
				.body(createRequestData(null, "Title with more than 20 chars", null, null)).when()
				.put("/userdetails/123");

		// validating status code
		Assertions.assertEquals(400, response.getStatusCode());
		Arrays.asList(response.body().as(String[].class))
				.forEach(a -> assertEquals("Title can be of max 20 characters.", a));
	}
}
