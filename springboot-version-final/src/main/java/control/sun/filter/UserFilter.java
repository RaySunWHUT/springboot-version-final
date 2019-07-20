package control.sun.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "userFilter", urlPatterns = "/*")
public class UserFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("---------------->>> init");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("---------------->>> doFilter");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

        System.out.println("---------------->>> destroy");

    }

}
