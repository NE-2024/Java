package rw.ac.rca.studentCourse.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;
import rw.ac.rca.studentCourse.v1.dto.requests.AssignMultipleCoursesToStudentDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentCourseDTO;
import rw.ac.rca.studentCourse.v1.dto.responses.StudentCourseMapperDTO;
import rw.ac.rca.studentCourse.v1.models.Course;
import rw.ac.rca.studentCourse.v1.models.Student;
import rw.ac.rca.studentCourse.v1.models.StudentCourse;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;
import rw.ac.rca.studentCourse.v1.repositories.CourseRepository;
import rw.ac.rca.studentCourse.v1.repositories.StudentCourseRepository;
import rw.ac.rca.studentCourse.v1.repositories.StudentRepository;
import rw.ac.rca.studentCourse.v1.services.StudentCourseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Service
@RequiredArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;
    private StudentCourseMapperDTO studentCourseMapperDTO;
    @Override
    public ResponseEntity<ApiResponse> registerStudentToCourse(UUID student_id, UUID course_id, CreateStudentCourseDTO createStudentCourseDTO) throws Exception {

        StudentCourse studentCourse = new StudentCourse(
                createStudentCourseDTO.getStudentMarks()
        );
        try {
           studentCourseRepository.save(studentCourse);
            return ResponseEntity.ok(new ApiResponse(true, "Student registered to course successfully", studentCourse));
        } catch (HttpServerErrorException.InternalServerError e){
            return ResponseEntity.status(500).body(new ApiResponse(
                    false,
                    "Failed to create the Student Course"
            ));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getStudentCourseById(UUID studentCourse_id) throws Exception {
        if(studentCourseRepository.existsById(studentCourse_id)){
            try {
                Optional<StudentCourse> studentCourse = studentCourseRepository.findById(studentCourse_id);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully fetched the student course",
                        studentCourse.map(studentCourseMapperDTO)
                ));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to get student course"));
            }
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Student course not found"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllStudentCourses() throws Exception {
        try {
            List<StudentCourse> studentCourses = studentCourseRepository.findAll();
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully fetched all student courses",
                    studentCourses.stream().map(studentCourseMapperDTO)
            ));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Failed to get student courses"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteStudentCourse(UUID studentCourse_id) throws Exception {
        if(studentCourseRepository.existsById(studentCourse_id)){
            try {
                studentCourseRepository.deleteById(studentCourse_id);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully deleted the student course"
                ));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to delete student course"));
            }
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Student course not found"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updateStudentCourse(UUID studentCourse_id, CreateStudentCourseDTO createStudentCourseDTO) throws Exception {

        if(studentCourseRepository.existsById(studentCourse_id)){
            StudentCourse studentCourse = studentCourseRepository.findById(studentCourse_id).get();
            studentCourse.setStudentMarks(createStudentCourseDTO.getStudentMarks());
            try {
                studentCourseRepository.save(studentCourse);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully updated the student course",
                        studentCourse
                ));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to update student course"));
            }
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Student course not found"));
        }
    }

    // assign multiple courses to student
    @Override
    public ResponseEntity<ApiResponse> assignCoursesToStudent(@RequestBody AssignMultipleCoursesToStudentDTO assignMultipleCoursesToStudentDTO) throws Exception {
           Student student = studentRepository.findById(assignMultipleCoursesToStudentDTO.getStudent_id()).get();
            List<Course> courses = courseRepository.findAllById(assignMultipleCoursesToStudentDTO.getCourse_id());
            try {
                for (Course course: courses){
                    StudentCourse studentCourse = new StudentCourse (
                            student,
                            course
                    );
                    studentCourseRepository.save(studentCourse);
                }
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully assigned courses to student"
                ));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to assign courses to student"));
            }
    }

}
