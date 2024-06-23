package rw.ac.rca.studentCourse.v1.controllers;

import lombok.RequiredArgsConstructor;
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
        return studentCourseService.registerStudentToCourse(student_id, course_id, createStudentCourseDTO);
    }
   // get studentCourse by Id
    @GetMapping("/get_studentCourse/{studentCourse_id}")
    public ResponseEntity<ApiResponse> getStudentCourseById(@PathVariable UUID studentCourse_id) throws Exception{
        return studentCourseService.getStudentCourseById(studentCourse_id);
    }
    // get all studentCourses
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse> getAllStudentCourses() throws Exception{
        return studentCourseService.getAllStudentCourses();
    }
    // delete studentCourse
    @DeleteMapping("/delete_studentCourse/{studentCourse_id}")
    public ResponseEntity<ApiResponse> deleteStudentCourse(@PathVariable UUID studentCourse_id) throws Exception{
        return studentCourseService.deleteStudentCourse(studentCourse_id);
    }
    // update studentCourse
    @PutMapping("/update_studentCourse/{studentCourse_id}")
    public ResponseEntity<ApiResponse> updateStudentCourse(@PathVariable UUID studentCourse_id, @RequestBody CreateStudentCourseDTO createStudentCourseDTO) throws Exception{
        return studentCourseService.updateStudentCourse(studentCourse_id, createStudentCourseDTO);
    }
    // assign multiple courses to student
    @PostMapping("/assign_courses")
    public ResponseEntity<ApiResponse> assignCoursesToStudent(@RequestBody AssignMultipleCoursesToStudentDTO assignMultipleCoursesToStudentDTO) throws Exception{
        return studentCourseService.assignCoursesToStudent(assignMultipleCoursesToStudentDTO);
    }

}
