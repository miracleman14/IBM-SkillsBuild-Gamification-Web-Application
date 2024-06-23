package org.example.ibmskillsbuildapp.repo;

import java.util.List;
import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing {@link Course} entities. This repository provides methods for
 * common database operations such as save, delete, and find.
 */
public interface CourseRepository extends CrudRepository<Course, Long> {

    /**
     * Finds all courses associated with a specific learning path.
     *
     * @param learningPath the learning path to find courses for.
     * @return a list of courses associated with the specified learning path.
     */
    List<Course> findByLearningPath(LearningPath learningPath);
}
