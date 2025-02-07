package by.frost.hibernate.starter;

import by.frost.hibernate.starter.entity.*;
import by.frost.hibernate.starter.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

public class StudentRunnerTest {
    @Test
    public void checkOneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Student student = Student.builder()
                .firstname("Денис2")
                .lastname("Алексеев")
                .build();

        Course course = session.get(Course.class, 1);

        course.addStudent(student);

        session.saveOrUpdate(student);


        session.getTransaction().commit();
    }

    @Test
    public void getStudentOnCourseJavaguru() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();


        Course course = session.get(Course.class, 1);

        System.out.println(course.getStudents());


        session.getTransaction().commit();
    }

    @Test
    public void removeStudentWithBadGrade() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();


        Course course = session.get(Course.class, 1);

        course.getStudents().removeIf(student -> student.getStudentProfile().getGrade() < 6);

        session.saveOrUpdate(course);

        System.out.println(course.getStudents());


        session.getTransaction().commit();
    }

    @Test
    public void addNewTeacher() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();


        Course course = session.get(Course.class, 1);
        Teacher teacher = Teacher.builder().name("Inigo").build();
        TeacherCourse teacherCourse = TeacherCourse.builder().build();
        teacherCourse.setTeacher(teacher);
        teacherCourse.setCourse(course);

        session.saveOrUpdate(teacherCourse);


        session.getTransaction().commit();
    }
}
