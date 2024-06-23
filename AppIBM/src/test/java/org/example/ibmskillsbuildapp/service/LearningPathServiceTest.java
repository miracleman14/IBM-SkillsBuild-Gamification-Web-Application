package org.example.ibmskillsbuildapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LearningPathServiceTest {

    @InjectMocks
    private LearningPathService learningPathService;

    @Mock
    private LearningPathRepository learningPathRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsEmpty() {
        when(learningPathRepository.count()).thenReturn(0L);
        assertTrue(learningPathService.isEmpty());

        when(learningPathRepository.count()).thenReturn(1L);
        assertFalse(learningPathService.isEmpty());
    }

    @Test
    void testCreateLearningPaths() {
        learningPathService.createLearningPaths();

        ArgumentCaptor<LearningPath> learningPathCaptor = ArgumentCaptor.forClass(
            LearningPath.class);
        verify(learningPathRepository, times(3)).save(learningPathCaptor.capture());
        List<LearningPath> capturedLearningPaths = learningPathCaptor.getAllValues();

        assertEquals(3, capturedLearningPaths.size());
        assertEquals("Artificial Intelligence", capturedLearningPaths.get(0).getPathName());
        assertEquals("Cloud Computing", capturedLearningPaths.get(1).getPathName());
        assertEquals("Design Thinking", capturedLearningPaths.get(2).getPathName());
    }
}