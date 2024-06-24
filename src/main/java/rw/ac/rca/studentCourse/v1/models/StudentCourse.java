package rw.ac.rca.studentCourse.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;

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
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "student_marks")
    private Integer studentMarks=0;

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

    public StudentCourse(ResponseEntity<ApiResponse> student, ResponseEntity<ApiResponse> course, Integer studentMarks) {
    }
}
