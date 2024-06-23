package org.example.ibmskillsbuildapp.service;

import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link UserRoles} entities. This service provides methods for common
 * operations such as checking if the repository is empty and creating roles.
 */
@Service
public class UserRolesService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    /**
     * Checks if the user roles repository is empty.
     *
     * @return `true` if the repository is empty, otherwise `false`.
     */
    public boolean isEmpty() {
        return userRolesRepository.count() == 0;
    }

    /**
     * Creates default roles (USER and ADMIN) and adds them to the repository.
     */
    public void createRoles() {
        UserRoles userRole = new UserRoles();
        userRole.setRoleName("USER");
        userRolesRepository.save(userRole);

        UserRoles adminRole = new UserRoles();
        adminRole.setRoleName("ADMIN");
        userRolesRepository.save(adminRole);
    }
}