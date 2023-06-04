package com.example.cinemaspringboot.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AlarmFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String alarmParameter = httpRequest.getParameter("alarm");
        if (alarmParameter != null) {
            System.setProperty("alarm_region", alarmParameter);
        }

        chain.doFilter(request, response);
    }

}
