package by.frost.hibernate.starter;

import by.frost.hibernate.starter.dao.UserDao;
import by.frost.hibernate.starter.dto.UserFilter;
import by.frost.hibernate.starter.entity.User;
import by.frost.hibernate.starter.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    private UserDao userDao;
    private Session session;

    @BeforeEach
    public void setUp() {
        userDao = new UserDao();
        var sessionFactory = HibernateUtil.buildSessionFactory(); // Создаем сессию с помощью вашего метода
        session = sessionFactory.openSession();

    }

    @AfterEach
    public void tearDown() {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback(); // Откат транзакции для чистоты тестов
        }
        session.close(); // Закрываем сессию
    }

    @Test
    public void testFindAll() {
        session.beginTransaction();
        // Act
        List<User> users = userDao.findAll(session);

        // Assert
        assertNotNull(users);
        assertTrue(!users.isEmpty());
    }

    @Test
    public void testFindByName() {
        session.beginTransaction();
        // Act
        List<User> users = userDao.findByName(session, "James");

        // Assert
        assertNotNull(users);
        assertTrue(users.stream().anyMatch(user -> user.getPersonalInfo().getFirstname().equals("James")), "List should contain user named 'James'");
    }

    @Test
    public void findAllByCompanyName() {
        session.beginTransaction();
        String companyName = "Meta";
        // Act
        List<User> users = userDao.findAllByCompanyName(session, companyName);

        // Assert
        assertNotNull(users);
        assertTrue(users.stream().anyMatch(user -> user.getCompany().getName().equals(companyName)), "Invalid case");
    }

    @Test
    public void festFindByFirstnameAndLastname() {
        session.beginTransaction();
        // Act
        List<User> users = userDao.findByFirstnameAndLastname(
                session,
                UserFilter.builder()
                        .firstname("James").lastname("Bond")
                        .build());

        // Assert
        assertNotNull(users);
        assertTrue(users.stream().anyMatch(user -> user.getPersonalInfo().getFirstname().equals("James")), "List should contain user named 'James'");
        assertTrue(users.stream().anyMatch(user -> user.getPersonalInfo().getLastname().equals("Bond")), "List should contain user named 'James'");
    }
}