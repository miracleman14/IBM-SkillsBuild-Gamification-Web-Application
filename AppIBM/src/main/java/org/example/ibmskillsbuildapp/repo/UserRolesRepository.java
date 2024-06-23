package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.UserRoles;
import org.springframework.data.repository.CrudRepository;

public interface UserRolesRepository extends CrudRepository<UserRoles, String> {

    UserRoles findByRoleName(String roleName);
}
