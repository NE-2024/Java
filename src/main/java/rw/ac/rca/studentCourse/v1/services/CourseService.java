package rw.ac.rca.studentCourse.v1.services;

import org.springframework.http.ResponseEntity;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateCourseDTO;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;

import java.util.UUID;

public interface CourseService {
    ResponseEntity<ApiResponse> createCourse(CreateCourseDTO createCourseDTO) throws Exception;
    ResponseEntity<ApiResponse> getCourseById(UUID course_id) throws Exception;
    ResponseEntity<ApiResponse> getAllCourses() throws Exception;
    ResponseEntity<ApiResponse> deleteCourse(UUID course_id) throws Exception;
    ResponseEntity<ApiResponse> updateCourse(UUID course_id, CreateCourseDTO createCourseDTO) throws Exception;
}
