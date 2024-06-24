package rw.ac.rca.studentCourse.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentDTO;
import rw.ac.rca.studentCourse.v1.models.Student;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    Student saveStudent(CreateStudentDTO createStudentDTO) throws Exception;
    Student getStudentById(UUID student_id) throws Exception;
    List<Student> getAllStudents() throws Exception;
    Page<Student> getAllStudentsPaginated(Pageable pageable) throws Exception;
    String deleteStudent(UUID student_id) throws Exception;
    Student updateStudent(UUID student_id, CreateStudentDTO createStudentDTO) throws Exception;
}
