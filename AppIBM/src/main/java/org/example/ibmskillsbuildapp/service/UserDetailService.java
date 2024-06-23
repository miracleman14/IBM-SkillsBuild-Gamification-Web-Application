package org.example.ibmskillsbuildapp.service;

import java.util.ArrayList;
import java.util.List;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user details and authentication. Implements the Spring Security
 * `UserDetailsService` interface.
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    /**
     * Loads user details by username.
     *
     * @param username The username to look up.
     * @return UserDetails object containing user information.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        List<GrantedAuthority> loginAuthorities = new ArrayList<>();
        for (UserRoles userRoles : user.getUserRoles()) {
            loginAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRoles.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
            user.getPassword(), true, true, true, true, loginAuthorities);
    }
}
