package mainpack;

import rendering.DisplayObjects;
import rendering.Renderer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        Renderer r = new Renderer(10, 10);
        r.fillWithEmptyFrame();
//        r.fillWithEmptyFrameThick();
        r.draw(new int[]{1, 1}, DisplayObjects.STAR);
        r.renderFrame();
        int[] playerPos = {1, 1};
        KeyListener l = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'w') {
                    r.moveUp(playerPos);
                } else if (e.getKeyChar() == 'a') {
                    r.moveLeft(playerPos);
                }
                if (e.getKeyChar() == 's') {
                    r.moveDown(playerPos);
                }
                if (e.getKeyChar() == 'd') {
                    r.moveRight(playerPos);
                }
                r.renderFrame();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        JFrame hidden = new JFrame();
        hidden.setBounds(1920,1080,50,50);
        hidden.addKeyListener(l);
        hidden.setVisible(true);
    }
}
