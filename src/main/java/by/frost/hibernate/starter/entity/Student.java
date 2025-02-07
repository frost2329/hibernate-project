package by.frost.hibernate.starter.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
@Entity
@Table(name = "students", schema = "public")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="firstname", nullable = false)
    private String firstname;
    @Column(name="lastname", nullable = false)
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    StudentProfile studentProfile;

    public void setStudentProfile(StudentProfile studentProfile){
        this.studentProfile = studentProfile;
        studentProfile.setStudent(this);
    }
}
