package jm.task.core.jdbc.hibernate_test.dao;

import jm.task.core.jdbc.hibernate_test.model.User;
import jm.task.core.jdbc.hibernate_test.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            String createTable = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "last_name VARCHAR(60) NOT NULL, " +
                    "age TINYINT(3) NOT NULL)";
            session.createSQLQuery(createTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            String dropTable = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(dropTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            session.save(new User(name, lastName, age));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            session.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<>();
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            userList = session.createQuery("select u from User u", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try(SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            String truncateTable = "TRUNCATE TABLE user";
            session.createSQLQuery(truncateTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
