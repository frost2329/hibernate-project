package by.frost.hibernate.starter;

import by.frost.hibernate.starter.entity.*;
import by.frost.hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class HibernateRunnerTest {
    @Test
    public void checkOneToMany() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            Company company = session.get(Company.class, 2);
            System.out.println(company.getUsers());
        }
    }

    @Test
    public void addNewUserAndCompany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        Company company =  Company.builder()
                .name("Google")
                .build();

        User user = User.builder()
                .username("Den5@mail.com")
                .build();

        company.addUser(user);

        session.saveOrUpdate(company);

        session.getTransaction().commit();

    }

    @Test
    public void checkOrphalRemoval() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        Company company =  session.get(Company.class, 2);
        company.getUsers().removeIf(user -> user.getId().equals(1));

        session.saveOrUpdate(company);

        session.getTransaction().commit();

    }

    @Test
    public void checkOneToOne() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();

        User user = User.builder()
                .personalInfo(PersonalInfo.builder()
                        .firstname("James")
                        .lastname("Bond")
                        .birthDate(new Birthday(LocalDate.now()))
                        .build())
                .username("Den4@mail.com")
                .build();

        Profile profile = Profile.builder()
                .street("yablochnay 1")
                .language("EN")
                .build();

        session.saveOrUpdate(user);
        profile.setUser(user);
        session.saveOrUpdate(profile);

        session.getTransaction().commit();
    }

    @Test
    public void checkManyToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        User user = session.get(User.class, 8);
        Chat chat = session.get(Chat.class, 1);

        UserChat userChat = UserChat.builder()
                .createdAt(Instant.now())
                .createdBy("Denis")
                .build();

        userChat.setChat(chat);
        userChat.setUser(user);


        session.save(userChat);

        session.getTransaction().commit();
    }

    @Test
    public void checkMappingRules() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> users = session.createQuery("select u from User u", User.class).list();

        for (User u : users) {
            System.out.println(u.getCompany().getName());
        }

        session.getTransaction().commit();
    }
}
