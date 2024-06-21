package rw.ac.rca.studentCourse.v1.dto.responses;

import java.util.Date;

public record UserDTO(
        java.util.UUID userId,
        String email,
        String username,
        String national_id,
        String role,
        Date lastLogin
        ) {
}
