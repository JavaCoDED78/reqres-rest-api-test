package com.javaded78.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class RegisterSpecs {

	public static RequestSpecification registerRequestSpec = BaseSpecs
			.baseRequestSpec
			.contentType(JSON);

	public static ResponseSpecification registerResponseSpec = new ResponseSpecBuilder()
			.addResponseSpecification(BaseSpecs.baseResponseSpec)
			.expectStatusCode(HttpStatus.SC_OK)
			.expectBody("id", notNullValue())
			.expectBody("token", notNullValue())
			.build();
}
