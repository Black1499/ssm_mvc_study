package com.lzx.vo_filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CorsFilter")
public class CorsFilter implements Filter {

    @Override
    public void destroy() {
    }

    String[] allowOrigin = null;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String origin = request.getHeader("Origin");
        if (origin != null && !origin.isEmpty()){
            for (String o: allowOrigin) {
                if ("*".equals(o) || o.equals(origin)){
                    response.setHeader("Access-Control-Allow-Origin", origin);
                    break;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String webFilter = config.getInitParameter("webFilter");
        if (webFilter != null) {
            if ("*".equals(webFilter)) {
                allowOrigin = new String[]{"*"};
            } else {
                allowOrigin = webFilter.split(",");
            }
        }
    }

}
