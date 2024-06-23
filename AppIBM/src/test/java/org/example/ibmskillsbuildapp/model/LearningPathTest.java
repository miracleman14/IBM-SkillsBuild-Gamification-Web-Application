package org.example.ibmskillsbuildapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LearningPathTest {

    private LearningPath learningPath;

    @BeforeEach
    public void setup() {
        learningPath = new LearningPath();
    }

    @Test
    void testId() {
        Long id = 1L;
        learningPath.setId(id);
        assertEquals(id, learningPath.getId());
    }

    @Test
    void testPathName() {
        String pathName = "Test Path";
        learningPath.setPathName(pathName);
        assertEquals(pathName, learningPath.getPathName());
    }

    @Test
    void testCourses() {
        List<Course> courses = new ArrayList<>();
        learningPath.setCourses(courses);
        assertEquals(courses, learningPath.getCourses());
    }

    @Test
    void testUserLearningPaths() {
        List<UserLearningPath> userLearningPaths = new ArrayList<>();
        learningPath.setUserLearningPaths(userLearningPaths);
        assertEquals(userLearningPaths, learningPath.getUserLearningPaths());
    }

    @Test
    void testConstructor() {
        String pathName = "Test Path";
        LearningPath learningPath = new LearningPath(pathName);
        assertNotNull(learningPath);
        assertEquals(pathName, learningPath.getPathName());
    }
}