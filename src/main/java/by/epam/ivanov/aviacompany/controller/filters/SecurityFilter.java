package by.epam.ivanov.aviacompany.controller.filters;

import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.entity.UserRole;
import by.epam.ivanov.aviacompany.util.Commands;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);
    private static final Map<String, Set<UserRole>> permits = new HashMap<>();

    static {
        Set<UserRole> all = new HashSet<>();
        all.addAll(Arrays.asList(UserRole.values()));
        Set<UserRole> admin = new HashSet<>();
        admin.add(UserRole.ADMIN);
        Set<UserRole> dispetcher = new HashSet<>();
        dispetcher.add(UserRole.DISPETCHER);

        permits.put(Commands.USERLIST_COMMAND, admin);
        permits.put(Commands.USEREDIT_COMMAND, admin);
        permits.put(Commands.USERSAVE_COMMAND, admin);
        permits.put(Commands.USERDELETE_COMMAND, admin);
        permits.put(Commands.STAFFLIST_COMMAND, dispetcher);
        permits.put(Commands.STAFFEDIT_COMMAND,dispetcher);
        permits.put(Commands.STAFFSAVE_COMMAND,dispetcher);
        permits.put(Commands.STAFFDELETE_COMMAND, dispetcher);
        permits.put(Commands.LOGOUT_COMMAND, all);
        permits.put(Commands.CREWLIST_COMMAND, dispetcher);
        permits.put(Commands.CREWEDIT_COMMAND, dispetcher);
        permits.put(Commands.CREWSAVE_COMMAND, dispetcher);
        permits.put(Commands.CREWSHOW_COMMAND, dispetcher);
        permits.put(Commands.ADDSTAFFINCREW_COMMAND, dispetcher);
        permits.put(Commands.DELSTAFFFROMCREW_COMMAND, dispetcher);
        permits.put(Commands.FLIGHTLIST_COMMAND, all);
        permits.put(Commands.FLIGHTEDIT_COMMAND, all);
        permits.put(Commands.FLIGHTSAVE_COMMAND, all);
        permits.put(Commands.USEREDITPASS_COMMAND, all);
        permits.put(Commands.USERSAVEPASS_COMMAND, all);
        permits.put(Commands.USERSETDEFPASS_COMMAND, admin);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("SecurityFilter init");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        Set<UserRole> userRoles = permits.get(url);
        if(userRoles != null) {
            HttpSession session = request.getSession(false);
            if(session != null) {
                User user = (User)session.getAttribute("currentUser");
                if(user != null && userRoles.contains(user.getUserRole())) {
                    LOGGER.debug("user have permits");
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        } else {
            LOGGER.debug("user need not permits");
            filterChain.doFilter(request, response);
            return;
        }
        LOGGER.info("user have not permits ");
        response.sendRedirect(Commands.LOGIN_COMMAND + "?message=app.access.denied");

    }

    @Override
    public void destroy() {
    }
}
