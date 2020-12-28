package UserInput;

import rendering.DisplayObjects;
import rendering.Renderer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyListener extends Component implements java.awt.event.KeyListener {

    private Renderer r;
    private int[] playerPos;
    private Boolean answer = null;

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
        } else if (e.getKeyChar() == 'y') {
            setAnswer(true);
            return;
        } else if (e.getKeyChar() == 'n') {
            setAnswer(false);
            return;
        } else if (e.getKeyChar() == '\n') {
            if (r.isEmpty(false)) {
                System.out.println("-".repeat(r.width * 2 - 1));
                System.out.printf("Nice! Well done! This took you exactly '%10.6f' Seconds %nRestart or end? [r/e]%n",
                        Math.pow(10, -9) * (System.nanoTime()-r.startTime));
                System.out.println("-".repeat(r.width * 2 - 1));
                return;
            } else {
                System.out.println("You missed a spot or two!");
            }
        } else if (e.getKeyChar() == 'r') {
            r.fillWithEmptyFrame();
            r.generateNoise(Initializer.getSpaceFactor());
            this.playerPos = new int[]{1, 1};
            r.draw(playerPos, DisplayObjects.BLOCK);
            r.startTime = System.nanoTime();
        } else if (e.getKeyChar() == 'e') {
            System.out.println("Bye!");
            System.exit(0);
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

    public void resetAnswer() {
        answer = null;
    }

    public boolean isWaitingForAnswer() {
        return answer == null;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
