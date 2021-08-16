package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import web.model.Role;
import web.model.User;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long>  {
    Role findRoleByName(String name);
}
