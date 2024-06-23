package rw.ac.rca.studentCourse.v1.dto.responses;

import java.util.Date;
import java.util.UUID;

public record UserDTO(
        UUID userId,
        String email,
        String username,
        String national_id,
        String role,
        Date lastLogin
        ) {
}
