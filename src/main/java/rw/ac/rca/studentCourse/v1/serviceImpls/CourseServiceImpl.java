package rw.ac.rca.studentCourse.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateCourseDTO;
import rw.ac.rca.studentCourse.v1.dto.responses.CourseDTOMapper;
import rw.ac.rca.studentCourse.v1.dto.responses.StudentCourseMapperDTO;
import rw.ac.rca.studentCourse.v1.models.Course;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;
import rw.ac.rca.studentCourse.v1.repositories.CourseRepository;
import rw.ac.rca.studentCourse.v1.services.CourseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Component
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseDTOMapper courseDTOMapper;
    @Override
    public ResponseEntity<ApiResponse> createCourse( CreateCourseDTO createCourseDTO) throws Exception {
        Course course = new Course(
                createCourseDTO.getCourseName(),
                createCourseDTO.getCourseCode(),
                createCourseDTO.getPassMark()
        );
        try {
            courseRepository.save(course);
            return ResponseEntity.ok(new ApiResponse(true, "Course saved successfully", course));
        } catch (HttpServerErrorException.InternalServerError e){
            return ResponseEntity.status(500).body(new ApiResponse(
                    false,
                    "Failed to create the Course"
            ));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getCourseById(UUID course_id) throws Exception {
        if(courseRepository.existsById(course_id)){
            try {
                Optional<Course> course = courseRepository.findById(course_id);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully fetched the course",
                        course.map(courseDTOMapper)
                ));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to get course"));
            }
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Course not found"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllCourses() throws Exception {
        try {
            List<Course> courses = courseRepository.findAll();
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully fetched all courses",
                    courses.stream().map(courseDTOMapper)
            ));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Failed to get courses"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCourse(UUID course_id) throws Exception {
       if(courseRepository.existsById(course_id)){
           try {
               courseRepository.deleteById(course_id);
               return ResponseEntity.ok(new ApiResponse(true, "Course deleted successfully"));
           } catch (Exception e) {
               return ResponseEntity.ok(new ApiResponse(false, "Failed to delete course"));
           }
       } else {
           return ResponseEntity.ok(new ApiResponse(false, "Course not found"));
       }
    }

    @Override
    public ResponseEntity<ApiResponse> updateCourse(UUID course_id, CreateCourseDTO createCourseDTO) throws Exception {
        if(courseRepository.existsById(course_id)){
            Course course = new Course(
                    createCourseDTO.getCourseName(),
                    createCourseDTO.getCourseCode(),
                    createCourseDTO.getPassMark());
            try {
                courseRepository.save(course);
                return ResponseEntity.ok(new ApiResponse(true, "Course updated successfully", course));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to update course"));
            }
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Course not found"));
        }
    }
}
