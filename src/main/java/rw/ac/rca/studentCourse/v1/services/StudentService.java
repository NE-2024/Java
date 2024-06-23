package rw.ac.rca.studentCourse.v1.services;

import org.springframework.http.ResponseEntity;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentDTO;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;

import java.util.UUID;

public interface StudentService {
    ResponseEntity<ApiResponse> saveStudent(CreateStudentDTO createStudentDTO) throws Exception;
    ResponseEntity<ApiResponse> getStudentById(UUID student_id) throws Exception;
    ResponseEntity<ApiResponse> getAllStudents() throws Exception;
    ResponseEntity<ApiResponse> deleteStudent(UUID student_id) throws Exception;
    ResponseEntity<ApiResponse> updateStudent(UUID student_id, CreateStudentDTO createStudentDTO) throws Exception;
}
