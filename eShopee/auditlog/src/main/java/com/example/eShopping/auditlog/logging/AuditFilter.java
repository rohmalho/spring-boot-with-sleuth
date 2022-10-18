package com.example.eShopping.auditlog.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Order
@Slf4j
public class AuditFilter extends OncePerRequestFilter implements ApplicationEventPublisherAware {
    @Autowired
    Tracer tracer;

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.error(tracer.currentSpan().context().parentId());
        Span span;
        String parentId = tracer.currentSpan().context().parentId();
        if(parentId == null) {
            span = tracer.nextSpan();
        }else{
            span = tracer.currentSpan();
        }
        MDC.put("traceId",tracer.currentSpan().context().traceId());
        MDC.put("spanId",tracer.currentSpan().context().spanId());
        MDC.put("parentSpanId",tracer.currentSpan().context().parentId());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}