package com.bookstore.customer.config;

import com.bookstore.customer.utils.LocalDateDeserializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

@Configuration
public class ObjectMapperConfig {

	@Bean
	@Primary
	public ObjectMapper serializingObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}
}
