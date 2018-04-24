package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.dao.mysql.MySqlUserDAO;
import by.epam.ivanov.aviacompany.entity.User;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.logic.UserServiceImpl;
import by.epam.ivanov.aviacompany.util.connection.Connector;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserListController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserListController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            MySqlUserDAO userDAO = new MySqlUserDAO();
            userDAO.setConnection(connection);
            UserServiceImpl userService = new UserServiceImpl();
            userService.setUserDAO(userDAO);
            List<User> userList = userService.readUsers();
            req.setAttribute("userList", userList);
            req.getRequestDispatcher("/WEB-INF/jsp/admin/userlist.jsp").forward(req, resp);
        } catch (SQLException | ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
        }
        finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void init() throws ServletException {
        String url = "jdbc:mysql://localhost:3306/aviacompany" +
                "?verifyServerCertificate=false" +
                "&useSSL=false" +
                "&requireSSL=false" +
                "&useLegacyDatetimeCode=false" +
                "&amp" +
                "&serverTimezone=UTC";
        try {
            Connector.init(10, "com.mysql.jdbc.Driver", url,
                    "root", "110989");
            LOGGER.debug("Controller pool has been init");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }
}
