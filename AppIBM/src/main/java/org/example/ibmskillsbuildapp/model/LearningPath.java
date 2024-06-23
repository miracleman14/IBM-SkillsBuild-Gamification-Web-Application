package org.example.ibmskillsbuildapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 * Represents a learning path with a name and a list of courses. A learning path is a collection of
 * courses that a user can take to learn a specific skill.
 */
@Entity
public class LearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pathName;
    @OneToMany(mappedBy = "learningPath")
    private List<Course> courses;
    @OneToMany(mappedBy = "learningPath")
    private List<UserLearningPath> userLearningPaths;

    public LearningPath() {
    }

    public LearningPath(String pathName) {
        this.pathName = pathName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<UserLearningPath> getUserLearningPaths() {
        return userLearningPaths;
    }

    public void setUserLearningPaths(List<UserLearningPath> userLearningPaths) {
        this.userLearningPaths = userLearningPaths;
    }
}
