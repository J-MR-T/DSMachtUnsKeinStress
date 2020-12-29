package updater;

import mainpack.StageEnum;
import mainpack.StateEnum;
import mainpack.Var;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {
    private final static int MAX_FORMULAS = 150;

    /*
    *  Runs the first level boss - makes it much harder - difficulty scales up with each level boss
    */
    private static void runKurztest1(){
        setSpawnParams(0.5f, 0.3f, 150, 4); //TODO playtest and determine values
    }
    private static void runKurztest2(){
        setSpawnParams(0.4f, 0.4f, 150, 4); //TODO playtest and determine values
    }
    private static void runKurztest3(){
        setSpawnParams(0.3f, 0.5f, 150, 4); //TODO playtest and determine values
    }

    /*
    * Final Level
     */
    private static void runExam(){
        //LUTTI APPROACHES
        Updater.setSpawningParameters(0.5f, 0.5f, 300, 8); //TODO playtest and determine values
    }

    /*
     * Runs the next stage
     */
    public void triggerWaitingForNextStage() {
        if (Var.gameStage <= StageEnum.BOSS_STAGE.ordinal()) {
            this.schedule(new TimerTask() {
                @Override
                public void run() {
                    Var.gameState= StateEnum.KURZTEST.ordinal();
                    Var.gameStage++;
                    Updater.triggerKurztest();
                }
            }, Var.TIME_BETWEEN_KURZTESTS);
        }else{
            this.schedule(new TimerTask() {
                @Override
                public void run() {
                    Var.gameStage++;
                }
            }, Var.TIME_BETWEEN_KURZTESTS/2);
        }
    }


}
