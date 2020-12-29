package updater;

import mainpack.Var;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {
    private final static int MAX_FORMULAS = 150;

    /*
     *  Runs the first level boss - makes it much harder - difficulty scales up with each level boss
     */
    private void runKurztest1() {
        setSpawnParams(0.5f, 0.3f, 150, 4); //TODO playtest and determine values
    }

    private void runKurztest2() {
        setSpawnParams(0.4f, 0.4f, 150, 4); //TODO playtest and determine values
    }

    private void runKurztest3() {
        setSpawnParams(0.3f, 0.5f, 150, 4); //TODO playtest and determine values
    }

    /*
     * Final Level
     */
    private void runExam() {
        setSpawnParams(0.5f, 0.5f, 300, 8); //TODO playtest and determine values
    }


    private void setSpawnParams(float percMedium, float percHard, long durationOfStage, int spawnRate) {

    }

    /*
     * Runs the next stage
     */
    public void triggerWaitingForNextStage() {
        if (Var.gameStage <= Var.BOSS_STAGE) {
            this.schedule(new TimerTask() {
                @Override
                public void run() {
                    Var.gameState=Var.KURZTEST;
                    Var.gameStage++;
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
