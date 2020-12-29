package entities;

class Answer {
    private String answerString;
    private boolean right;

    Answer(String answerString, boolean right) {
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
