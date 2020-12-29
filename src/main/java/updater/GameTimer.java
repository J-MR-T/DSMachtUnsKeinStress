package updater;

import mainpack.StageEnum;
import mainpack.StateEnum;
import mainpack.Var;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {
    private final static int MAX_FORMULAS = 150;


    /*
    * Final Level
     */
    private void runExam(){
        //LUTTI APPROACHES
        Updater.setSpawningParameters(0.5f, 0.5f, 300, 8); //TODO playtest and determine values
    }

    /*
     * Runs the next stage
     */
    public void triggerWaitingForNextStage() {
        if (Var.gameStage < StageEnum.BOSS_STAGE.ordinal()-1) {
            this.schedule(new TimerTask() {
                @Override
                public void run() {
                    Var.gameState= StateEnum.KURZTEST.ordinal();
                    Var.gameStage++;
                    Updater.triggerKurztest(Var.gameStage>=1&&Var.gameStage<=3?Var.gameStage:-1);
                }
            }, Var.TIME_BETWEEN_KURZTESTS);
        }else{
            this.schedule(new TimerTask() {
                @Override
                public void run() {
                    Var.gameState=StateEnum.BOSS.ordinal();
                    Updater.triggerBoss();
                }
            }, Var.TIME_BETWEEN_KURZTESTS/2);
        }
    }


}
