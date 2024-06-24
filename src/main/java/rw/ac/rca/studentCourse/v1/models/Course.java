package rw.ac.rca.studentCourse.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID course_id;
    @NotNull
    private String courseName;
    @NotNull
    private String courseCode;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<StudentCourse> studentCourseList;

    @NotNull
    private Integer passMark;

    public Course(String courseName, String courseCode, Integer passMark, List<StudentCourse> studentCourseList) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.passMark = passMark;
        this.studentCourseList = studentCourseList;
    }
    public Course(String courseName, String courseCode, Integer passMark) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.passMark = passMark;
    }
}
