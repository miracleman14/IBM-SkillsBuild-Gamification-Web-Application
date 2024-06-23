package org.example.ibmskillsbuildapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseViewTest {

    private CourseView courseView;

    @BeforeEach
    public void setup() {
        courseView = new CourseView(1L, "pathName", "courseName", "description",
            LearningStatus.STARTED, "url");
    }

    @Test
    void testPathName() {
        String pathName = "Test Path";
        courseView.setPathName(pathName);
        assertEquals(pathName, courseView.getPathName());
    }

    @Test
    void testCourseName() {
        String courseName = "Test Course";
        courseView.setCourseName(courseName);
        assertEquals(courseName, courseView.getCourseName());
    }

    @Test
    void testStatus() {
        LearningStatus status = LearningStatus.COMPLETED;
        courseView.setStatus(status);
        assertEquals(status, courseView.getStatus());
    }

    @Test
    void testUrl() {
        String url = "http://testcourse.com";
        courseView.setUrl(url);
        assertEquals(url, courseView.getUrl());
    }

    @Test
    void testConstructor() {
        Long courseId = 1L;
        String pathName = "Test Path";
        String courseName = "Test Course";
        String description = "This is a test course";
        LearningStatus status = LearningStatus.COMPLETED;
        String url = "http://testcourse.com";
        CourseView courseView = new CourseView(courseId, pathName, courseName, description, status,
            url);
        assertNotNull(courseView);
        assertEquals(pathName, courseView.getPathName());
        assertEquals(courseName, courseView.getCourseName());
        assertEquals(status, courseView.getStatus());
        assertEquals(url, courseView.getUrl());
    }
}