package org.example.ibmskillsbuildapp.controller;

import java.util.List;
import java.util.Map;
import org.example.ibmskillsbuildapp.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for handling requests related to analytics data.
 */
@RestController
public class AnalyticsDataController {

    @Autowired
    private UserCourseService userCourseService;

    /**
     * Handles GET requests to the /analyticsData endpoint.
     *
     * @return a ResponseEntity containing a list of maps with analytics data
     */
    @GetMapping("/analyticsData")
    public ResponseEntity<List<Map<String, Object>>> getAnalyticsData() {
        List<Map<String, Object>> formattedData = userCourseService.getAnalyticsData();
        return ResponseEntity.ok(formattedData);
    }
}
