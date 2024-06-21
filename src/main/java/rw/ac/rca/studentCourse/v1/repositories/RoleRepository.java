package rw.ac.rca.studentCourse.v1.repositories;

import rw.ac.rca.studentCourse.v1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role , Long> {
}
