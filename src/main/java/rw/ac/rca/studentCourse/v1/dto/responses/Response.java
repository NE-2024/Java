package rw.ac.rca.studentCourse.v1.dto.responses;

import rw.ac.rca.studentCourse.v1.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {
   private ResponseType type;
   private T payload;

    public static record StudentCourseDTO() {
    }
}
