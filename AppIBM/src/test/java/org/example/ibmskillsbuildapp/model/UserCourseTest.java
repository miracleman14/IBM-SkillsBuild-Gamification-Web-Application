package org.example.ibmskillsbuildapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserCourseTest {

    private UserCourse userCourse;

    @BeforeEach
    public void setup() {
        userCourse = new UserCourse();
    }

    @Test
    void testId() {
        Long id = 1L;
        userCourse.setId(id);
        assertEquals(id, userCourse.getId());
    }

    @Test
    void testUser() {
        User user = new User();
        userCourse.setUser(user);
        assertEquals(user, userCourse.getUser());
    }

    @Test
    void testCourse() {
        Course course = new Course();
        userCourse.setCourse(course);
        assertEquals(course, userCourse.getCourse());
    }

    @Test
    void testStatus() {
        LearningStatus status = LearningStatus.COMPLETED;
        userCourse.setStatus(status);
        assertEquals(status, userCourse.getStatus());
    }

    @Test
    void testStartDate() {
        Date startDate = new Date();
        userCourse.setStartDate(startDate);
        assertEquals(startDate, userCourse.getStartDate());
    }

    @Test
    void testCompletionDate() {
        Date completionDate = new Date();
        userCourse.setCompletionDate(completionDate);
        assertEquals(completionDate, userCourse.getCompletionDate());
    }

    @Test
    void testRating() {
        Integer rating = 5;
        userCourse.setRating(rating);
        assertEquals(rating, userCourse.getRating());
    }

    @Test
    void testConstructor() {
        User user = new User();
        Course course = new Course();
        LearningStatus status = LearningStatus.COMPLETED;
        UserCourse userCourse = new UserCourse(user, course, status);
        assertNotNull(userCourse);
        assertEquals(user, userCourse.getUser());
        assertEquals(course, userCourse.getCourse());
        assertEquals(status, userCourse.getStatus());
    }
}