package org.example.ibmskillsbuildapp.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.UserRolesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserRolesServiceTest {

    @Mock
    private UserRolesRepository userRolesRepository;

    @InjectMocks
    private UserRolesService userRolesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsEmpty() {
        when(userRolesRepository.count()).thenReturn(0L);
        assertTrue(userRolesService.isEmpty());

        when(userRolesRepository.count()).thenReturn(1L);
        assertFalse(userRolesService.isEmpty());
    }

    @Test
    void testCreateRoles() {
        userRolesService.createRoles();

        verify(userRolesRepository, times(2)).save(any(UserRoles.class));
    }
}