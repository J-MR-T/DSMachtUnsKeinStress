package updater;

import entities.Formula;
import mainpack.Var;

public class Updater {

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
    }

}
