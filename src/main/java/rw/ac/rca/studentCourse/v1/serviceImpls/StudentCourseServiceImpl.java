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
import rw.ac.rca.studentCourse.v1.services.CourseService;
import rw.ac.rca.studentCourse.v1.services.StudentCourseService;
import rw.ac.rca.studentCourse.v1.services.StudentService;

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
    private final StudentService studentService;
    private final CourseService courseService;
    private StudentCourseMapperDTO studentCourseMapperDTO;
    @Override
    public StudentCourse registerStudentToCourse(UUID student_id, UUID course_id, CreateStudentCourseDTO createStudentCourseDTO) throws Exception {
       Student student = studentService.getStudentById(student_id);
        Course course = courseService.getCourseById(course_id);
        StudentCourse studentCourse = new StudentCourse(
                student,
                course,
                createStudentCourseDTO.getStudentMarks()
        );
        try {
            return studentCourseRepository.save(studentCourse);
        } catch (HttpServerErrorException.InternalServerError e){
            throw new Exception("Failed to register student to course");
        }
    }

    @Override
    public StudentCourse getStudentCourseById(UUID studentCourse_id) throws Exception {
      return studentCourseRepository.findById(studentCourse_id).orElseThrow(()-> new Exception("Student course not found"));
    }

    @Override
    public List<StudentCourse> getAllStudentCourses() throws Exception {
        try {
            return studentCourseRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to get student courses");
    }
    }

    @Override
    public String deleteStudentCourse(UUID studentCourse_id) throws Exception {
        if(studentCourseRepository.existsById(studentCourse_id)){
            try {
                studentCourseRepository.deleteById(studentCourse_id);
                return "Student course deleted successfully";
            } catch (Exception e){
                throw new Exception("Failed to delete the student course");
            }
        } else {
            return "Student course not found";
        }
    }

    @Override
    public StudentCourse updateStudentCourse(UUID studentCourse_id, CreateStudentCourseDTO createStudentCourseDTO) throws Exception {

        if (studentCourseRepository.existsById(studentCourse_id)) {
            StudentCourse studentCourse = studentCourseRepository.findById(studentCourse_id).get();
            studentCourse.setStudentMarks(createStudentCourseDTO.getStudentMarks());
            try {
                return studentCourseRepository.save(studentCourse);
            } catch (Exception e) {
                throw new Exception("Failed to update student course");
            }
        } else {
            throw new Exception("Student course not found");
        }
    }

    // assign multiple courses to student
    @Override
    public StudentCourse assignCoursesToStudent(@RequestBody AssignMultipleCoursesToStudentDTO assignMultipleCoursesToStudentDTO) throws Exception {
        try {
            Student student = studentService.getStudentById(assignMultipleCoursesToStudentDTO.getStudent_id());
            List<StudentCourse> studentCourses=assignMultipleCoursesToStudentDTO.getCourse_id().stream().map(course_id -> {
                Course course = courseService.getCourseById(course_id);
                return new StudentCourse(student, course, 0);
            }).toList();
            studentCourseRepository.saveAll(studentCourses);
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new Exception("Failed to assign courses to student");
        }
        return null;
    }
}

