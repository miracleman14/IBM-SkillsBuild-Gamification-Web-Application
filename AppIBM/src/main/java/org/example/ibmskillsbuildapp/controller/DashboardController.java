package org.example.ibmskillsbuildapp.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.CourseView;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.service.CourseViewService;
import org.example.ibmskillsbuildapp.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for handling requests related to the dashboard.
 */
@Controller
public class DashboardController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseViewService courseViewService;
    @Autowired
    private UserCourseService userCourseService;

    /**
     * Handles GET requests to the /viewDashboard endpoint. Retrieves all CourseView objects for the
     * user, sorts them by their status, and adds the sorted list to the model.
     *
     * @param model the Model object to which the sorted list of CourseView objects is added
     * @return the name of the view to be rendered, in this case "dashboard"
     */
    @GetMapping("/viewDashboard")
    public String dashboard(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.findByUserName(username);

        List<CourseView> courseViews = new ArrayList<>(courseViewService.getAllCourseViews(user));
        courseViews.sort(Comparator.comparing(CourseView::getStatus));
        model.addAttribute("courseViews", courseViews);
        model.addAttribute("user", user); //for general user info
        return "dashboard";
    }

    /**
     * Handles POST requests to the /enroll endpoint. This method enrolls a user in a course. It
     * retrieves the User and Course objects corresponding to the given user ID and course ID, and
     * then calls the UserCourseService's enroll method to enroll the user in the course.
     *
     * @param userId   the ID of the user who is enrolling in the course
     * @param courseId the ID of the course the user is enrolling in
     * @return a redirect to the dashboard view
     */
    @PostMapping("/enroll")
    public String enroll(@RequestParam("userId") Long userId,
        @RequestParam("courseId") Long courseId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));
        userCourseService.enroll(user, course);
        return "redirect:/viewDashboard";
    }

    /**
     * Handles POST requests to the /complete endpoint. This method marks a course as completed for
     * a user. It retrieves the User and Course objects corresponding to the given user ID and
     * course ID, and then calls the UserCourseService's complete method to mark the course as
     * completed for the user. It also increments the user's score by 100.
     *
     * @param userId   the ID of the user who is completing the course
     * @param courseId the ID of the course the user is completing
     * @return a redirect to the dashboard view
     */
    @PostMapping("/complete")
    public String complete(@RequestParam("userId") Long userId,
        @RequestParam("courseId") Long courseId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));
        userCourseService.complete(user, course);
        return "redirect:/viewDashboard";
    }
}