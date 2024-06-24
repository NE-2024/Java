package rw.ac.rca.studentCourse.v1.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import rw.ac.rca.studentCourse.v1.dto.requests.AssignMultipleCoursesToStudentDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentCourseDTO;
import rw.ac.rca.studentCourse.v1.models.Course;
import rw.ac.rca.studentCourse.v1.models.StudentCourse;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface StudentCourseService {
    StudentCourse registerStudentToCourse(UUID student_id, UUID course_id, CreateStudentCourseDTO createStudentCourseDTO) throws Exception;
    StudentCourse getStudentCourseById(UUID studentCourse_id) throws Exception;
    List<StudentCourse> getAllStudentCourses() throws Exception;
    String deleteStudentCourse(UUID studentCourse_id) throws Exception;
    StudentCourse updateStudentCourse(UUID studentCourse_id, CreateStudentCourseDTO createStudentCourseDTO) throws Exception;
    // assign multiple courses to student
    StudentCourse assignCoursesToStudent(@RequestBody AssignMultipleCoursesToStudentDTO assignMultipleCoursesToStudentDTO) throws Exception;
}
