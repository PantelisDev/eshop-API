package com.iekakmi.eshop_api.apiLayer.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer
{
	@Override
	public void addCorsMappings(CorsRegistry reg)
	{
		reg.addMapping("/**").allowedMethods("*");
	}
	
	
}
