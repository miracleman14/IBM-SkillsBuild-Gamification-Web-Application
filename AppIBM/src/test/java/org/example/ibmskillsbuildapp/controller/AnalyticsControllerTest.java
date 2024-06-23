package org.example.ibmskillsbuildapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

class AnalyticsControllerTest {

    @InjectMocks
    private AnalyticsController analyticsController;

    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        model = mock(Model.class);
    }

    @Test
    void testViewAnalytics() {
        String view = analyticsController.viewAnalytics(model);
        assertEquals("analytics", view);
    }
}
