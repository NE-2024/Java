package rw.ac.rca.studentCourse.v1.models;

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
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID student_id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String schoolName;
    @NotNull
    private String studentNumber;

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> studentCourseList;

    public Student(String firstName, String lastName, String email, String phoneNumber, String schoolName, String studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.schoolName = schoolName;
        this.studentNumber = studentNumber;
    }


}
