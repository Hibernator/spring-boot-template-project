package com.example.demo.application.filter;

import com.example.demo.infrastructure.shared.AppConstants;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestIdMDCFilter extends OncePerRequestFilter {

  /** gen a new request id and add into MDC for tracing easier */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String requestId = UUID.randomUUID().toString();
      MDC.put(AppConstants.REQUEST_ID_KEY, requestId);
      response.setHeader(AppConstants.REQUEST_ID_KEY, requestId);
      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(AppConstants.REQUEST_ID_KEY);
    }
  }
}
