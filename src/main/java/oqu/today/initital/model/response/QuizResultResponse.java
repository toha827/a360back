package oqu.today.initital.model.response;

public class QuizResultResponse {
    private String message;
    private int correct;
    private int totalOptions;

    public QuizResultResponse(String message, int correct, int totalOptions) {
        this.message = message;
        this.correct = correct;
        this.totalOptions = totalOptions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getTotalOptions() {
        return totalOptions;
    }

    public void setTotalOptions(int totalOptions) {
        this.totalOptions = totalOptions;
    }
}
