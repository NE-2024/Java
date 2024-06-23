package rw.ac.rca.studentCourse.v1.dto.responses;

import org.springframework.stereotype.Component;
import rw.ac.rca.studentCourse.v1.models.Student;

import java.util.function.Function;
@Component
public class StudentDTOMapper implements Function<Student, StudentDTO>{
    @Override
    public  StudentDTO apply(Student student) {
        return new StudentDTO(
                student.getStudent_id(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getSchoolName(),
                student.getStudentNumber()
        );
    }
}