package org.example.ibmskillsbuildapp.model;

/**
 * Represents a view of a course. This view includes the path name, course name, learning status,
 * and URL.
 */
public class CourseView {

    private Long courseId;
    private String pathName;
    private String courseName;
    private String description;
    private LearningStatus status;
    private String url;

    public CourseView(Long courseId, String pathName, String courseName, String description,
        LearningStatus status, String url) {
        this.courseId = courseId;
        this.pathName = pathName;
        this.courseName = courseName;
        this.description = description;
        this.status = status;
        this.url = url;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
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

    public LearningStatus getStatus() {
        return status;
    }

    public void setStatus(LearningStatus status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
