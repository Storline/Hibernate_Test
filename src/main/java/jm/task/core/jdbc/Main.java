package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        Main test = new Main();
        test.createUsersTable();
        test.saveUser();
        test.getAllUsers();
        test.cleanUsersTable();
        test.dropUsersTable();
    }

    public void createUsersTable() {
        try {
            userService.createUsersTable();
        } catch (Exception e) {
            System.out.println("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    public void saveUser() {
        String name = null;
        String lastName = null;
        Byte age = null;

        userService.saveUser(name ="Alla", lastName = "Nemchenko", age = 29);
        System.out.println("User с именем " + name + " добавлен в базу данных");

        userService.saveUser(name = "Vasya", lastName = "Petrov", age = 23);
        System.out.println("User с именем " + name + " добавлен в базу данных");

        userService.saveUser(name = "Dimitri", lastName = "Demidov", age = 30);
        System.out.println("User с именем " + name + " добавлен в базу данных");

        userService.saveUser(name = "Alexey", lastName = "Malishev", age = 78);
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public String getAllUsers() {
        List<User> userList = userService.getAllUsers();

        return userList.toString();
    }

    public void cleanUsersTable() {
        userService.cleanUsersTable();
    }

    public void dropUsersTable() {
        userService.dropUsersTable();
    }

}