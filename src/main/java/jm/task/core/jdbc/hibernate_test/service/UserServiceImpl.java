package jm.task.core.jdbc.hibernate_test.service;

import jm.task.core.jdbc.hibernate_test.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.hibernate_test.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    //    UserDaoJDBCImpl service = new UserDaoJDBCImpl();
    UserDaoHibernateImpl service = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        service.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        service.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        service.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        service.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        service.cleanUsersTable();
    }
}
