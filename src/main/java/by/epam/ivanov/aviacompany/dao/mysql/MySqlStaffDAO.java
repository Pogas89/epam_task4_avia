package by.epam.ivanov.aviacompany.dao.mysql;

import by.epam.ivanov.aviacompany.dao.DaoException;
import by.epam.ivanov.aviacompany.dao.StaffDAO;
import by.epam.ivanov.aviacompany.entity.Department;
import by.epam.ivanov.aviacompany.entity.Staff;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlStaffDAO extends MySqlBaseDAO implements StaffDAO {
    private static Logger LOGGER = Logger.getLogger(MySqlStaffDAO.class);

    private Staff getStafFromDB(ResultSet resultSet) throws DaoException {
        Staff staff;
        try {
            staff = new Staff();
            staff.setId(resultSet.getInt("st_id"));
            staff.setFirstName(resultSet.getString("st_Fname"));
            staff.setLastName(resultSet.getString("st_Lname"));
            staff.setDepartment(Department.values()[resultSet.getInt("department")]);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return staff;
    }

    @Override
    public Staff read(Integer id) throws DaoException {
        String sql = "SELECT * FROM staff WHERE st_id =?;";
        Staff staff = null;
        try (PreparedStatement statement =
                     getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                staff = getStafFromDB(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return staff;
    }

    @Override
    public Staff readByLastName(String lastName) throws DaoException {
        String sql = "SELECT * FROM staff WHERE st_Lname =?;";
        Staff staff = null;
        try (PreparedStatement statement =
                     getConnection().prepareStatement(sql)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                staff = getStafFromDB(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return staff;
    }

    @Override
    public List<Staff> getStaffs() throws DaoException {
        String sql = "SELECT * FROM staff;";
        List<Staff> staffList = new ArrayList<>();
        Staff staff;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                staff = getStafFromDB(resultSet);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }
        return staffList;
    }

    @Override
    public Integer create(Staff staff) throws DaoException {
        String sql = "INSERT INTO staff (st_Fname, st_Lname, department) " +
                "VALUES (?,?,?);";
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setInt(3, staff.getDepartment().ordinal());
            preparedStatement.executeUpdate();
            Integer id = null;
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }

    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM staff WHERE st_id=?;";
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DaoException(e);
        }

    }

    @Override
    public void update(Staff staff) {
        String sql = "UPDATE staff SET st_Fname = ?," +
                "st_Lname = ?, department = ? WHERE st_id = ?;";
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, staff.getFirstName());
            preparedStatement.setString(2, staff.getLastName());
            preparedStatement.setInt(3, staff.getDepartment().ordinal());
            preparedStatement.setInt(4, staff.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Staff> getStaffsFromCrew(Integer cr_id) throws DaoException {
        String sql = "SELECT staff.* FROM staff,crew,crew_staff " +
                "WHERE crew.cr_id =? AND crew.cr_id=crew_staff.cr_id AND staff.st_id = crew_staff.st_id;";
        List<Staff> staffList = new ArrayList<>();
        Staff staff;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1,cr_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                staff=getStafFromDB(resultSet);
                staffList.add(staff);
            }
            return staffList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Staff> getFreeStaffs() throws DaoException {
        String sql = "SELECT staff.* FROM staff WHERE st_id NOT IN (SELECT crew_staff.st_id FROM crew_staff);";
        List<Staff> freeStaff = new ArrayList<>();
        Staff staff;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                staff = getStafFromDB(resultSet);
                freeStaff.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freeStaff;
    }
}
