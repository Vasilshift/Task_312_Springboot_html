package web.service;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> updateRoles(String[] roleView);

    void save(Role roleAdmin);

    List<Role> findAllRoles();

    Role findRoleByName(String name);

}
