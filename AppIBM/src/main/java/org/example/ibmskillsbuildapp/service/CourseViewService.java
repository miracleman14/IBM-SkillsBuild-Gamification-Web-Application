package org.example.ibmskillsbuildapp.service;

import java.util.ArrayList;
import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.CourseView;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.example.ibmskillsbuildapp.repo.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link CourseView} entities. This service provides methods for common
 * operations such as retrieving all courses from all learning paths and wrapping them into
 * CourseView objects.
 */
@Service
public class CourseViewService {

    @Autowired
    private LearningPathRepository learningPathRepository;

    @Autowired
    private UserCourseRepository userCourseRepository;

    /**
     * Retrieves all courses from all learning paths and wraps them into CourseView objects.
     *
     * @param user the user for whom the CourseView objects are being retrieved.
     * @return a list of CourseView objects representing all courses from all learning paths.
     */
    public List<CourseView> getAllCourseViews(User user) {
        List<CourseView> courseViews = new ArrayList<>();
        Iterable<LearningPath> learningPaths = learningPathRepository.findAll();

        for (LearningPath path : learningPaths) {
            for (Course course : path.getCourses()) {
                UserCourse userCourse = userCourseRepository.findByUserAndCourse(user, course);
                CourseView courseView = new CourseView(course.getId(), path.getPathName(),
                    course.getCourseName(),
                    course.getDescription(), userCourse.getStatus(), course.getUrl());
                courseViews.add(courseView);
            }
        }
        return courseViews;
    }
}