package oqu.today.initital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private int id;

    @Column(name = "content", nullable = false, length = 65535)
    private String content;

    @Column(name = "course_type", nullable = false, length = 65535)
    private String courseType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Option> options = new HashSet<Option>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Question() {
    }

    public Question(String content, String courseType, Set<Option> options, Course course) {
        this.content = content;
        this.courseType = courseType;
        this.options = options;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
