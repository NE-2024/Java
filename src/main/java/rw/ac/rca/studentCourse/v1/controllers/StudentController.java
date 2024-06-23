package rw.ac.rca.studentCourse.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateUserDTO;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;
import rw.ac.rca.studentCourse.v1.services.StudentService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final CreateStudentDTO createStudentDTO;
    //create student
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createStudent(@RequestBody CreateStudentDTO createStudentDTO) throws Exception{
        return studentService.saveStudent(createStudentDTO);
    }
    // get student by id
    @GetMapping("/get_student/{student_id}")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable UUID student_id) throws Exception{
        return studentService.getStudentById(student_id);
    }
    // get all students registered
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse> getAllStudents() throws Exception{
        return studentService.getAllStudents();
    }
    // delete student
    @DeleteMapping("/delete_student/{student_id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable UUID student_id) throws Exception{
        return studentService.deleteStudent(student_id);
    }
    // update student
    @PutMapping("/update_student/{student_id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable UUID student_id, @RequestBody CreateStudentDTO createStudentDTO) throws Exception{
        return studentService.updateStudent(student_id, createStudentDTO);
    }

}
