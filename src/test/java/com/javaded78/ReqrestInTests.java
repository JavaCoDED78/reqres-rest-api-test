package com.javaded78;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqrestInTests {

	private final String BASE_URL = "https://reqres.in/api";

	@Test
	@DisplayName("Check response status of existing list of users")
	@Tag("reqres_in")
	void checkListUsersStatus() {
		String endPoint = BASE_URL + "/users?page=2";
		given()
				.log().uri()
				.when()
				.get(endPoint)
				.then()
				.log().status()
				.log().body()
				.statusCode(HttpStatus.SC_OK);
	}

	@Test
	@DisplayName("Verify username and email of single user")
	@Tag("reqres_in")
	void checkSingleUser() {
		String endPoint = BASE_URL + "/users/2";
		TestData testData = TestData.builder()
				.id(2)
				.email("janet.weaver@reqres.in")
				.firstName("Janet")
				.lastName("Weaver")
				.build();

		given()
				.log().uri()
				.when()
				.get(endPoint)
				.then()
				.log().status()
				.log().body()
				.body("data.id", equalTo(testData.id()),
						"data.email", equalTo(testData.email()),
						"data.first_name", equalTo(testData.firstName()),
						"data.last_name", equalTo(testData.lastName())
				);
	}

	@Test
	@DisplayName("Check response status of non existing resource")
	@Tag("reqres_in")
	void checkResourceNotFoundStatus() {
		String endPoint = BASE_URL + "/unknown/23";
		given()
				.log().uri()
				.when()
				.get(endPoint)
				.then()
				.log().status()
				.statusCode(HttpStatus.SC_NOT_FOUND);
	}

	@Test
	@DisplayName("Verify username, job and date of user after update")
	@Tag("reqres_in")
	public void updateUser() {
		String endPoint = BASE_URL + "/users/2";
		String dateTimeNow = getCurrentDate();
		TestData updateData = TestData.builder()
				.username("morpheus")
				.job("zion resident")
				.updatedAt(dateTimeNow)
				.build();

		given()
				.log().uri()
				.contentType(ContentType.JSON)
				.body(updateData)
				.when()
				.put(endPoint)
				.then()
				.log().status()
				.log().body()
				.statusCode(HttpStatus.SC_OK)
				.body("name", is(updateData.username()),
						"job", is(updateData.job()),
						"updatedAt", containsString(dateTimeNow));
	}

	@Test
	@DisplayName("Verify user registration")
	@Tag("reqres_in")
	void checkSuccessfulRegisterTest() {
		String endPoint = BASE_URL + "/register";
		TestData testData = TestData.builder()
				.email("eve.holt@reqres.in")
				.password("pistol")
				.build();

		given()
				.log().uri()
				.contentType(ContentType.JSON)
				.body(testData)
				.when()
				.post(endPoint)
				.then()
				.log().status()
				.log().body()
				.statusCode(HttpStatus.SC_OK)
				.body("token", notNullValue(),
						"id", notNullValue());
	}

	private String getCurrentDate() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd")
				.format(LocalDateTime.now());
	}
}
