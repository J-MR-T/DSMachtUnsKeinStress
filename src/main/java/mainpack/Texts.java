package mainpack;

public enum Texts {
    STARTING_THE_GAME("\n" +
            "So...\n" +
            "you want to walk the path of excellence.\n" +
            "Good.\n" +
            "But first you need to survive\n" +
            "-> DISKRETE STRUKTUREN <-\n" +
            "\n" +
            "WASD to move.\n" +
            "Evade the formulas!\n" +
            "\n"),
    COLLISION_WITH_FORMULA("You were hit by a formula.\n" +
            "Solve to escape:\n" +
            "\n"),
    PROMPT_ANSWER_FORMULA("Press A, B, or C to proceed: "),
    PLAYER_GIVES_WRONG_ANSWER("Insufficient.\n"),
    PLAYER_ANSWER_TAKES_TOO_LONG("Submission deadline! No Submission uploaded.\n" +
            "\n"),
    PLAYER_GIVES_RIGHT_ANSWER("TRIVIAL\n"),
    LOSE_FIRST_LIFE("Dear contestant,\n" +
            "\n" +
            "in order to keep motivation high for the following ordeals, " +
            "we have decided to slightly adjust the rules for your survival: \n" +
            "\n" +
            "To secure your life you only need to protect 40% of all attainable points in " +
            "only two of the three areas: social life, sleep and will to live. In total you only " +
            "need to retain 60% sanity across all attainable points.\n" +
            "\n" +
            "Sufficient greetings\n" +
            "DS-TEAM\n"),
    LOSE_SECOND_LIFE("Dear contestant,\n" +
            "\n" +
            "not only have you not signed your test, you also seem to have used a red pen. Frankly, we find your lack of excellence disturbing. We can only suggest you proceed with caution.\n" +
            "\n" +
            "Necessary greetings\n" +
            "DS-TEAM\n"),
    LOSE_LAST_LIFE("GAME OVER\n" +
            "\n" +
            "You lost.\n" +
            "\n" +
            "bit.ly/unexzellenz ..?\n" +
            "\n" +
            "You might make in the retake, though. Good luck with that.\n" +
            "\n" +
            "Note: SEMESTERFERIEN have been irrecoverably lost.\n"),
    KURZTEST1("ALERT: \n" +
            "KURZTEST coming up\n" +
            "1/3\n"),
    KURZTEST2("ALERT: \n" +
            "KURZTEST coming up\n" +
            "2/3\n"),
    KURZTEST3("ALERT: \n" +
            "KURZTEST coming up\n" +
            "3/3\n"),
    KLAUSUR("ALERT:\n" +
            "Exam day has approached\n"),
    PLAGIAT("\n" +
            "/~\\_/~\\_/~\\ ÜL OPEN UP! /~\\_/~\\_/~\\\n" +
            "\n" +
            "Plagiarism detected!\n" +
            "\n" +
            "Dear contestant,\n" +
            "we have received notice of your BETRUGSVERSUCH. We are shocked and sad.\n" +
            "The character limit for defending yourself has been set to -1.\n" +
            "Without loss of generality, it can be said that:\n" +
            "\n" +
            "You lose.\n" +
            "\n" +
            "GAME OVER\n" +
            "\n"),
    GAME_WON("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n" +
            ">->->->->->->->->  YOU  SURVIVED  <-<-<-<-<-<-<-<-<-<\n" +
            "-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n" +
            "\n" +
            "Congratulations, contestant!\n" +
            "\n" +
            "You made it.\n" +
            "For now.\n" +
            "\n" +
            "Good luck with\n" +
            "LINEARE ALGEBRA FUER INFORMATIK\n" +
            "FUNKTIONALE PROGRAMMIERUNG UND VERIFIKATION\n" +
            "ANALYSIS FUER INFORMATIK\n" +
            "EINFUEHRUNG IN DIE THEORETISCHE INFORMATIK\n" +
            "DISKRETE WAHRSCHEINLICHKEITSTHEORIE\n" +
            "NUMERISCHES PROGRAMMIEREN\n" +
            "\n");

    Texts(String s) {
    }
}
