package mainpack;

import entities.Formula;
import entities.Player;
import rendering.Renderer;
import updater.GameTimer;

import java.util.List;

public class Var {
    public static Renderer r;
    public static GameTimer timer;
    public static final int width = 0;
    public static final int height = 0;


    public static final int EVADING_FORMULAS = 0;
    public static final int MENU = 1;
    public static final int HIT_BY_FORMULA = 2;
    public static final int KURZTEST = 3;
    public static final int BOSS = 4;

    public static int gameState;

    public static final int NO_KURZTEST_YET = 0;
    public static final int KURZTEST_1 = 1;
    public static final int KURZTEST_2 = 2;
    public static final int KURZTEST_3 = 3;
    public static final int BOSS_STAGE = 4;

    public static final long KURZTEST_DURATION = 30l*1000;
    public static final long TIME_BETWEEN_KURZTESTS = 180l*1000;


    public static int gameStage = NO_KURZTEST_YET;


    public static final Player player = null;
    public static List<Formula> formulas;
    public static int hitFormulaIndex = -1;


    //default spawning rate
    public static float defaultPercMedium = 0.4f;
    public static float defaultPercHard = 0.0f;


}
