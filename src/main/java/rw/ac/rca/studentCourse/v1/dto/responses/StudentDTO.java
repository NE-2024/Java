package rw.ac.rca.studentCourse.v1.dto.responses;

import java.util.UUID;

public record StudentDTO(
        UUID studentId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String schoolName,
        String studentNumber
) {


}
