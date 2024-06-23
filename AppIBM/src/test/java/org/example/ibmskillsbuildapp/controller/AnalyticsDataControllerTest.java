package org.example.ibmskillsbuildapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import org.example.ibmskillsbuildapp.service.UserCourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class AnalyticsDataControllerTest {

    @InjectMocks
    private AnalyticsDataController analyticsDataController;

    @Mock
    private UserCourseService userCourseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAnalyticsData() {
        List<Map<String, Object>> expectedData = List.of(
            Map.of("key", "value"));
        when(userCourseService.getAnalyticsData()).thenReturn(expectedData);

        ResponseEntity<List<Map<String, Object>>> response = analyticsDataController.getAnalyticsData();
        assertEquals(ResponseEntity.ok(expectedData), response);
    }
}
