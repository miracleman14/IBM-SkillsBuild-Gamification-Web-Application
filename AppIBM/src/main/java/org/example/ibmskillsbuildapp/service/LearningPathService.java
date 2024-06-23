package org.example.ibmskillsbuildapp.service;

import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link LearningPath} entities. This service provides methods for
 * common operations such as checking if the repository is empty and creating learning paths.
 */
@Service
public class LearningPathService {

    @Autowired
    private LearningPathRepository learningPathRepository;

    /**
     * Checks if the learning path repository is empty.
     *
     * @return true if the learning path repository is empty, false otherwise.
     */
    public boolean isEmpty() {
        return learningPathRepository.count() == 0;
    }

    /**
     * Creates and saves new learning paths in the learning path repository.
     */
    public void createLearningPaths() {
        LearningPath path1 = new LearningPath("Artificial Intelligence");
        LearningPath path2 = new LearningPath("Cloud Computing");
        LearningPath path3 = new LearningPath("Design Thinking");

        learningPathRepository.save(path1);
        learningPathRepository.save(path2);
        learningPathRepository.save(path3);
    }
}
