package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.repository.RoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Set<Role> updateRoles(String[] roleView) {
        Set<Role> roleList = new HashSet<>();
        for (String role : roleView) {
            if (role.equals("ROLE_ADMIN")) {
                roleList.add(roleRepository.findRoleByName("ROLE_ADMIN"));
            } else if (role.equals("ROLE_USER")) {
                roleList.add(roleRepository.findRoleByName("ROLE_USER"));
            }
        }
        return roleList;
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }


    public void save(Role roleAdmin) {
        roleRepository.save(roleAdmin);
    }
}
