package org.example.ibmskillsbuildapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.ibmskillsbuildapp.service.CourseService;
import org.example.ibmskillsbuildapp.service.LearningPathService;
import org.example.ibmskillsbuildapp.service.UserRolesService;
import org.example.ibmskillsbuildapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LoadDatabaseTest {

    @Mock
    private UserRolesService userRolesService;
    @Mock
    private UserService userService;
    @Mock
    private CourseService courseService;
    @Mock
    private LearningPathService learningPathService;
    private LoadDatabase loadDatabase;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        loadDatabase = new LoadDatabase(userRolesService, userService, courseService,
            learningPathService);
    }

    @Test
    void testInitDatabase() throws Exception {
        when(learningPathService.isEmpty()).thenReturn(true);
        when(courseService.isEmpty()).thenReturn(true);
        when(userService.isEmpty()).thenReturn(true);

        loadDatabase.initDatabase().run();

        verify(learningPathService, times(1)).createLearningPaths();
        verify(courseService, times(1)).createCourses();
        verify(userService, times(1)).createUsers();
    }
}
