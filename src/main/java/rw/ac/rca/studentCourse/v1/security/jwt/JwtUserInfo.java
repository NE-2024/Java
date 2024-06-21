package rw.ac.rca.studentCourse.v1.security.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class JwtUserInfo {
    private String userId;
    private String email;
    private String role;

}

