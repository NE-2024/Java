package rw.ac.rca.studentCourse.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateCourseDTO;
import rw.ac.rca.studentCourse.v1.dto.responses.CourseDTOMapper;
import rw.ac.rca.studentCourse.v1.dto.responses.StudentCourseMapperDTO;
import rw.ac.rca.studentCourse.v1.exceptions.ResourceNotFoundException;
import rw.ac.rca.studentCourse.v1.models.Course;
import rw.ac.rca.studentCourse.v1.models.Student;
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
    public Course createCourse(CreateCourseDTO createCourseDTO) throws Exception {
        Course course = new Course(
                createCourseDTO.getCourseName(),
                createCourseDTO.getCourseCode(),
                createCourseDTO.getPassMark()
        );
        try {
            return courseRepository.save(course);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new Exception("Failed to create the course");
        }
    }

    @Override
    public Course getCourseById(UUID course_id)  {
        return courseRepository.findById(course_id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", course_id.toString()));
    }

    @Override
    public List<Course> getAllCourses() throws Exception {
        try {
            return  courseRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to get courses");
        }
    }

    @Override
    public Page<Course> getAllCoursesPaginated(Pageable pageable) throws Exception {
        try{
            return courseRepository.findAll(pageable);
        }catch(Exception exception){
            ExceptionUtils.handleThrowable(exception);
            return null;
        }
    };


    @Override
    public String deleteCourse(UUID course_id) throws Exception {
        if (courseRepository.existsById(course_id)) {
            try {
                courseRepository.deleteById(course_id);
                return "Course deleted successfully";
            } catch (HttpServerErrorException.InternalServerError e) {
                throw new Exception("Failed to delete course");
            }
        } else {
            throw new ResourceNotFoundException("Course", "id", course_id.toString());
        }
    }

    @Override
    public Course updateCourse(UUID course_id, CreateCourseDTO createCourseDTO) throws Exception {
        if (courseRepository.existsById(course_id)) {
            Course course = new Course(
                    createCourseDTO.getCourseName(),
                    createCourseDTO.getCourseCode(),
                    createCourseDTO.getPassMark());
            try {
                return courseRepository.save(course);
            } catch (HttpServerErrorException.InternalServerError e) {
                throw new Exception("Failed to update the course");
            }
        } else {
            throw new ResourceNotFoundException("Course", "id", course_id.toString());
        }
    }
}