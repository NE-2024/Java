package rw.ac.rca.studentCourse.v1.repositories;

import rw.ac.rca.studentCourse.v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
}
