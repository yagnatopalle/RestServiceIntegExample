package au.com.interview.ing.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@LocalServerPort
	private int port;

	@Test
	public void testGetUserByIdSuccess() {

		RestAssured.when().get("/userdetails/123").then().assertThat().contentType(ContentType.JSON).and()
				.statusCode(200);
	}
}
