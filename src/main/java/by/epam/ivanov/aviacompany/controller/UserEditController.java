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

public class UserEditController extends HttpServlet {
    private Logger LOGGER = Logger.getLogger(UserEditController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            LOGGER.debug("id="+id);
        } catch (NumberFormatException e){}
        if (id != null) {
            Connection connection = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
                MySqlUserDAO dao = new MySqlUserDAO();
                dao.setConnection(connection);
                UserServiceImpl service = new UserServiceImpl();
                service.setUserDAO(dao);
                User user = service.readById(id);
                req.setAttribute("user", user);
            } catch (ClassNotFoundException | SQLException | ServiceException e) {
                LOGGER.error(e.getMessage());
                throw new ServletException(e);
            }  finally {
                try{
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        req.setAttribute("userRoles", UserRole.values());
        req.getRequestDispatcher("/WEB-INF/jsp/admin/useredit.jsp").forward(req,resp);
    }
}
