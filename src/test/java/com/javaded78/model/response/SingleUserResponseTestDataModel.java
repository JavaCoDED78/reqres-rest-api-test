package com.javaded78.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SingleUserResponseTestDataModel {

	private UserDataTestModel data;
	private SupportTestDataModel support;
}
