package rw.ac.rca.studentCourse.v1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_course")

public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID studentCourse_id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private Integer studentMarks;

    public StudentCourse( Student student, Course course, Integer studentMarks) {
        this.student = student;
        this.course = course;
        this.studentMarks = studentMarks;
    }
    public StudentCourse(  Integer studentMarks) {
        this.studentMarks = studentMarks;
    }


    public StudentCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
