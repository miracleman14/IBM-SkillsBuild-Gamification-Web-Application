package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.LearningPath;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing {@link LearningPath} entities. This repository provides methods
 * for common database operations such as save, delete, and find.
 */
public interface LearningPathRepository extends CrudRepository<LearningPath, Long> {

    /**
     * Finds a learning path by its name.
     *
     * @param pathName the name of the learning path to find.
     * @return the learning path with the specified name.
     */
    LearningPath findByPathName(String pathName);
}
