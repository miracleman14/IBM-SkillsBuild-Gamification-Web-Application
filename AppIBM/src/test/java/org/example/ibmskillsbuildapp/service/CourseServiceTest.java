package org.example.ibmskillsbuildapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private LearningPathRepository learningPathRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsEmpty() {
        when(courseRepository.count()).thenReturn(0L);
        assertTrue(courseService.isEmpty());

        when(courseRepository.count()).thenReturn(1L);
        assertFalse(courseService.isEmpty());
    }

    @Test
    void testCreateCourses() {
        LearningPath path1 = new LearningPath("Artificial intelligence");
        LearningPath path2 = new LearningPath("Cloud computing");
        LearningPath path3 = new LearningPath("Design thinking");

        when(learningPathRepository.findByPathName("Artificial intelligence")).thenReturn(path1);
        when(learningPathRepository.findByPathName("Cloud computing")).thenReturn(path2);
        when(learningPathRepository.findByPathName("Design thinking")).thenReturn(path3);

        courseService.createCourses();

        ArgumentCaptor<Course> courseCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository, times(5)).save(courseCaptor.capture());
        List<Course> capturedCourses = courseCaptor.getAllValues();

        assertEquals(5, capturedCourses.size());
        assertEquals("AI Foundations: A Collaboration of ISTE and IBM",
            capturedCourses.get(0).getCourseName());
        assertEquals("Artificial Intelligence Fundamentals",
            capturedCourses.get(1).getCourseName());
        assertEquals("Cloud Computing Fundamentals", capturedCourses.get(2).getCourseName());
        assertEquals("Introduction to Cloud", capturedCourses.get(3).getCourseName());
        assertEquals("Enterprise Design Thinking Practitioner",
            capturedCourses.get(4).getCourseName());
    }
}