package rw.ac.rca.studentCourse.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.studentCourse.v1.models.Course;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
