package com.javaded78.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseTestDataModel {

	private Integer page;

	@JsonProperty("per_page")
	private Integer perPage;
	private Integer total;

	@JsonProperty("total_pages")
	private Integer totalPages;
	private List<UserDataTestModel> data;
	private SupportTestDataModel support;
}
