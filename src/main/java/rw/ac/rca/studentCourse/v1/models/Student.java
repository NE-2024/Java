package rw.ac.rca.studentCourse.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import rw.ac.rca.studentCourse.v1.dto.responses.StudentDTOMapper;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = {"student_number", "email"})})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID student_id;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull
    @Column(name = "school_name")
    private String schoolName;
    @NotNull
    @Column(name = "student_number")
    private String studentNumber;



    public Student(String firstName, String lastName, String email, String phoneNumber, String schoolName, String studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.schoolName = schoolName;
        this.studentNumber = studentNumber;
    }


}
