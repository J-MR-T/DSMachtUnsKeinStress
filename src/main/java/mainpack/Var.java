package mainpack;

import entities.Formula;
import entities.Player;
import rendering.Renderer;

import java.util.List;

public class Var {
    public static Renderer r;
    public static final int width = 0;
    public static final int height = 0;



    public static final int EVADING_FORMULAS = 0;
    public static final int MENU = 1;
    public static final int HIT_BY_FORMULA = 2;
    public static final int BOSS = 3;

    public static int gameState;



    public static final Player player = null;
    public static List<Formula> formulas;
    public static int hitFormulaIndex = -1;


}
