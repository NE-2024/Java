package rw.ac.rca.studentCourse.v1.dto.responses;

import java.util.UUID;

public record StudentCourseDTO(
       UUID studentCourseId,
        StudentDTO student,
        CourseDTO course,
        Integer studentMarks

) {
}
