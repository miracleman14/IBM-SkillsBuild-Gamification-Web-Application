package org.example.ibmskillsbuildapp.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.example.ibmskillsbuildapp.repo.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link UserService} entities. This service provides methods for common
 * operations such as creating users, setting roles, friends, learning paths and courses and adds
 * them to the repository /database.
 */
@Service
public class UserService {

    private final Random random = new Random();
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LearningPathRepository learningPathRepository;

    /**
     * Checks if the user repository is empty.
     *
     * @return `true` if the repository is empty, otherwise `false`.
     */
    public boolean isEmpty() {
        return userRepository.count() == 0;
    }

    /**
     * Creates default users, roles, and relationships.
     */
    public void createUsers() {
        // Create user1 and user2
        User user1 = new User();
        User user2 = new User();

        UserRoles roleUser = userRolesRepository.findByRoleName("USER");
        UserRoles roleAdmin = userRolesRepository.findByRoleName("ADMIN");

        user1.setUserName("USER");
        user1.setPassword(passwordEncoder.encode("PASSWORD123"));
        user1.getUserRoles().add(roleUser);
        user1.setScore(300);
        user2.setUserName("ADMIN");
        user2.setPassword(passwordEncoder.encode("ADMIN123"));
        user2.getUserRoles().add(roleAdmin);
        user2.setScore(400);

        // Create friendship (USER friends with ADMIN)
        user1.setFriends(new ArrayList<>());
        user2.setFriends(new ArrayList<>());
        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user1.getFriends().add(user2); //USER friends with ADMIN but not other way around
        userRepository.save(user1);
        userRepository.save(user2);

        // Fetch learning paths
        Iterable<LearningPath> iterable = learningPathRepository.findAll();
        List<LearningPath> learningPaths = StreamSupport.stream(iterable.spliterator(), false)
            .toList();

        // Create user-course relationships
        for (LearningPath learningPath : learningPaths) {
            List<Course> courses = courseRepository.findByLearningPath(learningPath);
            for (Course course : courses) {
                UserCourse userCourse1 = new UserCourse(user1, course,
                    LearningStatus.AVAILABLE);
                UserCourse userCourse2 = new UserCourse(user2, course,
                    LearningStatus.AVAILABLE);
                userCourseRepository.save(userCourse1);
                userCourseRepository.save(userCourse2);
            }
        }
    }

    /**
     * This method creates demo data for the application. It generates 25 users and assigns them to
     * courses. Each user-course relation is assigned a random learning status. If the status is
     * STARTED or COMPLETED, a random start date within the last year is generated. If the status is
     * COMPLETED, a random completion date after the start date and a random rating between 1 and 5
     * is also generated.
     */
    public void createDemoData() {
        List<Course> courses = (List<Course>) courseRepository.findAll();

        for (int i = 0; i < 25; i++) {
            User user = new User();
            user.setUserName("DemoUser" + i);
            user.setPassword(passwordEncoder.encode("password" + i));
            user = userRepository.save(user);

            UserRoles roleUser = userRolesRepository.findByRoleName("USER");
            user.getUserRoles().add(roleUser);

            for (Course course : courses) {
                UserCourse userCourse = new UserCourse();
                userCourse.setUser(user);
                userCourse.setCourse(course);

                // Randomly assign a LearningStatus
                LearningStatus status = LearningStatus.values()[random.nextInt(
                    LearningStatus.values().length)];
                userCourse.setStatus(status);

                if (status != LearningStatus.AVAILABLE) {
                    // Randomly generate a startDate in the past year
                    LocalDate startDate = LocalDate.now().minusDays(random.nextInt(365));
                    userCourse.setStartDate(
                        Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                    if (status == LearningStatus.COMPLETED) {
                        // Randomly generate a completionDate that is after the startDate
                        LocalDate completionDate = startDate.plusDays(random.nextInt(365));
                        userCourse.setCompletionDate(Date.from(
                            completionDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                        // Randomly generate a rating
                        userCourse.setRating(random.nextInt(5) + 1);

                        // Add score to user
                        user.setScore(user.getScore() + 100);
                        userRepository.save(user);
                    }
                }
                userCourseRepository.save(userCourse);
            }
        }
    }
}
