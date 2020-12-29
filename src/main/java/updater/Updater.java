package updater;

import entities.Formula;
import mainpack.StateEnum;
import mainpack.Var;

public class Updater {

    private static float percMedium;
    private static float percHard;
    private static int durationOfStage;
    private static int spawnRate;
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
                Var.gameState = StateEnum.HIT_BY_FORMULA.ordinal();
                Var.hitFormulaIndex = i;
            }
        }
        Var.player.render(Var.r.screens.get(Var.r.ENTITY_LAYER));

        Var.r.renderFrame();
        Var.r.resetEntityLayer();

        //Spawning of new Entities
        if (stepsSinceStageTrigger >= durationOfStage) {
            //returns to old spawning pattern
            durationOfStage = Integer.MAX_VALUE;
            stepsSinceStageTrigger = Integer.MIN_VALUE;
            //ruft jetzt den reset auf
        }
    }

    static void setSpawningParameters(float pPercMedium, float pPercHard, int pDurationOfStage, int pSpawnRate) {
        stepsSinceStageTrigger = 0;
        percMedium = pPercMedium;
        percHard = pPercHard;
        durationOfStage = pDurationOfStage;
        spawnRate = pSpawnRate;
    }

    public static void triggerKurztest() {
        setSpawningParameters(0.5f,0.3f,(int)Var.KURZTEST_DURATION,9999);
    }
}
