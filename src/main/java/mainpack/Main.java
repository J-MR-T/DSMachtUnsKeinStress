package mainpack;

import UserInput.Initializer;
import rendering.DisplayObjects;
import rendering.Renderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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

}
