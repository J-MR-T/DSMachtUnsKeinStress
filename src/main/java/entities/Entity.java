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
    	if (e.getX() <= pos[0] + bounds[0] -1  && e.getX() >= pos[0] &&
     		   e.getY() <= pos[1] + bounds[1]-1 && e.getY() >= pos[1]) {
             return true;
        }
        //Top-Right corner
         if (e.getX() + e.getWidth()-1 <= pos[0] + bounds[0]-1 && e.getX() + e.getWidth()-1  >= pos[0] &&
         		e.getY() <= pos[1] + bounds[1]-1 && e.getY() >= pos[1]) {
             return true;
         }
         //Bottom-Right corner
         if (e.getX() <= pos[0] + bounds[0]-1 && e.getX() >= pos[0] &&
         		e.getY() + e.getHeight()-1 <= pos[1] + bounds[1]-1 && e.getY() + e.getWidth()-1 >= pos[1]) {
             return true;
         }
        //Bottom-Left corner
        if (e.getX() + e.getWidth()-1 <= pos[0] + bounds[0]-1 && e.getX() + e.getWidth()-1 >= pos[0] &&
     		   e.getY() + e.getHeight()-1 <= pos[1] + bounds[1]-1 && e.getY() + e.getHeight()-1 >= pos[1]) {
            return true;
        }
        
        //Checks if one Corner of the other entity is inside this entity
        if (pos[0] <= e.getX() + e.getWidth() -1  && pos[0] >= e.getX() &&
     		   pos[1] <= e.getY() + e.getHeight()-1 && pos[1] >= e.getY()) {
            return true;
       }
       //Top-Right corner
        if (pos[0] + bounds[0] -1 <= e.getX() + e.getWidth()-1 && pos[0] + bounds[0]  >= e.getX() &&
     		   pos[1] <= e.getY() + e.getHeight()-1 && pos[1] >= e.getY()) {
            return true;
        }
        //Bottom-Right corner
        if (pos[0] <= e.getX() + e.getWidth()-1 && pos[0] >= e.getWidth() &&
        		pos[1] + bounds[1]-1 <= e.getY() + e.getHeight()-1 && pos[1] + bounds[1]-1 >= e.getY()) {
            return true;
        }
       //Bottom-Left corner
       if (pos[0] + bounds[0]-1 <= e.getX() + e.getWidth()-1 && pos[0] + bounds[0]-1 >= e.getX() &&
    		   pos[1] + bounds[1]-1 <= e.getY() + e.getHeight()-1 && pos[1] + bounds[1]-1 >= e.getY()) {
           return true;
       }



       return false;

    }

    public void render(DisplayObjects[][] screenToRenderTo) {
        if(bounds[0]==1&&bounds[1]==1){
            screenToRenderTo[getDisplayX(screenToRenderTo)][getDisplayY(screenToRenderTo)]=charRepresentation[0][0];
        }else{
            //TODO
        }
    }

    public int getX() {
        return pos[0];
    }

    public int getY() {
        return pos[1];
    }

    public int getDisplayX(DisplayObjects[][] screenToRenderTo) {
        return pos[0]*2!=screenToRenderTo.length?pos[0]*2:pos[0]*2-1;
    }

    public int getDisplayY(DisplayObjects[][] screenToRenderTo) {
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

    public int getWidth() {
        return bounds[0];
    }

    public int getHeight() {
        return bounds[1];
    }

}
