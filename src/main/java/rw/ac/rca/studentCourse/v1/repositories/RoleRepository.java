package rw.ac.rca.studentCourse.v1.repositories;

import org.springframework.stereotype.Repository;
import rw.ac.rca.studentCourse.v1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {
}
