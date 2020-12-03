package au.com.interview.ing.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	private static String BASE_PATH = "http://localhost:";

	@LocalServerPort
	private int port;

	@Test
	public void testGetUserByIdSuccess() {

		RestAssured.when().get(BASE_PATH + port + "/userdetails/123").then().assertThat().statusCode(200);
	}
}
