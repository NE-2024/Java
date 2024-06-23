package rw.ac.rca.studentCourse.v1.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import rw.ac.rca.studentCourse.v1.models.StudentCourse;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CreateCourseDTO {
    private String courseName;
    private String courseCode;
    private Integer passMark;
    //private List<StudentCourse> studentCourseList;

}
