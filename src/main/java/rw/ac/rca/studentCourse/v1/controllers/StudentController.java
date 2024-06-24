package rw.ac.rca.studentCourse.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateUserDTO;
import rw.ac.rca.studentCourse.v1.models.Student;
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
    public ResponseEntity<ApiResponse> createStudent(@RequestBody CreateStudentDTO createStudentDTO) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student created successfully!", studentService.saveStudent(createStudentDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    // get student by id
    @GetMapping("/get_student/{student_id}")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable UUID student_id) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student retrieved successfully!", studentService.getStudentById(student_id)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // get all students registered
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getAllStudents(){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Students retrieved successfully!", studentService.getAllStudents()));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // get all students paginated and sorted
    @GetMapping("all/paginated")
    ResponseEntity<ApiResponse> getAllStudentsPaginated(@RequestParam("size") int size, @RequestParam("page") int page){
        Sort sort = Sort.by(Sort.Order.asc("studentNumber"));

        Pageable pageable = PageRequest.of(page, size, sort);
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Data retrieved successfully!", studentService.getAllStudentsPaginated(pageable)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // delete student
    @DeleteMapping("/delete_student/{student_id}")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable UUID student_id){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student deleted successfully!", studentService.deleteStudent(student_id)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    // update student
    @PutMapping("/update_student/{student_id}")
    public ResponseEntity<ApiResponse> updateStudent(@PathVariable UUID student_id, @RequestBody CreateStudentDTO createStudentDTO){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student updated successfully!", studentService.updateStudent(student_id, createStudentDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
