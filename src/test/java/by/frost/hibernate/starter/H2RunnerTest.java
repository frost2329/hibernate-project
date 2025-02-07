package by.frost.hibernate.starter;

import by.frost.hibernate.starter.entity.Company;
import by.frost.hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.util.List;

public class H2RunnerTest {
    @Test
    public void checkCrateDropAllTables() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Company company = Company.builder()
                .name("Google")
                .build();

        session.save(company);


        session.getTransaction().commit();
    }

    @Test
    public void checkHql() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

    /*  List users = session.createQuery("""
                select u
                from Profile p
                join p.user u
                where p.user.id = u.id
                and p.language = :language
                """)
                .setParameter("language", "EN")
                .list();
                */

        session.createQuery("update User u set u.role = 'ADMIN'").executeUpdate();

        session.getTransaction().commit();
    }
}
