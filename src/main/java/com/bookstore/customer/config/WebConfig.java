package com.bookstore.customer.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bookstore.customer.auth.AuthInterceptor;

@Configuration
@EnableWebMvc
@Profile("!test")
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	AuthInterceptor authInerceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> excludePathList = new ArrayList<String>();
		excludePathList.add("/swagger-ui/**");
		excludePathList.add("/api-docs/**");
		excludePathList.add("/customer/cart/all");
		registry.addInterceptor(authInerceptor).excludePathPatterns(excludePathList);
	}

	@Bean
	public WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}
}
