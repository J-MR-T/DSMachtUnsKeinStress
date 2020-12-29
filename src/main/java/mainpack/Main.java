package mainpack;

import UserInput.Initializer;
import rendering.DisplayObjects;
import rendering.Renderer;

import javax.imageio.ImageIO;
import javax.naming.SizeLimitExceededException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        showStoryLine();
        try {
            Initializer.initialize();
        } catch (IllegalStateException | InputMismatchException e) {
            System.out.println("You have to enter useful values, you know? smh");
            System.exit(0);
        }
        Renderer r = new Renderer(Initializer.getBoardWidth(), Initializer.getBoardHeight());
        r.fillWithEmptyFrame(r.peek());
//        r.fillWithEmptyFrameThick();
        r.draw(new int[]{1, 1}, DisplayObjects.BLOCK,r.peek());
        int[] playerPos = {1, 1};
        r.renderFrame();
    }

    private static void showStoryLine(){
        System.out.println("So...");
        sleep(800);
        slowPrint("you want to walk the path of excellence.\n", 900);
        sleep(100);
        System.out.println("Good.");
        sleep(140);
        System.out.println("But first you need to survive");
        sleep(600);
        slowPrint("-> DISKRETE STRUKTUREN <-", 1200);
        sleep(800);
        System.out.println("WASD to move");
        System.out.println("evade the formulas");
        sleep(800);
        System.out.println("GO!");
        sleep(500);
    }

    private static void sleep(long millies) {
        try {
            Thread.sleep(millies);
        } catch (InterruptedException e) {
            //System.out.println("Thread is interrupted");
            Thread.currentThread().interrupt();
        }
    }

    private static void slowPrint(String text, int totalTime){
        char[] textArr = text.toCharArray();
        int timeBetweenChars =  totalTime / textArr.length;
        for(char charToPrint : textArr){
            System.out.println(charToPrint);
            sleep(timeBetweenChars);
        }
    }

}
