package updater;

import java.util.Timer;

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
        setSpawnParams(0.5f, 0.5f, 300, 8); //TODO playtest and determine values
    }


    private static void setSpawnParams(float percMedium, float percHard, long durationOfStage, int spawnRate){

    }
    /*
    * Runs the next stage
     */
    private static void triggerNextStage(){

    }


}
