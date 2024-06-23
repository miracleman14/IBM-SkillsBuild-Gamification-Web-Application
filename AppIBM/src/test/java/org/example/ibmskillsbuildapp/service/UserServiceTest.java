package org.example.ibmskillsbuildapp.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.repo.UserRolesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest {

    @Mock
    private UserRolesRepository userRolesRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LearningPathRepository learningPathRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(learningPathRepository.findAll()).thenReturn(new ArrayList<>());
        when(courseRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    void testIsEmpty() {
        when(userRepository.count()).thenReturn(0L);
        assertTrue(userService.isEmpty());

        when(userRepository.count()).thenReturn(1L);
        assertFalse(userService.isEmpty());
    }

    @Test
    void testCreateUsers() {
        when(userRolesRepository.findByRoleName(anyString())).thenReturn(new UserRoles());
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        userService.createUsers();

        verify(userRepository, times(4)).save(any(User.class));
    }

    @Test
    void testCreateDemoData() {
        when(userRolesRepository.findByRoleName(anyString())).thenReturn(new UserRoles());
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        userService.createDemoData();

        verify(userRepository, times(25)).save(any(User.class));
    }
}
