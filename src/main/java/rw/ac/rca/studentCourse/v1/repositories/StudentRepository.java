package rw.ac.rca.studentCourse.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.studentCourse.v1.models.Student;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID>{
}
