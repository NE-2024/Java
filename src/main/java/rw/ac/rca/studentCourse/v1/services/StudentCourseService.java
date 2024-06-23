package rw.ac.rca.studentCourse.v1.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import rw.ac.rca.studentCourse.v1.dto.requests.AssignMultipleCoursesToStudentDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentCourseDTO;
import rw.ac.rca.studentCourse.v1.models.Course;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface StudentCourseService {
    ResponseEntity<ApiResponse> registerStudentToCourse(UUID student_id, UUID course_id, CreateStudentCourseDTO createStudentCourseDTO) throws Exception;
    ResponseEntity<ApiResponse> getStudentCourseById(UUID studentCourse_id) throws Exception;
    ResponseEntity<ApiResponse> getAllStudentCourses() throws Exception;
    ResponseEntity<ApiResponse> deleteStudentCourse(UUID studentCourse_id) throws Exception;
    ResponseEntity<ApiResponse> updateStudentCourse(UUID studentCourse_id, CreateStudentCourseDTO createStudentCourseDTO) throws Exception;
    // assign multiple courses to student
    ResponseEntity<ApiResponse> assignCoursesToStudent(@RequestBody AssignMultipleCoursesToStudentDTO assignMultipleCoursesToStudentDTO) throws Exception;
}
