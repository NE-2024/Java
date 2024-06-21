package rw.ac.rca.studentCourse.v1.controllers;


import rw.ac.rca.studentCourse.v1.dto.requests.CreateUserDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.UpdateRoleDTO;
import rw.ac.rca.studentCourse.v1.dto.requests.UserLoginDTO;
import rw.ac.rca.studentCourse.v1.exceptions.LoginFailedException;
import rw.ac.rca.studentCourse.v1.payload.ApiResponse;
import rw.ac.rca.studentCourse.v1.serviceImpls.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final CreateUserDTO createUserDTO;

    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ApiResponse> getAllUser() throws Exception{
        return userService.getAllUsers();
    }
    @GetMapping("id/{user_id}")
    @PreAuthorize("#user_id ==  authentication.principal.grantedAuthorities[0].userId or hasAuthority('Admin')")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable UUID user_id) throws Exception{
        return userService.getUserById(user_id);
    }
    @PutMapping("update/{user_id}")
    @PreAuthorize("#user_id ==  authentication.principal.grantedAuthorities[0].userId or hasAuthority('Admin')")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable UUID user_id , @RequestBody CreateUserDTO createUserDTO) throws Exception{
        return userService.updateUser(user_id , createUserDTO);
    }
    @DeleteMapping("delete/{user_id}")
    @PreAuthorize("#user_id ==  authentication.principal.grantedAuthorities[0].userId or hasAuthority('Admin')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID user_id) throws Exception{
        return userService.deleteUser(user_id);
    }

    // Api endpoints which are not authenticated
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserDTO createUserDTO) throws Exception{
        return userService.createUser(createUserDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) throws BadCredentialsException , LoginFailedException {
        return userService.authenticateUser(userLoginDTO);
    }

    @PutMapping("/role")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<ApiResponse> updateUserRole(@RequestBody UpdateRoleDTO updateRoleDTO) throws Exception{
        return userService.updateUserRole(updateRoleDTO);
    }

    @PutMapping("/changePass/{user_id}")
    public ResponseEntity<ApiResponse> updatePassword(@PathVariable UUID user_id , @RequestBody String newPassword) throws Exception{
        return userService.updatePassword( user_id , newPassword);
    }

    public ResponseEntity<String> uploadPhoto(){
        return ResponseEntity.ok().body("Function to upload a picture");
    }
}
