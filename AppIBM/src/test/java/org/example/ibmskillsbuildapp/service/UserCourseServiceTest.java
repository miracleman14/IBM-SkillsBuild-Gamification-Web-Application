package org.example.ibmskillsbuildapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserCourseServiceTest {

    @InjectMocks
    private UserCourseService userCourseService;

    @Mock
    private UserCourseRepository userCourseRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnroll() {
        User user = new User();
        Course course = new Course();
        UserCourse userCourse = new UserCourse();
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setStatus(LearningStatus.STARTED);

        when(userCourseRepository.findByUserAndCourse(user, course)).thenReturn(userCourse);

        userCourseService.enroll(user, course);

        verify(userCourseRepository, times(1)).save(userCourse);
    }

    @Test
    void testComplete() {
        User user = new User();
        user.setScore(0);
        Course course = new Course();
        UserCourse userCourse = new UserCourse();
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setStatus(LearningStatus.COMPLETED);

        when(userCourseRepository.findByUserAndCourse(user, course)).thenReturn(userCourse);

        userCourseService.complete(user, course);

        verify(userCourseRepository, times(1)).save(userCourse);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testChangeCourseStatus() {
        User user = new User();
        Course course = new Course();
        UserCourse userCourse = new UserCourse();
        userCourse.setUser(user);
        userCourse.setCourse(course);
        userCourse.setStatus(LearningStatus.STARTED);

        when(userCourseRepository.findByUserAndCourse(user, course)).thenReturn(userCourse);

        userCourseService.changeCourseStatus(user, course, LearningStatus.COMPLETED, new Date());

        verify(userCourseRepository, times(1)).save(userCourse);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetCoursesCount() {
        List<UserCourse> userCourses = new ArrayList<>();
        UserCourse userCourse = new UserCourse();
        Course course = new Course();
        userCourse.setCourse(course);
        userCourses.add(userCourse);

        when(userCourseRepository.findAll()).thenReturn(userCourses);

        Predicate<UserCourse> predicate = userCoursePredicate -> userCoursePredicate.getCourse()
            != null;
        Map<Course, Long> result = userCourseService.getCoursesCount(predicate);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetStartedCoursesCount() {
        List<UserCourse> userCourses = new ArrayList<>();
        UserCourse userCourse = new UserCourse();
        userCourse.setStatus(LearningStatus.STARTED);
        Course course = new Course();
        userCourse.setCourse(course);
        userCourses.add(userCourse);

        when(userCourseRepository.findAll()).thenReturn(userCourses);

        Predicate<UserCourse> predicate = userCoursePredicate -> userCoursePredicate.getCourse()
            != null;
        Map<Course, Long> result = userCourseService.getCoursesCount(predicate);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetCompletedCoursesCount() {
        List<UserCourse> userCourses = new ArrayList<>();
        UserCourse userCourse = new UserCourse();
        userCourse.setStatus(LearningStatus.COMPLETED);
        Course course = new Course();
        userCourse.setCourse(course);
        userCourses.add(userCourse);

        when(userCourseRepository.findAll()).thenReturn(userCourses);

        Predicate<UserCourse> predicate = userCoursePredicate -> userCoursePredicate.getCourse()
            != null;
        Map<Course, Long> result = userCourseService.getCoursesCount(predicate);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetRatedCoursesCount() {
        List<UserCourse> userCourses = new ArrayList<>();
        UserCourse userCourse = new UserCourse();
        userCourse.setRating(5);
        Course course = new Course();
        userCourse.setCourse(course);
        userCourses.add(userCourse);

        when(userCourseRepository.findAll()).thenReturn(userCourses);

        Map<Course, Long> result = userCourseService.getRatedCoursesCount();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetEnrollmentsLast30DaysCount() {
        List<UserCourse> userCourses = new ArrayList<>();
        UserCourse userCourse = new UserCourse();
        userCourse.setStartDate(new Date());
        Course course = new Course();
        userCourse.setCourse(course);
        userCourses.add(userCourse);

        when(userCourseRepository.findAll()).thenReturn(userCourses);

        Map<Course, Long> result = userCourseService.getEnrollmentsLast30DaysCount();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetAverageRatings() {
        List<UserCourse> userCourses = new ArrayList<>();
        UserCourse userCourse = new UserCourse();
        userCourse.setRating(5);
        Course course = new Course();
        userCourse.setCourse(course);
        userCourses.add(userCourse);

        when(userCourseRepository.findAll()).thenReturn(userCourses);

        Map<Course, Double> result = userCourseService.getAverageRatings();

        assertNotNull(result);
        assertEquals(5.0, result.values().stream().findFirst().get());
    }

    @Test
    void testGetAnalyticsData() {
        List<UserCourse> userCourses = new ArrayList<>();
        UserCourse userCourse = new UserCourse();
        userCourse.setRating(5);
        userCourse.setStatus(LearningStatus.STARTED);
        userCourse.setStartDate(new Date());
        Course course = new Course();
        userCourse.setCourse(course);
        userCourses.add(userCourse);

        when(userCourseRepository.findAll()).thenReturn(userCourses);

        List<Map<String, Object>> result = userCourseService.getAnalyticsData();

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
