package org.example.ibmskillsbuildapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.CourseView;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CourseViewServiceTest {

    @Mock
    private LearningPathRepository learningPathRepository;

    @Mock
    private UserCourseRepository userCourseRepository;

    @InjectMocks
    private CourseViewService courseViewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCourseViews() {
        User user = new User();
        Course course = new Course();
        course.setCourseName("Course Name");
        course.setUrl("Course URL");
        LearningPath learningPath = new LearningPath();
        learningPath.setPathName("Path Name");
        learningPath.setCourses(List.of(course));
        UserCourse userCourse = new UserCourse();
        userCourse.setStatus(LearningStatus.COMPLETED);
        userCourse.setUser(user);
        userCourse.setCourse(course);

        when(learningPathRepository.findAll()).thenReturn(List.of(learningPath));
        when(userCourseRepository.findByUserAndCourse(user, course)).thenReturn(userCourse);

        List<CourseView> courseViews = courseViewService.getAllCourseViews(user);

        assertEquals(1, courseViews.size());
        CourseView courseView = courseViews.get(0);
        assertEquals("Path Name", courseView.getPathName());
        assertEquals("Course Name", courseView.getCourseName());
        assertEquals(LearningStatus.COMPLETED, courseView.getStatus());
        assertEquals("Course URL", courseView.getUrl());
    }
}