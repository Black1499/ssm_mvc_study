package com.lzx.vo_filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SimpleCorsFilter")
public class SimpleCorsFilter implements Filter {
    @Override
    public void destroy() {

    }

    private String doFilter = null;
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String[] allowList = new String[]{
                "http://localhost:8080",
                "http://localhost:7070",
                "http://localhost:63342",
        };
        String origin = request.getHeader("origin");
        if (origin != null && !origin.isEmpty()) {
            boolean isOrigin = false;
            for (String s : allowList) {
                if (origin.equals(s)) {
                    isOrigin = true;
                    break;
                }
            }
            if (isOrigin) {
                response.setHeader("Access-Control-Allow-Origin", origin);
            }
        }

        response.setHeader("Access-Control-Allow-Origin", origin);

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        doFilter = config.getInitParameter("doFilter");
    }

}
