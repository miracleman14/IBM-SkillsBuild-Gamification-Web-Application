package org.example.ibmskillsbuildapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {

    private Course course;

    @BeforeEach
    void setup() {
        course = new Course();
    }

    @Test
    void testId() {
        Long id = 1L;
        course.setId(id);
        assertEquals(id, course.getId());
    }

    @Test
    void testCourseName() {
        String courseName = "Test Course";
        course.setCourseName(courseName);
        assertEquals(courseName, course.getCourseName());
    }

    @Test
    void testUrl() {
        String url = "http://testcourse.com";
        course.setUrl(url);
        assertEquals(url, course.getUrl());
    }

    @Test
    void testLearningPath() {
        LearningPath learningPath = new LearningPath();
        course.setLearningPath(learningPath);
        assertEquals(learningPath, course.getLearningPath());
    }

    @Test
    void testUserCourse() {
        List<UserCourse> userCourse = new ArrayList<>();
        course.setUserCourse(userCourse);
        assertEquals(userCourse, course.getUserCourse());
    }

    @Test
    void testConstructor() {
        String courseName = "Test Course";
        String description = "This is a test course";
        String url = "http://testcourse.com";
        Course course = new Course(courseName, description, url);
        assertNotNull(course);
        assertEquals(courseName, course.getCourseName());
        assertEquals(url, course.getUrl());
    }
}