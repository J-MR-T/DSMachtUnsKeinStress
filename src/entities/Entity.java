package entities;

import mainpack.Var;
import rendering.DisplayObjects;

public abstract class Entity {
    protected int pos[]; //oben,links
    protected DisplayObjects[][] charRepresentation;
    /**
     * bounds[0] = width
     * bounds[1] = height
     */
    protected int bounds[];
    protected final int frequency;
    protected int currentFrequency;

    protected Entity(int[] pos, DisplayObjects[][] charRepresentation, int[] bounds, int frequency) {
        this.pos = pos;
        this.charRepresentation = charRepresentation;
        this.bounds = bounds;
        this.frequency = frequency;
        currentFrequency = frequency;
    }

    /**
     * Moves the player (Warning! Does not account for bounds!)
     */
    protected abstract void move(int newPos[]);

    public void update() {
        currentFrequency++;
        if (currentFrequency == frequency) {
            currentFrequency = 0;
        }
        updateInner();
    }

    public abstract void updateInner();

    public boolean collidesWith(Entity e) {
        //Checks if one Corner is inside the other entity

        //Top-Left corner
       if (e.getX() <= pos[0] + bounds[0] && e.getX() >= pos[0] && e.getY() <= pos[1] + bounds[1] && e.getY() >= pos[1]) {
            return true;
       }
       //Top-Right corner
        if (e.getX() + e.getGetWidth() <= pos[0] + bounds[0] && e.getX() + e.getGetWidth()  >= pos[0] && e.getY() <= pos[1] + bounds[1] && e.getY() >= pos[1]) {
            return true;
        }
        //Bottom-Right corner
        if (e.getX() <= pos[0] + bounds[0] && e.getX() >= pos[0] && e.getY() + e.getGetWidth() <= pos[1] + bounds[1] && e.getY() + e.getGetWidth() >= pos[1]) {
            return true;
        }
       //Bottom-Left corner
       if (e.getX() + e.getGetWidth() <= pos[0] + bounds[0] && e.getX() + e.getGetWidth() >= pos[0] && e.getY() + e.getHeight() <= pos[1] + bounds[1] && e.getY() + e.getHeight() >= pos[1]) {
           return true;
       }

       //No Corner of e is insinde the Entity => Checks if one boarder of e collides with the entity

        if (e.getX())



       return false;

    }

    public void render(DisplayObjects[][] screenToRenderTo) {
        //TODO
    }

    public int getX() {
        return pos[0];
    }

    public int getY() {
        return pos[1];
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public int[] getBounds() {
        return bounds;
    }

    public int getGetWidth() {
        return bounds[0];
    }

    public int getHeight() {
        return bounds[1];
    }

}
