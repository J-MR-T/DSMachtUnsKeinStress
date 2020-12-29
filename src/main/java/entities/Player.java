package entities;

import rendering.DisplayObjects;

public class Player extends Entity {

    private int hp;
    private final int maxHp;

    private char movement;



    public Player(int[] pos, DisplayObjects[][] charRepresentation, int[] bounds, int maxHp) {
        super(pos, charRepresentation, bounds, 1);
        this.maxHp = maxHp;
        hp = maxHp;
    }

    /**
     * Moves the player up (Warning! Does not account for bounds!)
     */
    public void moveUp() {
        pos[1]--;
    }

    /**
     * Moves the player down (Warning! Does not account for bounds!)
     */
    public void moveDown() {
        pos[1]++;
    }

    /**
     * Moves the player left (Warning! Does not account for bounds!)
     */
    public void moveLeft() {
        pos[0]--;
    }

    /**
     * Moves the player right (Warning! Does not account for bounds!)
     */
    public void moveRight() {
        pos[0]++;
    }

    @Override
    protected void move(int[] newPos) {
        pos[0] = newPos[0];
        pos[1] = newPos[1];
    }

    @Override
    public void updateInner() {
        switch (movement){
            case 'w'->moveUp();
            case 'a'->moveLeft();
            case 's'->moveDown();
            case 'd'->moveRight();
        }
    }

    public Player setMovement(char movement) {
        this.movement = movement;
        return this;
    }
}
