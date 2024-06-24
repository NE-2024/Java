package rw.ac.rca.studentCourse.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentDTO;
import rw.ac.rca.studentCourse.v1.dto.responses.StudentDTOMapper;
import rw.ac.rca.studentCourse.v1.exceptions.ResourceNotFoundException;
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
    public Student saveStudent( CreateStudentDTO createStudentDTO) throws Exception {
        Student student = new Student (
                createStudentDTO.getFirstName(),
                createStudentDTO.getLastName(),
                createStudentDTO.getEmail(),
                createStudentDTO.getPhoneNumber(),
                createStudentDTO.getStudentNumber(),
                createStudentDTO.getSchoolName()
        );
        try {
           return studentRepository.save(student);
        } catch (HttpServerErrorException.InternalServerError e){
            throw new Exception("Failed to create the student");
        }

    }

    @Override
    public Student getStudentById(UUID student_id) throws Exception {
               return  studentRepository.findById(student_id).orElseThrow(()-> new ResourceNotFoundException("Student", "id", student_id.toString()));
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
        try {
            return  studentRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to get students");
        }
    }

    @Override
    public String deleteStudent(UUID student_id) throws Exception {
        if(studentRepository.existsById(student_id)){
            try {
                studentRepository.deleteById(student_id);
                return "Student deleted successfully";
            } catch (Exception e){
                throw new Exception("Failed to delete the student");
            }
        } else {
            return "Student not found";
        }
    }

    @Override
    public Student updateStudent(UUID student_id, CreateStudentDTO createStudentDTO) throws Exception {
        if (studentRepository.existsById(student_id)) {

            Student student = new Student (createStudentDTO.getFirstName(),
                createStudentDTO.getLastName(),
                createStudentDTO.getEmail(),
                createStudentDTO.getPhoneNumber(),
                createStudentDTO.getSchoolName(),
                createStudentDTO.getStudentNumber()
                );
            try {
                 return  studentRepository.save(student);
            }  catch (HttpServerErrorException.InternalServerError e){
                throw new Exception("Failed to create the student");
            }
        } else {
            throw new Exception("Student not found");
        }
    }
}
