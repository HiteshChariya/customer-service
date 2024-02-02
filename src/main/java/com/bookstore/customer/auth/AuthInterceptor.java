package com.bookstore.customer.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bookstore.customer.constant.Constants;
import com.bookstore.customer.constant.MessageConstant;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.utils.MessageSupplier;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private TokenValidation tokenValidation;
	@Autowired
	private MessageSupplier messageSupplier;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("****** end " + request.getRequestURI() + "********");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("****** start " + request.getRequestURI() + "********");
		String eqbInt = request.getHeader(Constants.BSINT);
		if (BooleanUtils.toBoolean(eqbInt)) {
			return true;
		}

		if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
			log.info("Skipping Authentication {}", request.getMethod());
			return true;
		}

		String authToken = request.getHeader("authorization");
		if (StringUtils.isBlank(authToken)) {
			String failureMessage = messageSupplier.get(MessageConstant.AuthConstants.MISSING_TOKEN);
			CommonResponse<String> commonResponse = CommonResponse.failure(failureMessage, failureMessage);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(objectMapper.writeValueAsString(commonResponse));
			return false;
		}

		ValidationResponse validationResponse = tokenValidation.handleRequest(authToken);
		if (BooleanUtils.isTrue(validationResponse.getTokenValidated())) {
			TokenData principle = validationResponse.getPrinciple();
			log.info("User Type is {} ", principle.getUserType());
		} else {
			CommonResponse<String> commonResponse = CommonResponse.failure(validationResponse.getFailureMessage(),
					validationResponse.getFailureMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(objectMapper.writeValueAsString(commonResponse));
			return false;
		}

		return true;
	}

}
