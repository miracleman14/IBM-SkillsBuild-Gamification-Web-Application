package org.example.ibmskillsbuildapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserLearningPathTest {

    private UserLearningPath userLearningPath;

    @BeforeEach
    public void setup() {
        userLearningPath = new UserLearningPath();
    }

    @Test
    void testId() {
        Long id = 1L;
        userLearningPath.setId(id);
        assertEquals(id, userLearningPath.getId());
    }

    @Test
    void testUser() {
        User user = new User();
        userLearningPath.setUser(user);
        assertEquals(user, userLearningPath.getUser());
    }

    @Test
    void testLearningPath() {
        LearningPath learningPath = new LearningPath();
        userLearningPath.setLearningPath(learningPath);
        assertEquals(learningPath, userLearningPath.getLearningPath());
    }

    @Test
    void testStatus() {
        LearningStatus status = LearningStatus.COMPLETED;
        userLearningPath.setStatus(status);
        assertEquals(status, userLearningPath.getStatus());
    }

    @Test
    void testConstructor() {
        User user = new User();
        LearningPath learningPath = new LearningPath();
        LearningStatus status = LearningStatus.COMPLETED;
        UserLearningPath userLearningPath = new UserLearningPath(user, learningPath, status);
        assertNotNull(userLearningPath);
        assertEquals(user, userLearningPath.getUser());
        assertEquals(learningPath, userLearningPath.getLearningPath());
        assertEquals(status, userLearningPath.getStatus());
    }
}