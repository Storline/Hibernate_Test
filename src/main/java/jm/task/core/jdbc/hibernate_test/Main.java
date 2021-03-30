package jm.task.core.jdbc.hibernate_test;

import jm.task.core.jdbc.hibernate_test.service.UserService;
import jm.task.core.jdbc.hibernate_test.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Vasya", "Pupkin", (byte) 29);
        userService.saveUser("Petya", "Pupkin", (byte) 14);
        userService.saveUser("Alla", "Pupkina", (byte) 20);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}