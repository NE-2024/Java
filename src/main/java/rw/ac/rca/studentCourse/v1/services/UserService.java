package rw.ac.rca.studentCourse.v1.services;


import rw.ac.rca.studentCourse.v1.dto.requests.CreateUserDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.UpdateRoleDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.UserLoginDTO;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    public ResponseEntity<ApiResponse> getAllUsers() throws Exception;
    public ResponseEntity<ApiResponse> getUserById(UUID user_id) throws Exception;
    public ResponseEntity<ApiResponse> createUser(CreateUserDTO createUserDTO) throws Exception;
    public ResponseEntity<ApiResponse> updateUser(UUID user_id , CreateUserDTO createUserDTO) throws Exception;
    ResponseEntity<ApiResponse> deleteUser(UUID user_id) throws Exception;
    ResponseEntity<ApiResponse> authenticateUser(UserLoginDTO userLoginDTO) throws Exception;
    ResponseEntity<ApiResponse> updateUserRole(UpdateRoleDTO updateRoleDTO) throws Exception;
     ResponseEntity<ApiResponse> updatePassword( UUID user_id , String newPassword) throws Exception;
}
