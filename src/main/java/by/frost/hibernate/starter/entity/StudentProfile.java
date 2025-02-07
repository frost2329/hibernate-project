package by.frost.hibernate.starter.entity;

import lombok.*;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "student")
@Entity
@Table(name = "student_profile", schema = "public")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer grade;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
