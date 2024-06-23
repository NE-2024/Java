package rw.ac.rca.studentCourse.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.studentCourse.v1.models.StudentCourse;

import java.util.UUID;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, UUID>{
}
