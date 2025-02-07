package by.frost.hibernate.starter;

import by.frost.hibernate.starter.converter.BirthdayConverter;
import by.frost.hibernate.starter.dao.CompanyRepository;
import by.frost.hibernate.starter.dao.UserRepository;
import by.frost.hibernate.starter.dto.CreateUserDto;
import by.frost.hibernate.starter.entity.*;
import by.frost.hibernate.starter.mapper.CompanyMapper;
import by.frost.hibernate.starter.mapper.CreateUserMapper;
import by.frost.hibernate.starter.mapper.UserMapper;
import by.frost.hibernate.starter.service.UserService;
import by.frost.hibernate.starter.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.Optional;

public class HibernateRunner {
    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            session.beginTransaction();


            var companyMapper = new CompanyMapper();
            var userMapper = new UserMapper(companyMapper);


            var userRepository = new UserRepository(session);
            var companyRepository = new CompanyRepository(session);
            var createUserMapper = new CreateUserMapper(companyRepository);
            var userService = new UserService(userRepository, userMapper, createUserMapper);

            //userService.findUserById(1).ifPresent(System.out::println);

            CreateUserDto createUserDto = new CreateUserDto(
                    PersonalInfo.builder()
                            .firstname("Bred")
                            .lastname("Pit")
                            //.birthDate(new Birthday(LocalDate.now()))
                            .build(),
                    "3231223",
                    Role.ADMIN,
                    1
                    );
            userService.create(createUserDto);


            session.getTransaction().commit();
        }

    }
}
