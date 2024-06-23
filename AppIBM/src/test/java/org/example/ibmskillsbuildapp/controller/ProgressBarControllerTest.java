package org.example.ibmskillsbuildapp.controller;

import static org.example.ibmskillsbuildapp.model.LearningStatus.COMPLETED;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
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
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@Controller
public class ProgressBarControllerTest {

    @InjectMocks
    private ProgressBarController progressBarController;
    @Mock
    private LearningPathRepository learningPathRepository;

    @Mock
    private UserCourseRepository userCourseRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(progressBarController).build();
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
    void testProgressBar() {
        User user = new User();
        user.setUserName("testUser");
        // Arrange
        LearningPath aiPath = new LearningPath("Artificial Intelligence");
        LearningPath ccPath = new LearningPath("Cloud Computing");
        LearningPath dtPath = new LearningPath("Design Thinking");

        Iterable<LearningPath> learningPaths = learningPathRepository.findAll();
        List<Course> AI = new ArrayList<>();
        List<Course> CC = new ArrayList<>();
        List<Course> DT = new ArrayList<>();
        for (LearningPath path : learningPaths) {
            for (Course course : path.getCourses()) {
                if (course.getLearningPath().getPathName().equals("Artificial Intelligence")) {
                    AI.add(course);
                }
                if (course.getLearningPath().getPathName().equals("Cloud Computing")) {
                    CC.add(course);
                }
                if (course.getLearningPath().getPathName().equals("Design Thinking")) {
                    DT.add(course);
                }
            }
        }
        List<UserCourse> completedCourses = new ArrayList<>();

        when(learningPathRepository.findAll()).thenReturn(List.of(aiPath));
        when(learningPathRepository.findAll()).thenReturn(List.of(ccPath));
        when(learningPathRepository.findAll()).thenReturn(List.of(dtPath));
        when(userCourseRepository.findAllByUserAndStatus(user, COMPLETED)).thenReturn(
            completedCourses);
        // Act
        int AICount = 0;
        int CCcount = 0;
        int DTCount = 0;
        for (UserCourse userCourse : completedCourses) {
            if (userCourse.getCourse().getLearningPath().getPathName()
                .equals("Artificial Intelligence")) {
                AICount += 1;
            }

            if (userCourse.getCourse().getLearningPath().getPathName().equals("Cloud Computing")) {
                CCcount += 1;
            }

            if (userCourse.getCourse().getLearningPath().getPathName().equals("Design Thinking")) {
                DTCount += 1;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        double AIPercent = !AI.isEmpty() ? (double) AICount / AI.size() * 100 : 0;
        String AIPercentage = df.format(AIPercent);
        double CCPercent = !CC.isEmpty() ? (double) CCcount / CC.size() * 100 : 0;
        String CCPercentage = df.format(CCPercent);
        double DTPercent = !DT.isEmpty() ? (double) DTCount / DT.size() * 100 : 0;
        String DTPercentage = df.format(DTPercent);

        // Assert
        assertNotNull(AIPercentage);
        assertNotNull(CCPercentage);
        assertNotNull(DTPercentage);

    }


}
