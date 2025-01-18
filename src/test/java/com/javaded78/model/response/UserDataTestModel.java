package com.javaded78.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataTestModel {

	private Integer id;
	private String email;

	@JsonProperty("first_name")
	private String firstname;

	@JsonProperty("last_name")
	private String lastname;
	private String avatar;
}
