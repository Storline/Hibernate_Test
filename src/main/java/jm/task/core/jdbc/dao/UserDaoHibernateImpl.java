package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String createTable = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "last_name VARCHAR(60) NOT NULL, " +
                    "age TINYINT(3) NOT NULL)";
            session.createSQLQuery(createTable).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String dropTable = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(dropTable).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<>();
        Transaction transaction = null;
        try (SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String getAll = "SELECT * FROM user";
            userList = session.createNativeQuery(getAll).addEntity(User.class).list();

            for (Object user : userList) {
                System.out.println(user.toString());
            }
            transaction.commit();

            return userList;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(SessionFactory sessionFactory = Util.getSessionFactory(); Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String dropTable = "TRUNCATE TABLE user";
            session.createSQLQuery(dropTable).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
