package rw.ac.rca.studentCourse.v1.dto.responses;

import java.util.UUID;

public record CourseDTO(
        UUID courseId,
        String courseName,
        String courseCode,
        Integer passMark
) {
}
