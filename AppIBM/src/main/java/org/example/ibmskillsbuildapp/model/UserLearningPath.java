package org.example.ibmskillsbuildapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Represents a user's progress in a learning path in the application. This includes the user, the
 * learning path they are taking, and their current learning status. Currently unused, but could be
 * used to track user progress in the future.
 */
@Entity
public class UserLearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private LearningPath learningPath;

    @Enumerated(EnumType.STRING)
    private LearningStatus status;

    public UserLearningPath() {
    }

    public UserLearningPath(User user, LearningPath learningPath, LearningStatus status) {
        this.user = user;
        this.learningPath = learningPath;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }

    public LearningStatus getStatus() {
        return status;
    }

    public void setStatus(LearningStatus status) {
        this.status = status;
    }
}