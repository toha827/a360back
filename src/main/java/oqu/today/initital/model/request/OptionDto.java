package oqu.today.initital.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import oqu.today.initital.model.Option;
import oqu.today.initital.model.Question;

import javax.persistence.*;
import java.io.Serializable;

public class OptionDto implements Serializable {
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;
    private String content;
    private boolean correct;

    public OptionDto(Question question, String content, boolean correct) {
        this.question = question;
        this.content = content;
        this.correct = correct;
    }

    public OptionDto() {
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


    public Option toEntity() {
        return new Option(
                question,
                content,
                correct
        );
    }
}
