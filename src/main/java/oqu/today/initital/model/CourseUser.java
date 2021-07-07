package oqu.today.initital.model;


import javax.persistence.*;

@Entity
@Table(name = "CourseUsers")
public class CourseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int courseId;
    @OneToOne
    private Course course;
    private int progress;

    public CourseUser() {
    }

    public CourseUser(int user_id, int course_id, int progress, Course course) {
        this.userId = user_id;
        this.courseId = course_id;
        this.progress = progress;
        this.course = course;
    }

    public CourseUser(int id, int user_id, int course_id, int progress, Course course) {
        this.id = id;
        this.userId = user_id;
        this.courseId = course_id;
        this.progress = progress;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "CourseUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", progress=" + progress +
                '}';
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}