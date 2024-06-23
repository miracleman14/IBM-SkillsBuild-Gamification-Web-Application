package org.example.ibmskillsbuildapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class UserRoleTest {

    @InjectMocks
    private UserRoles userRoles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetRoleName() {

        userRoles.setRoleName("ADMIN");

        String roleName = userRoles.getRoleName();

        assertEquals("ADMIN", roleName);
    }

    @Test
    void testSetRoleName() {

        userRoles.setRoleName("USER");

        String roleName = userRoles.getRoleName();

        assertEquals("USER", roleName);
    }

}
