package com.courseapp.backend.filters;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class HeadersFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            if (containsHeaders(httpRequest)) {
                chain.doFilter(request, response);
                addHeaders((HttpServletResponse)response);
            }
            else {
                sendError((HttpServletResponse) response);
            }
        }
    }

    private void addHeaders(HttpServletResponse response) {
        response.addHeader("Recipe-server", "Recipe-response");
    }

    private boolean containsHeaders(HttpServletRequest httpRequest) {
        String header = httpRequest.getHeader("Web-client");
        return Optional.ofNullable(header).isPresent() && header.equals("Recipe");
    }

    private void sendError(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(),
                "This request comes from unknown source");
    }
}
