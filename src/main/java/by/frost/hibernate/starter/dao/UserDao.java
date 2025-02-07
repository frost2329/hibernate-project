package by.frost.hibernate.starter.dao;

import by.frost.hibernate.starter.dto.UserFilter;
import by.frost.hibernate.starter.entity.QCompany;
import by.frost.hibernate.starter.entity.QUser;
import by.frost.hibernate.starter.entity.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

public class UserDao {
    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session)
                .select(QUser.user)
                .from(QUser.user)
                .fetch();
    }

    public  List<User>findByName(Session session, String firstName) {
        return new JPAQuery<User>(session)
                .select(QUser.user)
                .from(QUser.user)
                .where(QUser.user.personalInfo().firstname.eq(firstName))
                .fetch();
    }

    public  List<User>findAllByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session)
                .select(QUser.user)
                .from(QUser.user)
                .where(QUser.user.company().name.eq(companyName))
                .orderBy(QUser.user.personalInfo().firstname.asc())
                .fetch();
    }

    public  List<User>findByFirstnameAndLastname(Session session, UserFilter filter) {
        var predicate = QPredicate.builder()
                .add(filter.getFirstname(), QUser.user.personalInfo().firstname::eq)
                .add(filter.getLastname(), QUser.user.personalInfo().lastname::eq)
                .buildAnd();
        return new JPAQuery<User>(session)
                .select(QUser.user)
                .from(QUser.user)
                .where(predicate)
                .fetch();
    }
}
