package com.example.test.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        logger.info("Request URI: method={}, URI={}, params={}, headers={}",
                request.getRequestURI(),
                request.getRequestURI(),
                request.getQueryString(),
                getHeadersAsString(request));

        filterChain.doFilter(request, response);

        logger.info("Outgoing Response: {} {}",
                response.getStatus(),
                response.getHeaderNames());
    }

    private String getHeadersAsString(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                headers.append(headerName).append("=").append(request.getHeader(headerName)).append(", ")
        );
        return headers.toString();
    }
}
