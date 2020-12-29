package updater;

import UserInput.KeyListener;
import audioOutput.Audio;
import entities.Answer;
import entities.Formula;
import entities.FormulaCollection;
import entities.Player;
import mainpack.StageEnum;
import mainpack.StateEnum;
import mainpack.Var;
import rendering.DisplayObjects;

import java.util.EventListener;
import java.util.function.Function;
import org.apache.commons.vfs2.FileSystemException;
import rendering.DisplayObjects;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import static mainpack.StateEnum.EVADING_FORMULAS;

public class Updater {

    private static float percMedium;
    private static float percHard;
    private static int durationOfStage;
    private static int spawnRate;
    private static int stepsSinceStageTrigger = 0;
    private static GameTimer timer;
    private static StageEnum stage;
    private static AtomicBoolean updated=new AtomicBoolean(false);
    private static KeyListener keys;

    static void setGameStage(StageEnum s){
        stage=s;
        updated.set(true);
    }

    public static void run(boolean forceGui) throws FileSystemException {
        Var.player=new Player(new int[]{10,15},new DisplayObjects[][]{{DisplayObjects.BLOCK}},new int[]{1,1},3);
        Var.formCollection= FormulaCollection.getFormulas();
        timer=new GameTimer();
        keys= KeyListener.getKeyListener(Updater::update,forceGui);
        timer.triggerWaitingForNextStage();
    }

    public static void update(char input) {

        if(updated.get()){
            switch (stage){
                case KURZTEST_1->{
                    triggerKurztest(1);
                }
                case KURZTEST_2 -> {
                    triggerKurztest(2);
                }
                case KURZTEST_3 -> {
                    triggerKurztest(3);
                }
                case BOSS_STAGE -> {
                    triggerBoss();
                }
            }
        }

        switch (Var.gameState) {
            case EVADING_FORMULAS, KURZTEST, BOSS -> {
                switch (input){
                    case 'w','a','s','d',' '->{}
                    default -> {
                        try {
                            Audio.play(new URL("https://github.com/J-MR-T/UnicodeRenderer/raw/master/src/main/resources/sounds/betrugsversuch.wav"));
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                        return;
                    }
                }
                Var.player.setMovement(input).update();
                for (int i = 0; i < Var.formulas.size(); i++) {
                    Formula formula = Var.formulas.get(i);
                    formula.update();
                    if(formula.getY()>=Var.height-1){
                        Var.formulas.remove(i);
                    }
                    //Not sure if this can be done in one go
                    formula.render(Var.r.screens.get(Var.r.ENTITY_LAYER));
                }
                for (int i = 0; i < Var.formulas.size(); i++) {
                    if (Var.player.collidesWith(Var.formulas.get(i))) {
                        //Hit by formula
                        Var.gameState = StateEnum.HIT_BY_FORMULA;
                        Var.hitFormulaIndex = i;
                        //Deletes formula as soon as we hit it
                        Var.formulas.remove(i);
                        Var.formulas.get(Var.hitFormulaIndex).hit();
                    }
                }
                Var.player.render(Var.r.screens.get(Var.r.ENTITY_LAYER));
                try {
                    Var.r.renderFrame();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Var.r.resetEntityLayer();

                if (Var.gameState == StateEnum.HIT_BY_FORMULA) {
                    Var.formulas.get(Var.hitFormulaIndex).hit();
                }

                //Spawning of new Entities
                if (stepsSinceStageTrigger >= durationOfStage) {
                    //returns to old spawning pattern
                    durationOfStage = Integer.MAX_VALUE;
                    stepsSinceStageTrigger = Integer.MIN_VALUE;

                    percMedium = Var.defaultPercMedium;
                    percHard = Var.defaultPercHard;
                    spawnRate = Var.defaultSpawnRate;
                    //ruft den neuen Timer auf
                    Var.timer.triggerWaitingForNextStage();
                }


                //Spawning now
                int entitiesToSpawn = spawnRate;
                for (int i = 0; i < entitiesToSpawn; i++) {
                    int[] coords = getRandomCoordsOnTop();
                    if(coords!=null){
                        Formula newFormula = new Formula(coords, Var.formulaDisplay, new int[]{1,1}, Var.formulaFrequency, randomDifficulty());
                        Var.formulas.add(newFormula);
                    }
                }

                stepsSinceStageTrigger++;
            }
            case HIT_BY_FORMULA -> {
                try{
                    var a=Var.formulas.get(Var.hitFormulaIndex).getDisplayForm().getAnswer(Integer.parseInt(String.valueOf(input)));
                    if(a.isRight()){

                    }else {
                        Var.player.setHp(Var.player.getHp()-1);

                    }
                    Var.gameState= EVADING_FORMULAS;
                    Var.formulas.remove(Var.hitFormulaIndex);
                }catch (NumberFormatException e){
                    try {
                        Audio.play(new URL("https://github.com/J-MR-T/UnicodeRenderer/raw/master/src/main/resources/sounds/betrugsversuch.wav"));
                    } catch (MalformedURLException e2) {
                        throw new RuntimeException(e2);
                    }
                }catch (IndexOutOfBoundsException e){
                    try {
                        Audio.play(new URL("https://github.com/J-MR-T/UnicodeRenderer/raw/master/src/main/resources/sounds/betrugsversuch.wav"));
                    } catch (MalformedURLException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
            case MENU -> {
                try {
                    Var.r.renderFrame();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static void setSpawningParameters(float pPercMedium, float pPercHard, int pDurationOfStage, int pSpawnRate) {
        stepsSinceStageTrigger = 0;
        percMedium = pPercMedium;
        percHard = pPercHard;
        durationOfStage = pDurationOfStage;
        spawnRate = pSpawnRate;
    }

    public static void triggerKurztest(int number) {
        if (number != -1) {
            if (number == 1) {
                setSpawningParameters(0.5f, 0.3f, (int) Var.KURZTEST_DURATION, 4); //TODO playtest and determine values
            } else if (number == 2) {
                setSpawningParameters(0.4f, 0.4f, (int) Var.KURZTEST_DURATION, 5); //TODO playtest and determine values
            } else if (number == 3) {
                setSpawningParameters(0.3f, 0.5f, (int) Var.KURZTEST_DURATION, 6); //TODO playtest and determine values
            }
        }
    }

    public static void triggerBoss(){
        setSpawningParameters(0.4f, 0.6f, (int) Var.EXAM_DURATION, 8); //TODO playtest and determine values
    }
    private static int randomX() {
        return (int) ((Math.random() * Var.width));
    }
    private static int randomDifficulty() {
        return (int) ((Math.random() * (2)));
    }

    private static int[] getRandomCoordsOnTop(){
        //lets generate a random x for the coord of the Formula
        for (int i = 0; i < 10; i++) { //max 10 attempts
            int[] coords = new int[]{randomX(), 0};
            boolean conflict = false;
            //lets check if there is already a formula there
            for (int j = Var.formulas.size()-1; j >= 0; j--) {
                if(Var.formulas.get(j).getX()==coords[0]) {
                    conflict = true;
                    break; //Recursively find new coords
                    }
                if(Var.formulas.get(j).getY()>0){
                    break; //We can skip looking since from now on all formulas will be below
                }
            }
            if(!conflict)return coords;
        }

        return null;
    }
}
