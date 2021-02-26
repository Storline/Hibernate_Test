package jm.task.core.jdbc.hibernate_test.util;

import jm.task.core.jdbc.hibernate_test.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util{
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/jmsql?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final SessionFactory sessionFactory = configureSessionFactory();

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration cfg = new Configuration();

        try {
            cfg.setProperty("hibernate.connection.url", URL);
            cfg.setProperty("hibernate.connection.username", USERNAME);
            cfg.setProperty("hibernate.connection.password", PASSWORD);

            cfg.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            cfg.setProperty("show_sql", "true");
            cfg.setProperty("hibernate.hbm2dll.auto", "update");

            cfg.addAnnotatedClass(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to connect");
        }
        return connection;
    }

    public static void close() {
        sessionFactory.close();
    }
}
