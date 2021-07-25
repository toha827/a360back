package oqu.today.initital.model.request;

import oqu.today.initital.model.Option;
import oqu.today.initital.model.Question;
import oqu.today.initital.model.Quiz;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

public class QuizSubmitRequest {
    private Quiz quiz;
    private List<OptionDto> answered_options;

    public QuizSubmitRequest(Quiz quiz, List<OptionDto> answered_options) {
        this.quiz = quiz;
        this.answered_options = answered_options;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<OptionDto> getAnswered_options() {
        return answered_options;
    }

    public void setAnswered_options(List<OptionDto> answered_options) {
        this.answered_options = answered_options;
    }
}
