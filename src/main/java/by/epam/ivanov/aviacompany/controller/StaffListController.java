package by.epam.ivanov.aviacompany.controller;

import by.epam.ivanov.aviacompany.dao.mysql.MySqlStaffDAO;
import by.epam.ivanov.aviacompany.entity.Staff;
import by.epam.ivanov.aviacompany.service.ServiceException;
import by.epam.ivanov.aviacompany.service.logic.StaffServiceImpl;
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

public class StaffListController extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(StaffListController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection;
        try {
            connection = Connector.getConnection();
            MySqlStaffDAO staffDAO = new MySqlStaffDAO();
            staffDAO.setConnection(connection);
            StaffServiceImpl staffService = new StaffServiceImpl();
            staffService.setStaffDAO(staffDAO);
            List<Staff> staffList = staffService.readStaffs();
            req.setAttribute("staffList", staffList);
            req.getRequestDispatcher("/WEB-INF/jsp/admin/stafflist.jsp").forward(req, resp);
        } catch (SQLException | ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ServletException(e);
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