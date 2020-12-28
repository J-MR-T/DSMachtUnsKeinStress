package entities;

import rendering.DisplayObjects;
import mainpack.Var;
public class Formula extends Entity {
    private int difficulty;
    private String formulaT;
    private String[] answers;
    private int correctAnswerIndex;
    public Formula(int[] pos, DisplayObjects[][] charRepresentation, int[] bounds, int frequency, int difficulty, String formulaT, String[] answers, int correctAnswerIndex) {
        super(pos, charRepresentation, bounds, frequency);
        this.difficulty = difficulty;
        //Formula Text
        this.formulaT = formulaT;

        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    @Override
    protected void move(int[] newPos) {
        this.position = newPos;
    }

    @Override
    public void updateInner() {
        //Check for collisions before doing step
        if(Var.player.collidesWith(this)){
            //lets render the math question
            Var.isPaused = true;

        }
    }

    public String toString(){
        Stringbuilder render = new StringBuilder();
        render.apend("So it seems you ran into me  -  now you got to solve me\nSolve me for an epic boost or fail and die miserably\n");
        render.append(this.formulaT);
        render.append(".\n");
        return render.toString();
    }
}
