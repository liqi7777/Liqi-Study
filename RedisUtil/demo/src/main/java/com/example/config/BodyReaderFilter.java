package com.example.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Sky
 * create 2019/03/20
 * email sky.li@ixiaoshuidi.com
 **/

//@WebFilter(filterName = "bodyReaderFilter", urlPatterns = {"/wrapper/request"})
public class BodyReaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        chain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
