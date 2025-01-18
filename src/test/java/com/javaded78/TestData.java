package com.javaded78;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TestData(
	Integer id,
	String email,
	String password,

	@JsonProperty("name")
	String username,

	@JsonProperty("first_name")
	String firstName,

	@JsonProperty("last_name")
	String lastName,
	String job,
	String updatedAt,
	String avatar
) {
}
