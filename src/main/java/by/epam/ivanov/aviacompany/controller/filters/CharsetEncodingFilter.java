package by.epam.ivanov.aviacompany.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

//@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
//        DispatcherType.INCLUDE}, urlPatterns = {"/*"}, initParams = {
//        @WebInitParam(name = "encoding", value = "UTF-8")})
public class CharsetEncodingFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(CharsetEncodingFilter.class);

    private String encoding;

    public void init(FilterConfig filterConfig) {
        LOGGER.debug("Filter init");
        encoding = filterConfig.getInitParameter("encoding");
        LOGGER.trace("Encoding ->" + encoding);
        LOGGER.debug("Filter init finished");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("Filter starts");
        String codeRequest = servletRequest.getCharacterEncoding();
        if (encoding == null && !encoding.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }

        LOGGER.debug("Filter finished with encoding - " + encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        LOGGER.debug("Filter destroy");
    }
}
