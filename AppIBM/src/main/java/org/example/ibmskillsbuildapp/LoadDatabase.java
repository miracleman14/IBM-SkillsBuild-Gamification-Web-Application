package org.example.ibmskillsbuildapp;

import org.example.ibmskillsbuildapp.service.CourseService;
import org.example.ibmskillsbuildapp.service.LearningPathService;
import org.example.ibmskillsbuildapp.service.UserRolesService;
import org.example.ibmskillsbuildapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for loading initial data into the database. This class checks if the
 * repositories for users, user roles, courses, and learning paths are empty, and if so, creates and
 * saves new entities.
 */
@Configuration
public class LoadDatabase {

    private final UserRolesService userRolesService;
    private final UserService userService;
    private final CourseService courseService;
    private final LearningPathService learningPathService;

    /**
     * Constructs a new LoadDatabase object with the specified user, course, and learning path
     * services.
     *
     * @param userService         the service for managing users.
     * @param courseService       the service for managing courses.
     * @param learningPathService the service for managing learning paths.
     */
    public LoadDatabase(UserRolesService userRolesService, UserService userService,
        CourseService courseService,
        LearningPathService learningPathService) {
        this.userRolesService = userRolesService;
        this.userService = userService;
        this.courseService = courseService;
        this.learningPathService = learningPathService;
    }

    /**
     * Initializes the database with user roles, users, courses, and learning paths if their
     * respective repositories are empty.
     *
     * @return a CommandLineRunner that runs the initialization logic.
     */
    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (learningPathService.isEmpty()) {
                learningPathService.createLearningPaths();
            }

            if (courseService.isEmpty()) {
                courseService.createCourses();
            }

            if (userRolesService.isEmpty()) {
                userRolesService.createRoles();
            }

            if (userService.isEmpty()) {
                userService.createUsers();
                userService.createDemoData();
            }
        };
    }
}
