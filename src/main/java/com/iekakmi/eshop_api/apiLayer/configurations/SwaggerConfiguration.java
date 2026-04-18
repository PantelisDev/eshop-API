package com.iekakmi.eshop_api.apiLayer.configurations;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfiguration
{
	@Bean
	GroupedOpenApi api()
	{
		return GroupedOpenApi.builder()
				.group("eshop-api")
				.pathsToMatch("/**")
				.build();
	}
}
