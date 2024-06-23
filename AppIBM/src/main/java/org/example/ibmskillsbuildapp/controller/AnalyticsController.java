package org.example.ibmskillsbuildapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling requests related to analytics view.
 */
@Controller
public class AnalyticsController {

    /**
     * Handles GET requests to the /viewAnalytics endpoint. Only users with the 'ADMIN' role can
     * access this method.
     *
     * @param model the Model object to be used in the view
     * @return the name of the analytics view
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/viewAnalytics")
    public String viewAnalytics(Model model) {
        return "analytics";
    }
}
