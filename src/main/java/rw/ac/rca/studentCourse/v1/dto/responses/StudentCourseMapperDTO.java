package rw.ac.rca.studentCourse.v1.dto.responses;

import org.springframework.stereotype.Component;
import rw.ac.rca.studentCourse.v1.models.StudentCourse;

import java.util.function.Function;
@Component
public class StudentCourseMapperDTO implements Function<StudentCourse, StudentCourseDTO> {
    @Override
    public StudentCourseDTO apply(StudentCourse studentCourse) {
        return new StudentCourseDTO(
                studentCourse.getId(),
                new StudentDTOMapper().apply(studentCourse.getStudent()),
                new CourseDTOMapper().apply(studentCourse.getCourse()),
                studentCourse.getStudentMarks()
        );
    }
}
