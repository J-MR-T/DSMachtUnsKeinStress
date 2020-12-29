package entities;

public class Answer {
    private String answerString;
    private boolean right;

    public Answer(String answerString, boolean right) {
        this.answerString = answerString;
        this.right = right;
    }

    public String getAnswerString() {
        return answerString;
    }

    public boolean isRight() {
        return right;
    }
}
