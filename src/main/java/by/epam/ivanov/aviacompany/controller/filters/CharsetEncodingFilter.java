package by.epam.ivanov.aviacompany.controller.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

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
        if (servletRequest.getCharacterEncoding() == null) {
            servletRequest.setCharacterEncoding(encoding);
        }

        LOGGER.debug("Filter finished with encoding - " + encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        LOGGER.debug("Filter destroy");
    }
}
