package oqu.today.initital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "option")
public class Option implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = true)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "content", nullable = false, length = 65535)
    private String content;
    private boolean correct;

    public Option(Question question, String content, boolean correct) {
        this.question = question;
        this.content = content;
        this.correct = correct;
    }

    public Option() {
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
