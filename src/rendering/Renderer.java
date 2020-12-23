package rendering;

public class Renderer {
    public int[][] screen;
    public final int width;
    public final int height;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
        screen = new int[width * 2][height];
    }

    public void renderFrame() {
        StringBuilder s = new StringBuilder();
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
            generateSidesSpace(y, xAxis);
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
            generateSidesSpace(y, xAxis);
            xAxis[0] = DisplayObjects.BLOCK.asChar();
            xAxis[1] = DisplayObjects.WALL_UPPER.asChar();
            xAxis[xAxis.length - 1] = DisplayObjects.WALL_LOWER.asChar();
            xAxis[xAxis.length - 2] = DisplayObjects.WALL_RIGHT.asChar();
        }
        //Corners
        screen[0][1] = DisplayObjects.CORNER_UP_LEFT.asChar();
        screen[0][screen[1].length - 2] = DisplayObjects.CORNER_LOW_LEFT.asChar();
        screen[screen.length - 1][1] = DisplayObjects.CORNER_UP_RIGHT.asChar();
        screen[screen.length - 1][screen[1].length - 2] = DisplayObjects.CORNER_LOW_RIGHT.asChar();
    }

    private void generateSidesSpace(int y, int[] xAxis) {
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
    }

    public void draw(int[] pos, DisplayObjects d) {
        screen[pos[0] * 2][pos[1]] = d.asChar();
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
