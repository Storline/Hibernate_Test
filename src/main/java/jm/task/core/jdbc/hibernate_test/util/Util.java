package jm.task.core.jdbc.hibernate_test.util;

import jm.task.core.jdbc.hibernate_test.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util{
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/jmsql?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory getSessionFactory(){
        Configuration cfg = new Configuration();

        try {
            cfg.setProperty("hibernate.connection.url", URL);
            cfg.setProperty("hibernate.connection.username", USERNAME);
            cfg.setProperty("hibernate.connection.password", PASSWORD);

            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            cfg.setProperty("show_sql", "true");
            cfg.setProperty("hibernate.hbm2ddl.auto", "update");

            cfg.addAnnotatedClass(User.class);

//            serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(cfg.getProperties())
//                    .build();
//            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cfg.buildSessionFactory();
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

}
