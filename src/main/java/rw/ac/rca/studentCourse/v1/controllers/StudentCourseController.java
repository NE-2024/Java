package rw.ac.rca.studentCourse.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.studentCourse.v1.dto.requests.AssignMultipleCoursesToStudentDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateStudentCourseDTO;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;
import rw.ac.rca.studentCourse.v1.services.StudentCourseService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/studentcourses")
@RequiredArgsConstructor
public class StudentCourseController {

    public final StudentCourseService studentCourseService;
    // create student course
    @PostMapping("/create/{student_id}/{course_id}")
    public ResponseEntity<ApiResponse> createStudentCourse(@PathVariable UUID student_id, @PathVariable UUID course_id, @RequestBody CreateStudentCourseDTO createStudentCourseDTO) throws Exception{
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student course created successfully!", studentCourseService.registerStudentToCourse(student_id, course_id, createStudentCourseDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }
   // get studentCourse by Id
    @GetMapping("/get_studentCourse/{studentCourse_id}")
    public ResponseEntity<ApiResponse> getStudentCourseById(@PathVariable UUID studentCourse_id) throws Exception{
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student course retrieved successfully!", studentCourseService.getStudentCourseById(studentCourse_id)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    // get all studentCourses
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getAllStudentCourses() throws Exception{
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student courses retrieved successfully!", studentCourseService.getAllStudentCourses()));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    // getting all students paginated and sorted
    @GetMapping("all/paginated")
    ResponseEntity<ApiResponse> getAllPaginated(@RequestParam("size") int size, @RequestParam("page") int page){
        Sort sort = Sort.by(Sort.Order.desc("studentMarks"), Sort.Order.asc("studentNumber"));

        Pageable pageable = PageRequest.of(page, size, sort);
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Data retrieved successfully!", studentCourseService.getAllStudentCoursesPaginated(pageable)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // delete studentCourse
    @DeleteMapping("/delete_studentCourse/{studentCourse_id}")
    public ResponseEntity<ApiResponse> deleteStudentCourse(@PathVariable UUID studentCourse_id) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student course deleted successfully!", studentCourseService.deleteStudentCourse(studentCourse_id)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // update studentCourse
    @PutMapping("/update_studentCourse/{studentCourse_id}")
    public ResponseEntity<ApiResponse> updateStudentCourse(@PathVariable UUID studentCourse_id, @RequestBody CreateStudentCourseDTO createStudentCourseDTO) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Student course updated successfully!", studentCourseService.updateStudentCourse(studentCourse_id, createStudentCourseDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // assign multiple courses to student
    @PostMapping("/assign_courses")
    public ResponseEntity<ApiResponse> assignCoursesToStudent(@RequestBody AssignMultipleCoursesToStudentDTO assignMultipleCoursesToStudentDTO) throws Exception{
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Courses assigned to student successfully!", studentCourseService.assignCoursesToStudent(assignMultipleCoursesToStudentDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
