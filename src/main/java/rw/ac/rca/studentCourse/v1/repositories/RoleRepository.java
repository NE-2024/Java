package rw.ac.rca.studentCourse.v1.repositories;

import org.springframework.stereotype.Repository;
import rw.ac.rca.studentCourse.v1.enums.Roles;
import rw.ac.rca.studentCourse.v1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {
    Optional<Role> findRoleByRoleName(Roles roleName);

}
