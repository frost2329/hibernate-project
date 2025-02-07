package by.frost.hibernate.starter.dao;

import by.frost.hibernate.starter.entity.User;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class UserRepository extends BaseRepository<Integer, User>{
    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
