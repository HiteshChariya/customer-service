package com.bookstore.customer.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class RequestResponseLoggingFilter extends CommonsRequestLoggingFilter {

    private boolean includeResponsePayload;

    private boolean includeAfterCompletion;

    private boolean includeBeforeCompletion;

    public void setIncludeResponsePayload(boolean includeResponsePayload) {
        this.includeResponsePayload = includeResponsePayload;
    }

    public void setIncludeAfterCompletion(boolean includeAfterCompletion) {
        this.includeAfterCompletion = includeAfterCompletion;
    }

    public void setIncludeBeforeCompletion(boolean includeBeforeCompletion) {
        this.includeBeforeCompletion = includeBeforeCompletion;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponse responseToUse = response;
        final long startTime = System.currentTimeMillis();
        if (includeResponsePayload) {
            responseToUse = new ContentCachingResponseWrapper(response);
        }
        try {
            super.doFilterInternal(request, responseToUse, filterChain);
        } finally {
            if (includeResponsePayload) {
                final ContentCachingResponseWrapper contentCachedResponse = (ContentCachingResponseWrapper) responseToUse;
                logger.info("Request is "+request.getRequestURI()+" takes Time: " + (System.currentTimeMillis() - startTime) + "ms, Response: " + new String(contentCachedResponse.getContentAsByteArray()));
                      contentCachedResponse.copyBodyToResponse();
            }
        }
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        if (includeAfterCompletion) {
            super.afterRequest(request, message);
        }
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        if (includeBeforeCompletion) {
        	super.beforeRequest(request, message);
        	logger.info(" Request Received: "+message);
        }
    }
}
