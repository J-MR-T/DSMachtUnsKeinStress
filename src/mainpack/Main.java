package mainpack;

import UserInput.KeyListener;
import rendering.DisplayObjects;
import rendering.Renderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args.length==0){
            args=new String[]{"10","10"};
        }
        Renderer r = new Renderer(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        r.fillWithEmptyFrame();
//        r.fillWithEmptyFrameThick();
        r.draw(new int[]{1, 1}, DisplayObjects.BLOCK);
        int[] playerPos = {1, 1};
        KeyListener l = new KeyListener(r, playerPos);
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
        r.renderFrame();
        System.out.println("Does the frame above look right to you? [y/n] (Don't press enter, just the keys!)");
        DisplayObjects.changeToAscii();
        r.renderFrame();
    }

}
