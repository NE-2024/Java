package rw.ac.rca.studentCourse.v1.serviceImpls;

import com.cloudinary.api.exceptions.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.rca.studentCourse.v1.enums.Roles;
import rw.ac.rca.studentCourse.v1.exceptions.BadRequestAlertException;
import rw.ac.rca.studentCourse.v1.exceptions.InternalServerErrorException;
import rw.ac.rca.studentCourse.v1.exceptions.NotFoundException;
import rw.ac.rca.studentCourse.v1.exceptions.ResourceNotFoundException;
import rw.ac.rca.studentCourse.v1.models.Role;
import rw.ac.rca.studentCourse.v1.repositories.RoleRepository;
import rw.ac.rca.studentCourse.v1.services.RoleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository iRoleRepository) {
        this.roleRepository = iRoleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(()-> {
            try {
                throw new ResourceNotFoundException("The Role was not found");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Role getRoleByName(Roles roleName) {
        return roleRepository.findRoleByRoleName(roleName).orElseThrow(()->{
            try {
                throw new ResourceNotFoundException("The Role was not found");
            } catch (ResourceNotFoundException e){
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void createRole(Roles roleName) {
        Optional<Role> optionalRole = roleRepository.findRoleByRoleName(roleName);
        if(optionalRole.isPresent()){
            throw new BadRequestAlertException("The role already exists");
        }else{
            Role role = new Role(
                    roleName
            );
            try {
                roleRepository.save(role);
            }catch (Exception e){
                e.printStackTrace();
                throw new InternalServerErrorException(e.getMessage());
            }
        }
    }

    @Override
    public Role deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(()-> {throw new NotFoundException("The role is not present");
        });
        try {
            roleRepository.deleteById(roleId);
            return role;
        }catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public boolean isRolePresent(Roles roleName) {
        try {
            return roleRepository.findRoleByRoleName(roleName).isPresent();
        }catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
