package com.bookstore.customer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class MessageSupplier {

    private final MessageSource messageSource;

    @Autowired
    public MessageSupplier(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String messageKey, Object... args) {
        return messageSource.getMessage(messageKey, args, Locale.US);
    }

    public List<String> mapErrors(Errors errors) {
        return errors.getAllErrors()
                .stream()
                .map(err -> get(err.getCode()))
                .collect(Collectors.toList());
    }

}
