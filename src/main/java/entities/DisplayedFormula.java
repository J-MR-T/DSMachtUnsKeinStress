package entities;

public class DisplayedFormula {
    private String question;

    public Answer[] getAnswers() {
        return answers;
    }

    private Answer[] answers;
    private int difficulty;

    public int getDifficulty() {
        return difficulty;
    }

    public DisplayedFormula(String question, Answer[] answers, int difficulty) {
        this.question = question;
        this.answers = answers;
        this.difficulty=difficulty;
    }
//
//    public boolean answerFormula(char answer) {
//        return switch (Character.toLowerCase(answer)) {
//            case 'a' -> aTrue;
//            case 'b' -> bTrue;
//            case 'c' -> cTrue;
//            default -> false;
//        };
//    }
//
//    public boolean answerFormula(int answer) {
//        return switch (answer) {
//            case 1 -> aTrue;
//            case 2 -> bTrue;
//            case 3 -> cTrue;
//            default -> false;
//        };
//    }

    public String getQuestion() {
        return question;
    }

    public Answer getAnswer(int i) {
        return answers[i];
    }

}
