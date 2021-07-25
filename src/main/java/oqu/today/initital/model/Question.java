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
    private long id;

    @Column(name = "content", nullable = false, length = 65535)
    private String content;

    @Column(name = "course_type", nullable = false, length = 65535)
    private String courseType;

    @OneToMany
    @JoinColumn(name = "question_id")
    private Set<Option> options = new HashSet<Option>(0);

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;

    public Question() {
    }
    public Question(String content, String courseType, Set<Option> options, Quiz quiz) {
        this.content = content;
        this.courseType = courseType;
        this.options = options;
        this.quiz = quiz;
    }

    public long getId() {
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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
