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

    public static int whichText = -1;

    public static StateEnum gameState;

    public static final long KURZTEST_DURATION = 30l * 1000;
    public static final long TIME_BETWEEN_KURZTESTS = 180l * 1000;


    public static int gameStage = StageEnum.NO_KURZTEST_YET.ordinal();


    public static Player player = null;
    public static List<Formula> formulas;
    public static int hitFormulaIndex = -1;


    //default spawning rate
    public static float defaultPercMedium = 0.4f;
    public static float defaultPercHard = 0.0f;
    public static int defaultSpawnRate = 2;

}
