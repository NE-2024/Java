package rw.ac.rca.studentCourse.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateCourseDTO;
import rw.ac.rca.studentCourse.v1.models.Course;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course createCourse(CreateCourseDTO createCourseDTO) throws Exception;
    Course getCourseById(UUID course_id) ;
    List<Course> getAllCourses() throws Exception;
    Page<Course> getAllCoursesPaginated(Pageable pageable) throws Exception;
    String deleteCourse(UUID course_id) throws Exception;
    Course updateCourse(UUID course_id, CreateCourseDTO createCourseDTO) throws Exception;
}
