package rw.ac.rca.studentCourse.v1.dto.responses;

import org.springframework.stereotype.Component;
import rw.ac.rca.studentCourse.v1.models.Course;

import java.util.function.Function;
@Component
public class CourseDTOMapper implements Function<Course, CourseDTO> {
    @Override
    public CourseDTO apply(Course course) {
        return new CourseDTO(
                course.getCourse_id(),
                course.getCourseName(),
                course.getCourseCode(),
                course.getPassMark()
        );
    }
}
