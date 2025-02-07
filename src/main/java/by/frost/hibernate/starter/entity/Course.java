package by.frost.hibernate.starter.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course", schema = "public")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students =  new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "course")
    private List<TeacherCourse> teacherCourses = new ArrayList<>();

    public void addStudent(Student student) {
        this.getStudents().add(student);
        student.setCourse(this);
    }
}

