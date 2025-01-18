package com.javaded78.model.request;

import lombok.Builder;

@Builder
public record LoginRequestDataModel(

	String email,
	String password
) {
}
