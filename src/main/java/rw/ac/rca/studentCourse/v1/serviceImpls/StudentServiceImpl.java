package rw.ac.rca.studentCourse.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentDTO;
import rw.ac.rca.studentCourse.v1.dto.responses.StudentDTOMapper;
import rw.ac.rca.studentCourse.v1.models.Student;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;
import rw.ac.rca.studentCourse.v1.repositories.StudentRepository;
import rw.ac.rca.studentCourse.v1.services.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private  final StudentRepository studentRepository;
    private  final StudentDTOMapper studentDTOMapper;


    @Override
    public ResponseEntity<ApiResponse> saveStudent( CreateStudentDTO createStudentDTO) throws Exception {
        Student student = new Student (
                createStudentDTO.getFirstName(),
                createStudentDTO.getLastName(),
                createStudentDTO.getEmail(),
                createStudentDTO.getPhoneNumber(),
                createStudentDTO.getStudentNumber(),
                createStudentDTO.getSchoolName()
        );
        try {
            studentRepository.save(student);
            return ResponseEntity.ok(new ApiResponse(true, "Student saved successfully", student));
        } catch (HttpServerErrorException.InternalServerError e){
            return ResponseEntity.status(500).body(new ApiResponse(
                    false,
                    "Failed to create the student"
            ));
        }

    }

    @Override
    public ResponseEntity<ApiResponse> getStudentById(UUID student_id) throws Exception {
        if (studentRepository.existsById(student_id)) {
            try{
                Optional<Student> student = studentRepository.findById(student_id);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully fetched the student",
                        student.map(studentDTOMapper)

                ));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to get student"));
            }

        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Student not found"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getAllStudents() throws Exception {
        try {
            List<Student> students = studentRepository.findAll();
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully fetched all students",
                    students.stream().map(studentDTOMapper)

            ));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Failed to get students"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteStudent(UUID student_id) throws Exception {
        if (studentRepository.existsById(student_id)) {
            try {
                studentRepository.deleteById(student_id);
                return ResponseEntity.ok(new ApiResponse(true, "Student deleted successfully"));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to delete student"));
            }
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Student not found"));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updateStudent(UUID student_id, CreateStudentDTO createStudentDTO) throws Exception {
        if (studentRepository.existsById(student_id)) {

            Student student = new Student (createStudentDTO.getFirstName(),
                createStudentDTO.getLastName(),
                createStudentDTO.getEmail(),
                createStudentDTO.getPhoneNumber(),
                createStudentDTO.getSchoolName(),
                createStudentDTO.getStudentNumber()
                );
            try {
                studentRepository.save(student);
                return ResponseEntity.ok(new ApiResponse(true, "Student updated successfully", student));
            } catch (Exception e) {
                return ResponseEntity.ok(new ApiResponse(false, "Failed to update student"));
            }
        } else {
            return ResponseEntity.ok(new ApiResponse(false, "Student not found"));
        }
    }
}
