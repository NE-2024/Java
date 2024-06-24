package rw.ac.rca.studentCourse.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Course created successfully!", courseService.createCourse(createCourseDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    // get course by id
    @GetMapping("/get_course/{course_id}")
    public ResponseEntity<ApiResponse> getCourseById(@PathVariable UUID course_id){
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Course retrieved successfully!", courseService.getCourseById(course_id)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    // get all courses
    @GetMapping("/get_all")
    public ResponseEntity<ApiResponse> getAllCourses() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Courses retrieved successfully!", courseService.getAllCourses()));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // get all courses paginated and sorted
    @GetMapping("all/paginated")
    ResponseEntity<ApiResponse> getAllPaginated(@RequestParam("size") int size, @RequestParam("page") int page){
        Sort sort = Sort.by(Sort.Order.asc("courseName"), Sort.Order.asc("courseCode"));

        Pageable pageable = PageRequest.of(page, size, sort);
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Data retrieved successfully!", courseService.getAllCoursesPaginated(pageable)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // delete course
    @DeleteMapping("/delete_course/{course_id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable UUID course_id) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Course deleted successfully!", courseService.deleteCourse(course_id)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }
    // update course
    @PutMapping("/update_course/{course_id}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable UUID course_id, @RequestBody CreateCourseDTO createCourseDTO){
       try {
              return ResponseEntity.ok(new ApiResponse(true, "Course updated successfully!", courseService.updateCourse(course_id, createCourseDTO)));
         } catch (Exception e) {
              return new ResponseEntity<>(new ApiResponse(false, e.getMessage(), null), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

}
