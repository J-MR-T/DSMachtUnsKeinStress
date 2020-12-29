package rendering;

import entities.Formula;
import mainpack.Var;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import rendering.DisplayObjects;

public class Renderer {
    public ArrayList<DisplayObjects[][]> screens;
    public final int width;
    public final int height;

    public final int BOARD_LAYER = 0;
    public final int ENTITY_LAYER = 1;

    private Thread scheduledDraw = null;

    public Renderer(int width, int height) {
        this.width = width * 2 - 1;
        this.height = height;
        screens = new ArrayList<DisplayObjects[][]>();
        screens.add(new DisplayObjects[width * 2 - 1][height]);
    }

    public void changeToAscii() {
        DisplayObjects.changeToAscii();
    }

    /**
     * @return Top of screens-list
     */
    public DisplayObjects[][] peek() {
        return screens.size() > 0 ? screens.get(screens.size() - 1) : null;
    }

    public void renderFrame() {
        StringBuilder s = new StringBuilder();
        //Add as many empty lines as needed
        s.append("\n".repeat(30));
        if (Var.gameState == Var.EVADING_FORMULAS) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    for (int i = screens.size() - 1; i >= 0; i++) {
                        DisplayObjects[][] screen = screens.get(i);
                        if (screen[x][y] != DisplayObjects.EMPTY || i == 0) {
                            if (i != 0) {
                                s.append(screen[x][y]);
                            } else {
                                s.append(DisplayObjects.SPACE);
                            }
                        }
                    }
                }
                s.append('\n');
            }
        }else if(Var.gameState==Var.HIT_BY_FORMULA&&Var.hitFormulaIndex!=-1){
            Formula hit = Var.formulas.get(Var.hitFormulaIndex);
            s.append(hit);
            //TODO warten bis formula gelöst ist
        }
        System.out.println(s.toString());
    }

    public void resetEntityLayer() {
        DisplayObjects[][] entityLayer = screens.get(ENTITY_LAYER);
        for (int i = 0; i < entityLayer.length; i++) {
            DisplayObjects[] displayObjects = entityLayer[i];
            for (int r = 0; r < displayObjects.length; r++) {
                displayObjects[r] = DisplayObjects.EMPTY;
            }
        }
    }

    public boolean isEmpty(boolean thickFrame, DisplayObjects[][] screen) {
        //TODO thick frame isn't handled yet
        int numberOfNonSpaceItems = -1;
        if (!thickFrame) {
            for (int i = 1; i < screen.length - 1; i++) {
                DisplayObjects[] displayObjects = screen[i];
                for (int j = 1; j < displayObjects.length - 1; j++) {
                    DisplayObjects displayObject = displayObjects[j];
                    if (displayObject != DisplayObjects.SPACE) {
                        numberOfNonSpaceItems++;
                    }
                }
            }
        }
        return numberOfNonSpaceItems < 1;
    }


    public void generateNoise(double spaceFactor, DisplayObjects[][] screen) {
        for (int i = 1; i < width - 1; i++) {
            DisplayObjects[] displayObjects = screen[i * 2];
            for (int j = 1; j < displayObjects.length - 1; j++) {
                DisplayObjects object = DisplayObjects.getRandomDisplayObject();
                if (Math.random() < spaceFactor) {
                    object = DisplayObjects.SPACE;
                }
                displayObjects[j] = object == DisplayObjects.EMPTY || object == DisplayObjects.BLOCK ?
                        DisplayObjects.SPACE : object;
            }
        }
    }

    public void fillWithEmptyFrame(DisplayObjects[][] screen) {
        for (int y = 0; y < screen.length; y++) {
            DisplayObjects[] xAxis = screen[y];
            if (y == 0) {
                for (int x = 1; x < xAxis.length - 1; x++) {
                    xAxis[x] = DisplayObjects.WALL_LEFT;
                }
            } else if (y == screen.length - 1) {
                for (int x = 1; x < xAxis.length - 1; x++) {
                    xAxis[x] = DisplayObjects.WALL_RIGHT;
                }
            } else {
                for (int x = 1; x < xAxis.length - 1; x++) {
                    xAxis[x] = DisplayObjects.SPACE;
                }
            }
            xAxis[0] = DisplayObjects.WALL_UPPER;
            xAxis[xAxis.length - 1] = DisplayObjects.WALL_LOWER;
        }
        //Corners
        screen[0][0] = DisplayObjects.CORNER_UP_LEFT;
        screen[0][screen[0].length - 1] = DisplayObjects.CORNER_LOW_LEFT;
        screen[screen.length - 1][0] = DisplayObjects.CORNER_UP_RIGHT;
        screen[screen.length - 1][screen[0].length - 1] = DisplayObjects.CORNER_LOW_RIGHT;
    }

    public void fillWithEmptyFrameThick(DisplayObjects[][] screen) {
        for (int y = 0; y < screen.length; y++) {
            DisplayObjects[] xAxis = screen[y];
            xAxis[0] = DisplayObjects.WALL_UPPER;
            xAxis[xAxis.length - 1] = DisplayObjects.WALL_LOWER;
            if (y == 0) {
                for (int x = 0; x < xAxis.length; x++) {
                    xAxis[x] = DisplayObjects.BLOCK;
                }
            } else if (y == 1) {
                for (int x = 0; x < xAxis.length; x++) {
                    xAxis[x] = DisplayObjects.WALL_LEFT;
                }
            } else if (y == screen.length - 2) {
                for (int x = 0; x < xAxis.length; x++) {
                    xAxis[x] = DisplayObjects.WALL_RIGHT;
                }
            } else if (y == screen.length - 1) {
                for (int x = 0; x < xAxis.length; x++) {
                    xAxis[x] = DisplayObjects.BLOCK;
                }
            } else {
                for (int x = 1; x < xAxis.length - 1; x++) {
                    xAxis[x] = DisplayObjects.SPACE;
                }
            }
        }
        //Corners
        screen[1][0] = DisplayObjects.CORNER_UP_LEFT;
        screen[1][screen[1].length - 1] = DisplayObjects.CORNER_LOW_LEFT;
        screen[screen.length - 2][0] = DisplayObjects.CORNER_UP_RIGHT;
        screen[screen.length - 2][screen[1].length - 1] = DisplayObjects.CORNER_LOW_RIGHT;
    }


    public void draw(int[] pos, DisplayObjects d, DisplayObjects[][] screen) {
        screen[pos[0] * 2][pos[1]] = d;
    }

    public boolean scheduledDrawExists() {
        return scheduledDraw != null;
    }

    public void scheduleDraw(int[] pos, @Nullable DisplayObjects d, DisplayObjects[][] screen) {
        int[] finalPos = pos.clone();
        if (d != null) {
            scheduledDraw = new Thread(() -> screen[finalPos[0] * 2][finalPos[1]] = d);
        }
    }

    public void doScheduledDraw() {
        if (scheduledDraw != null) {
            scheduledDraw.run();
            scheduledDraw = null;
        }
    }

    public void moveUp(int[] pos, DisplayObjects[][] screen) {
        int[] old = new int[]{pos[0], pos[1]};
        move(old, --pos[1] == 0 ? new int[]{pos[0], ++pos[1]} : pos, screen);
    }

    public void moveDown(int[] pos, DisplayObjects[][] screen) {
        int[] old = new int[]{pos[0], pos[1]};
        move(old, ++pos[1] == height ? new int[]{pos[0], --pos[1]} : pos, screen);
    }

    public void moveLeft(int[] pos, DisplayObjects[][] screen) {
        int[] old = new int[]{pos[0], pos[1]};
        move(old, --pos[0] == 0 ? new int[]{++pos[0], pos[1]} : pos, screen);
    }

    public void moveRight(int[] pos, DisplayObjects[][] screen) {
        int[] old = new int[]{pos[0], pos[1]};
        move(old, ++pos[0] == width ? new int[]{--pos[0], pos[1]} : pos, screen);
    }

    public void move(int[] from, int[] to, DisplayObjects[][] screen) {
        copy(from, to, true, screen);
    }

    public void copy(int[] from, int[] to, boolean deleteOld, DisplayObjects[][] screen) {
        from = new int[]{from[0] * 2, from[1]};
        to = new int[]{to[0] * 2, to[1]};
        screen[to[0]][to[1]] = screen[from[0]][from[1]];
        if (deleteOld) screen[from[0]][from[1]] = DisplayObjects.SPACE;
    }

}
