package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {
    Role findRoleByName(String name);
}
