package oqu.today.initital.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oqu.today.initital.model.Option;
import oqu.today.initital.model.Question;
import oqu.today.initital.model.Quiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class QuestionDTO implements Serializable {
    @Id
    private int id;
    private String content;
    private String courseType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Option> options = new HashSet<Option>(0);

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public QuestionDTO() {
    }

    public QuestionDTO(String content, String courseType, Set<Option> options, Quiz quiz) {
        this.content = content;
        this.courseType = courseType;
        this.options = options;
        this.quiz = quiz;
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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question toEntity () {
        return new Question(
                content,
                courseType,
                options,
                quiz
        );
    }
}
