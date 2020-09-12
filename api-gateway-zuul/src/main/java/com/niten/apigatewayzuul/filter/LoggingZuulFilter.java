package com.niten.apigatewayzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

public class LoggingZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {

        // pre, post processing
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        final HttpServletRequest request = context.getRequest();

        System.err.println(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        return null;
    }
}
