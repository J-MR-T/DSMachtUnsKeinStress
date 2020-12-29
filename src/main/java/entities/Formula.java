package entities;

import rendering.DisplayObjects;
import mainpack.Var;

public class Formula extends Entity {
    public DisplayedFormula getDisplayForm() {
        return displayForm;
    }

    private DisplayedFormula displayForm;
    private int difficulty;

    public Formula(int[] pos, DisplayObjects[][] charRepresentation, int[] bounds, int frequency, int difficulty) {
        super(pos, charRepresentation, bounds, frequency);
        displayForm = null;
        this.difficulty=difficulty;
    }

    public Formula(int[] pos, DisplayObjects[][] charRepresentation, int[] bounds, int frequency, DisplayedFormula displayForm) {
        super(pos, charRepresentation, bounds, frequency);
        this.displayForm = displayForm;
    }

    @Override
    protected void move(int[] newPos) {
        this.pos = newPos;
    }

    @Override
    public void updateInner() {

    }

    public void hit(){
        displayForm=Var.formCollection.getRandomFormula(difficulty);
    }

    public String toString() {
        if (displayForm != null) {
            StringBuilder render = new StringBuilder();
            render.append("So it seems you ran into me  -  now you've got to solve me\n" +
                    "Solve me for an epic boost or fail and die miserably\n\n");
            render.append(displayForm);
            render.append("\n");
            return render.toString();
        } else {
            return "";
        }
    }
}
