package mainpack;

import UserInput.Initializer;
import UserInput.KeyListenerWindows;
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
        r.fillWithEmptyFrame();
        r.generateNoise(Initializer.getSpaceFactor());
//        r.fillWithEmptyFrameThick();
        r.draw(new int[]{1, 1}, DisplayObjects.BLOCK);
        int[] playerPos = {1, 1};
        KeyListenerWindows l = new KeyListenerWindows(r, playerPos);
        JFrame hidden = new JFrame();
        hidden.setBounds(-50, -50, 0, 0);
        hidden.addKeyListener(l);
        try {
            hidden.setIconImage(ImageIO.read(new File("rsc/SantaPingu.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hidden.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hidden.setVisible(true);
        hidden.setAutoRequestFocus(true);
        l.resetAnswer();
        int timeout = 15000;
        int time = 0;
        while (l.isWaitingForAnswer()) {
            Thread.sleep(100);
            time += 100;
            if (time >= timeout) {
                System.out.println("So you haven't then? smh");
                System.exit(0);
            }
        }
        if (l.getAnswer()) {
            System.out.println("Very nice, lets continue with the Setup then: ");
        } else {
            System.out.println("Uhm that's fine too I guess, I'll just carry on then");
        }
        Thread.sleep(2500);
        r.renderFrame();
        l.resetAnswer();
        System.out.println("Does the frame above look right to you? [y/n]");
        while (l.isWaitingForAnswer()) {
            Thread.sleep(100);
        }
        if (!l.getAnswer()) {
            DisplayObjects.changeToAscii();
        }
        r.renderFrame();
    }

}
