package updater;

import entities.Formula;
import mainpack.Var;

public class Updater {

    static float percMedium;
    static float percHard;
    static int durationOfStage;
    static int spawnRate;
    private static int stepsSinceStageTrigger = 0;

    public static void update() {
        Var.player.update();
        for (int i = 0; i < Var.formulas.size(); i++) {
            Formula formula = Var.formulas.get(i);
            formula.update();
            //Not sure if this can be done in one go
            formula.render(Var.r.screens.get(Var.r.ENTITY_LAYER));
        }
        for (int i = 0; i < Var.formulas.size(); i++) {
            if (Var.player.collidesWith(Var.formulas.get(i))) {
                //Hit by formula
                Var.gameState = Var.HIT_BY_FORMULA;
                Var.hitFormulaIndex = i;
            }
        }
        Var.player.render(Var.r.screens.get(Var.r.ENTITY_LAYER));

        Var.r.renderFrame();
        Var.r.resetEntityLayer();

        //Spawning of new Entities
        if(stepsSinceStageTrigger>=durationOfStage){
            //returns to old spawning pattern
            durationOfStage = Integer.MAX_VALUE;
            stepsSinceStageTrigger = Integer.MIN_VALUE;
            //ruft jetzt den reset auf
        }
    }
    static void setSpawningParameters(float percMedium, float percHard, int durationOfStage, int spawnRate){
        stepsSinceStageTrigger = 0;
        percMedium = percMedium;
        percHard = percHard;
        durationOfStage = durationOfStage;
        spawnRate = spawnRate;

    }

}
