package rendering;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Renderer {
    public int[][] screen;
    public final int width;
    public final int height;

    private Thread scheduledDraw = null;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
        screen = new int[width * 2 - 1][height];
    }

    public void renderFrame() {
        StringBuilder s = new StringBuilder();
        //Add as many empty lines as needed
        for (int i = 0; i < 30; i++) {
            s.append('\n');
        }
        for (int y = 0; y < screen[0].length; y++) {
            for (int x = 0; x < screen.length; x++) {
                int[] xAxis = screen[x];
                s.append((char) xAxis[y]);
            }
            s.append('\n');
        }
        System.out.println(s.toString());
    }

    public void fillWithEmptyFrame() {
        for (int y = 0; y < screen.length; y++) {
            int[] xAxis = screen[y];
            if (y == 0) {
                for (int x = 1; x < xAxis.length - 1; x++) {
                    xAxis[x] = DisplayObjects.WALL_LEFT.asChar();
                }
            } else if (y == screen.length - 1) {
                for (int x = 1; x < xAxis.length - 1; x++) {
                    xAxis[x] = DisplayObjects.WALL_RIGHT.asChar();
                }
            } else {
                for (int x = 1; x < xAxis.length - 1; x++) {
                    xAxis[x] = DisplayObjects.SPACE.asChar();
                }
            }
            xAxis[0] = DisplayObjects.WALL_UPPER.asChar();
            xAxis[xAxis.length - 1] = DisplayObjects.WALL_LOWER.asChar();
        }
        //Corners
        screen[0][0] = DisplayObjects.CORNER_UP_LEFT.asChar();
        screen[0][screen[0].length - 1] = DisplayObjects.CORNER_LOW_LEFT.asChar();
        screen[screen.length - 1][0] = DisplayObjects.CORNER_UP_RIGHT.asChar();
        screen[screen.length - 1][screen[0].length - 1] = DisplayObjects.CORNER_LOW_RIGHT.asChar();
    }

    public void fillWithEmptyFrameThick() {
        for (int y = 0; y < screen.length; y++) {
            int[] xAxis = screen[y];
            xAxis[0] = DisplayObjects.WALL_UPPER.asChar();
            xAxis[xAxis.length - 1] = DisplayObjects.WALL_LOWER.asChar();
            if (y == 0) {
                for (int x = 0; x < xAxis.length; x++) {
                    xAxis[x] = DisplayObjects.BLOCK.asChar();
                }
            } else if (y == 1) {
                for (int x = 0; x < xAxis.length; x++) {
                    xAxis[x] = DisplayObjects.WALL_LEFT.asChar();
                }
            } else if (y == screen.length - 2) {
                for (int x = 0; x < xAxis.length; x++) {
                    xAxis[x] = DisplayObjects.WALL_RIGHT.asChar();
                }
            } else if (y == screen.length - 1) {
                for (int x = 0; x < xAxis.length; x++) {
                    xAxis[x] = DisplayObjects.BLOCK.asChar();
                }
            } else {
                for (int x = 1; x < xAxis.length - 1; x++) {
                    xAxis[x] = DisplayObjects.SPACE.asChar();
                }
            }
        }
        //Corners
        screen[1][0] = DisplayObjects.CORNER_UP_LEFT.asChar();
        screen[1][screen[1].length - 1] = DisplayObjects.CORNER_LOW_LEFT.asChar();
        screen[screen.length - 2][0] = DisplayObjects.CORNER_UP_RIGHT.asChar();
        screen[screen.length - 2][screen[1].length - 1] = DisplayObjects.CORNER_LOW_RIGHT.asChar();
    }


    public void draw(int[] pos, DisplayObjects d) {
        screen[pos[0] * 2][pos[1]] = d.asChar();
    }

    public boolean scheduledDrawExists() {
        return scheduledDraw != null;
    }

    public void scheduleDraw(@Nullable int[] pos, @Nullable DisplayObjects d) {
        int[] finalPos = pos.clone();
        if (d != null){
            scheduledDraw = new Thread(() -> screen[finalPos[0] * 2][finalPos[1]] = d.asChar());
        }
    }

    public void doScheduledDraw() {
        if (scheduledDraw != null) {
            scheduledDraw.run();
            scheduledDraw = null;
        }
    }

    public void moveUp(int[] pos) {
        int[] old = new int[]{pos[0], pos[1]};
        move(old, --pos[1] == 0 ? new int[]{pos[0], ++pos[1]} : pos);
    }

    public void moveDown(int[] pos) {
        int[] old = new int[]{pos[0], pos[1]};
        move(old, ++pos[1] == screen[0].length ? new int[]{pos[0], --pos[1]} : pos);
    }

    public void moveLeft(int[] pos) {
        int[] old = new int[]{pos[0], pos[1]};
        move(old, --pos[0] == 0 ? new int[]{++pos[0], pos[1]} : pos);
    }

    public void moveRight(int[] pos) {
        int[] old = new int[]{pos[0], pos[1]};
        move(old, ++pos[0] == screen[0].length ? new int[]{--pos[0], pos[1]} : pos);
    }

    public void move(int[] from, int[] to) {
        copy(from, to, true);
    }

    public void copy(int[] from, int[] to, boolean deleteOld) {
        from = new int[]{from[0] * 2, from[1]};
        to = new int[]{to[0] * 2, to[1]};
        screen[to[0]][to[1]] = screen[from[0]][from[1]];
        if (deleteOld) screen[from[0]][from[1]] = DisplayObjects.SPACE.asChar();
    }

}
