package com.bookstore.customer.config;

import com.bookstore.customer.constant.MessageConstant;
import com.bookstore.customer.utils.RequestResponseLoggingFilter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.time.format.DateTimeFormatter;

@Configuration
public class CommonServiceConfig {
	
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(MessageConstant.DATE_FORMATE);

    @Bean(name="messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages","messages_common");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        final RequestResponseLoggingFilter filter = new RequestResponseLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeAfterCompletion(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeClientInfo(true);
        filter.setIncludeResponsePayload(true);
        return filter;
    }
}
