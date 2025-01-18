package com.javaded78.tests;

import com.javaded78.model.request.CreatedRequestDataModel;
import com.javaded78.model.request.LoginRequestDataModel;
import com.javaded78.model.response.RegisteredResponseTestDataModel;
import com.javaded78.model.response.SingleUserResponseTestDataModel;
import com.javaded78.model.response.UpdatedResponseTestModel;
import com.javaded78.model.response.UsersResponseTestDataModel;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqrestInTests {

	private final String BASE_URL = "https://reqres.in/api";

	@Test
	@DisplayName("Check response status of existing list of users")
	@Tag("reqres_in")
	void checkListUsersStatus() {
		String endPoint = BASE_URL + "/users?page=2";

		UsersResponseTestDataModel model = given()
				.log().uri()
				.when()
				.get(endPoint)
				.then()
				.log().status()
				.log().body()
				.statusCode(HttpStatus.SC_OK)
				.extract().as(UsersResponseTestDataModel.class);

		assertThat(model).isNotNull();
		assertThat(model.getData()).hasSize(6);
	}

	@Test
	@DisplayName("Verify username and email of single user")
	@Tag("reqres_in")
	void checkSingleUser() {
		String endPoint = BASE_URL + "/users/2";

		SingleUserResponseTestDataModel userResponse = given()
				.log().uri()
				.when()
				.get(endPoint)
				.then()
				.log().status()
				.log().body()
				.extract().as(SingleUserResponseTestDataModel.class);

		assertThat(userResponse.getData().getId()).isEqualTo(2);
		assertThat(userResponse.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
		assertThat(userResponse.getData().getFirstname()).isEqualTo("Janet");
		assertThat(userResponse.getData().getLastname()).isEqualTo("Weaver");
		assertThat(userResponse.getData().getAvatar()).isEqualTo("https://reqres.in/img/faces/2-image.jpg");
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
		String job = "zion resident";
		String name = "morpheus";
		CreatedRequestDataModel updateRequest = CreatedRequestDataModel.builder()
				.name(name)
				.job(job)
				.build();

		UpdatedResponseTestModel updatedResponse = given()
				.log().uri()
				.contentType(ContentType.JSON)
				.body(updateRequest)
				.when()
				.put(endPoint)
				.then()
				.log().status()
				.log().body()
				.statusCode(HttpStatus.SC_OK)
				.extract().as(UpdatedResponseTestModel.class);

		assertThat(updatedResponse.getName()).isEqualTo(name);
		assertThat(updatedResponse.getJob()).isEqualTo(job);
		assertThat(updatedResponse.getUpdatedAt()).contains(dateTimeNow);
	}

	@Test
	@DisplayName("Verify user registration")
	@Tag("reqres_in")
	void checkSuccessfulRegisterTest() {

		String endPoint = BASE_URL + "/register";
		String email = "eve.holt@reqres.in";
		String password = "pistol";
		LoginRequestDataModel requestData = LoginRequestDataModel.builder()
				.email(email)
				.password(password)
				.build();

		RegisteredResponseTestDataModel responseData = given()
				.log().uri()
				.contentType(ContentType.JSON)
				.body(requestData)
				.when()
				.post(endPoint)
				.then()
				.log().status()
				.log().body()
				.statusCode(HttpStatus.SC_OK)
				.extract().as(RegisteredResponseTestDataModel.class);

		assertThat(responseData.getId()).isNotNull();
		assertThat(responseData.getId()).isGreaterThan(0);
	}

	private String getCurrentDate() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd")
				.format(LocalDateTime.now());
	}
}
