package org.example.ibmskillsbuildapp.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.CourseView;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.service.CourseViewService;
import org.example.ibmskillsbuildapp.service.UserCourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class DashboardControllerTest {

    @InjectMocks
    private DashboardController dashboardController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseViewService courseViewService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserCourseService userCourseService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dashboardController).build();

        // Create a UserDetails object using Spring Security's User class
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(
            "testUser").password("password").roles("USER").build();

        // Set up the SecurityContext with UserDetails as the principal
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(
            new UsernamePasswordAuthenticationToken(userDetails, "password"));
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @WithMockUser(username = "testUser")
    void testDashboard() throws Exception {
        User user = new User();
        user.setUserName("testUser");

        CourseView courseView = new CourseView(1L, "pathName", "courseName", "description",
            LearningStatus.STARTED, "url");

        when(userRepository.findByUserName(anyString())).thenReturn(user);
        when(courseViewService.getAllCourseViews(user)).thenReturn(List.of(courseView));

        mockMvc.perform(get("/viewDashboard"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("courseViews"))
            .andExpect(model().attributeExists("user"));
    }

    @Test
    void testEnroll() throws Exception {
        User user = new User();
        user.setId(1L);
        Course course = new Course();
        course.setId(1L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        mockMvc.perform(post("/enroll")
                .param("userId", user.getId().toString())
                .param("courseId", course.getId().toString()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/viewDashboard"));

        verify(userCourseService, times(1)).enroll(user, course);
    }

    @Test
    void testComplete() throws Exception {
        User user = new User();
        user.setId(1L);
        Course course = new Course();
        course.setId(1L);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        mockMvc.perform(post("/complete")
                .param("userId", user.getId().toString())
                .param("courseId", course.getId().toString()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/viewDashboard"));

        verify(userCourseService, times(1)).complete(user, course);
    }
}