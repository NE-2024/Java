package rw.ac.rca.studentCourse.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.studentCourse.v1.models.Student;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID>{
}
