package jm.task.core.jdbc.hibernate_test.service;

import jm.task.core.jdbc.hibernate_test.dao.UserDao;
import jm.task.core.jdbc.hibernate_test.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.hibernate_test.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao service = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() throws SQLException {
        service.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        service.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        service.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        service.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return service.getAllUsers();
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        service.cleanUsersTable();
    }
}
