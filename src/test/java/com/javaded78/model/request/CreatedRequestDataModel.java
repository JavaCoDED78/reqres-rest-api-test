package com.javaded78.model.request;

import lombok.Builder;

@Builder
public record CreatedRequestDataModel(

		String name,
		String job
) {
}
