package com.javaded78.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedResponseTestModel extends ResponseTestDataModel {

	private Integer id;
	private String createdAt;
}
