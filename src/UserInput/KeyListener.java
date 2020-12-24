package UserInput;

import rendering.DisplayObjects;
import rendering.Renderer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyListener extends Component implements java.awt.event.KeyListener {

    private Renderer r;
    private int[] playerPos;

    public KeyListener(Renderer r, int[] playerPos) {
        this.r = r;
        this.playerPos = playerPos;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'w' && playerPos[1] > 1) {
            r.moveUp(playerPos);
            r.doScheduledDraw();
        } else if (e.getKeyChar() == 'a' && playerPos[0] > 1) {
            r.moveLeft(playerPos);
            r.doScheduledDraw();
        } else if (e.getKeyChar() == 's' && playerPos[1] < r.height - 2) {
            r.moveDown(playerPos);
            r.doScheduledDraw();
        } else if (e.getKeyChar() == 'd' && playerPos[0] < r.width - 2) {
            r.moveRight(playerPos);
            r.doScheduledDraw();
        } else if (e.getKeyChar() == 'e') {
            System.exit(0);
//                } else if (e.getKeyChar() == DisplayObjects.ZERO.asChar()) {
//                    r.scheduleDraw(playerPos, DisplayObjects.ZERO);
        } else {
            r.scheduleDraw(playerPos, DisplayObjects.getDisplayObject(e.getKeyChar()));
        }
        r.renderFrame();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
