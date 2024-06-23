package org.example.ibmskillsbuildapp.controller;

import static org.example.ibmskillsbuildapp.model.LearningStatus.COMPLETED;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling requests related to Course Progression.
 */
@Controller
public class ProgressBarController {

    @Autowired
    private LearningPathRepository learningPathRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Handles GET requests to the /viewProgress endpoint. Retrieves all courses and completed
     * courses for the user, sorts them into separate categories, and calculates the percentage
     * competed in each category.
     *
     * @param model the Model object to which the sorted list of Courses and completed percentage is
     *              added
     * @return the name of the view to be rendered, in this case "ProgressBar"
     */

    @GetMapping("/viewProgress")
    public String ProgressBar(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = userRepository.findByUserName(username);

        List<Course> AI = new ArrayList<>();
        List<Course> CC = new ArrayList<>();
        List<Course> DT = new ArrayList<>();
        Iterable<LearningPath> learningPaths = learningPathRepository.findAll();
        List<UserCourse> completedCourses = userCourseRepository.findAllByUserAndStatus(user,
            COMPLETED);

        for (LearningPath path : learningPaths) {
            for (Course course : path.getCourses()) {
                if (course.getLearningPath().getPathName().equals("Artificial Intelligence")) {
                    AI.add(course);
                }
                if (course.getLearningPath().getPathName().equals("Cloud Computing")) {
                    CC.add(course);
                }
                if (course.getLearningPath().getPathName().equals("Design Thinking")) {
                    DT.add(course);
                }
            }
        }

        int AICount = 0;
        int CCcount = 0;
        int DTCount = 0;
        for (UserCourse userCourse : completedCourses) {
            if (userCourse.getCourse().getLearningPath().getPathName()
                .equals("Artificial Intelligence")) {
                AICount += 1;
            }

            if (userCourse.getCourse().getLearningPath().getPathName().equals("Cloud Computing")) {
                CCcount += 1;
            }

            if (userCourse.getCourse().getLearningPath().getPathName().equals("Design Thinking")) {
                DTCount += 1;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        double AIPercent = !AI.isEmpty() ? (double) AICount / AI.size() * 100 : 0;
        String AIPercentage = df.format(AIPercent);
        double CCPercent = !CC.isEmpty() ? (double) CCcount / CC.size() * 100 : 0;
        String CCPercentage = df.format(CCPercent);
        double DTPercent = !DT.isEmpty() ? (double) DTCount / DT.size() * 100 : 0;
        String DTPercentage = df.format(DTPercent);

        model.addAttribute("AI", AI);
        model.addAttribute("CC", CC);
        model.addAttribute("DT", DT);
        model.addAttribute("AIPercentage", AIPercentage);
        model.addAttribute("CCPercentage", CCPercentage);
        model.addAttribute("DTPercentage", DTPercentage);
        return "ProgressBar";
    }
}
