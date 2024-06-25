package rw.ac.rca.studentCourse.v1.services;

import rw.ac.rca.studentCourse.v1.enums.Roles;
import rw.ac.rca.studentCourse.v1.models.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    public List<Role> getAllRoles();
    public Role getRoleById(Long roleId);
    public  Role getRoleByName(Roles roleName);
    public void createRole(Roles roleName);
    public Role deleteRole(Long roleId);
    public boolean isRolePresent(Roles roleName);
}
