package rw.ac.rca.studentCourse.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.studentCourse.v1.dto.requests.CreateCourseDTO;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;
import rw.ac.rca.studentCourse.v1.services.CourseService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService  courseService;
    private final CreateCourseDTO createCourseDTO;
    // create course
   @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCourse(@RequestBody CreateCourseDTO createCourseDTO) throws Exception{
        return courseService.createCourse(createCourseDTO);
    }
    // get course by id
    @GetMapping("/get_course/{course_id}")
    public ResponseEntity<ApiResponse> getCourseById(@PathVariable UUID course_id) throws Exception{
        return courseService.getCourseById(course_id);
    }
    // get all courses
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse> getAllCourses() throws Exception{
        return courseService.getAllCourses();
    }
    // delete course
    @DeleteMapping("/delete_course/{course_id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable UUID course_id) throws Exception{
        return courseService.deleteCourse(course_id);
    }
    // update course
    @PutMapping("/update_course/{course_id}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable UUID course_id, @RequestBody CreateCourseDTO createCourseDTO) throws Exception{
        return courseService.updateCourse(course_id, createCourseDTO);
    }

}
