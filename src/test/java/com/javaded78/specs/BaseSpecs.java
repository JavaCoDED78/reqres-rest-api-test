package com.javaded78.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.javaded78.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class BaseSpecs {

	public static RequestSpecification baseRequestSpec = with()
			.filter(withCustomTemplates())
			.log().uri()
			.log().headers()
			.log().body()
			.baseUri("https://reqres.in")
			.basePath("/api");

	public static ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
			.log(STATUS)
			.log(BODY)
			.build();
}
