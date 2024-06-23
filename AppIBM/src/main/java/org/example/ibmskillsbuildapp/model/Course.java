package org.example.ibmskillsbuildapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 * Represents a course. A course is a part of a learning path.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    @Column(length = 1000)
    private String description;
    private String url;
    @ManyToOne
    @JoinColumn(name = "learning_path_id")
    private LearningPath learningPath;
    @OneToMany(mappedBy = "course")
    private List<UserCourse> userCourse;

    public Course() {
    }

    public Course(String courseName, String description, String url) {
        this.courseName = courseName;
        this.description = description;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }

    public List<UserCourse> getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(List<UserCourse> userCourse) {
        this.userCourse = userCourse;
    }
}
