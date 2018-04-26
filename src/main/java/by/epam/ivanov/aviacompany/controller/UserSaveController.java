package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.dao.mysql.MySqlUserDAO;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.entity.UserRole;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.logic.UserServiceImpl;
import by.epam.ivanov.aviacompany.util.connection.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserSaveController extends HttpServlet {
    private Logger LOGGER = Logger.getLogger(UserSaveController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));
        try {
            user.setUserRole(UserRole.values()[Integer.parseInt(req.getParameter("userRole"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        }
        if (user.getLogin() != null && user.getUserRole() != null){
            Connection connection = null;
            try{
                connection = ConnectionPool.getInstance().getConnection();
                MySqlUserDAO userDAO = new MySqlUserDAO();
                userDAO.setConnection(connection);
                UserServiceImpl userService = new UserServiceImpl();
                userService.setUserDAO(userDAO);
                userService.save(user);
            } catch (ServiceException | SQLException | ClassNotFoundException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            } finally {
                try{
                    connection.close();
                } catch (Exception e) {
                }
            }
            resp.sendRedirect(req.getContextPath()+ "/admin/userlist.html");
        }
    }
}
