package org.example.ibmskillsbuildapp.repo;

import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningStatus;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserCourse;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing {@link UserCourse} entities. This repository provides methods
 * for common database operations such as save, delete, and find.
 */
public interface UserCourseRepository extends CrudRepository<UserCourse, Long> {

    /**
     * Finds a UserCourse by its User and Course.
     *
     * @param user   the user of the UserCourse to find.
     * @param course the course of the UserCourse to find.
     * @return the UserCourse with the specified user and course.
     */
    UserCourse findByUserAndCourse(User user, Course course);

    List<UserCourse> findAllByUserAndStatus(User user, LearningStatus status);
}
