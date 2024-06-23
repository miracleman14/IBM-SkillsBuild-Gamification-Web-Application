package org.example.ibmskillsbuildapp.model;

/**
 * Represents the learning status of a course in the application. The status can be one of the
 * following:
 * <ul>
 *     <li>AVAILABLE: The course is available for the user to start.</li>
 *     <li>STARTED: The user has started the course but has not completed it yet.</li>
 *     <li>COMPLETED: The user has completed the course.</li>
 * </ul>
 */
public enum LearningStatus {
    AVAILABLE,
    STARTED,
    COMPLETED
}
