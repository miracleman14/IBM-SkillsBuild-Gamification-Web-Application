package org.example.ibmskillsbuildapp.controller;

import org.example.ibmskillsbuildapp.model.UserStreak;
import org.example.ibmskillsbuildapp.service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StreakController {

    private final StreakService streakService;

    // Constructor injection of StreakService
    @Autowired
    public StreakController(StreakService streakService) {
        this.streakService = streakService;
    }

    // Handle POST request to update streak
    @PostMapping("/streaks/{userId}")
    public String updateStreak(@PathVariable Long userId) {
        streakService.updateStreak(userId);
        return "redirect:/streaks/" + userId; // Redirect to GET endpoint after updating streak
    }

    // Handle GET request to retrieve user streak
    @GetMapping("/streaks/{userId}")
    public String getUserStreak(@PathVariable Long userId, Model model) {
        // Retrieve user streak from service layer
        UserStreak userStreak = streakService.getUserStreak(userId);
        // Add user streak attribute to model for view rendering
        model.addAttribute("userStreak", userStreak);
        // Return view name for displaying user streak
        return "streak";
    }
}
