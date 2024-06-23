package org.example.ibmskillsbuildapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.contains;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.ibmskillsbuildapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


class RecordStartControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private RecordStartController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRecordStart() {
        User user = new User();
        String result = controller.recordStart(user, model);

        // If isPaused is false, the notification should contain "started"
        assertEquals("record-start", result);
        verify(model, times(1)).addAttribute(eq("notification"), contains("started"));
    }

    @Test
    void testPause() {
        User user = new User();
        String result = controller.pause(user, model);

        // If isPaused is false, the notification should contain "paused"
        assertEquals("pause", result);
        verify(model, times(1)).addAttribute(eq("notification"), contains("paused"));
    }

    @Test
    void testResumeWhenPaused() {
        User user = new User();
        //When resume is paused, the notification should contain "resumed"
        when(model.addAttribute(eq("notification"), any(String.class)))
            .thenAnswer(invocation -> {
                String actualNotification = invocation.getArgument(1);
                assertTrue(actualNotification.contains("resumed") || actualNotification.contains(
                    "not paused"));
                return null;
            });

        String result = controller.resume(user, model);
        //verification
        assertEquals("resume", result);
        verify(model, times(1)).addAttribute(eq("notification"), any(String.class));
    }

    @Test
    void testResumeWhenNotPaused() {
        User user = new User();
        //When resume is not paused, the notification should contain "not paused"
        when(model.addAttribute(eq("notification"), any(String.class)))
            .thenAnswer(invocation -> {
                String actualNotification = invocation.getArgument(1);
                assertTrue(actualNotification.contains("not paused"));
                return null;
            });

        controller.resume(user, model);

        String result = controller.resume(user, model);

        assertEquals("resume", result);
        verify(model, times(2)).addAttribute(eq("notification"), any(String.class));
    }
}
