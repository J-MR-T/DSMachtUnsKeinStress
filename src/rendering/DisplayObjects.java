package rendering;

public enum DisplayObjects {
    EMPTY((char) 0),
    SPACE(' '),
    STAR('*'),
    PLUS('+'),
    BLOCK('\u2588'),
    LIGHT_SHADE('\u2591'),
    MEDIUM_SHADE('\u2592'),
    DARK_SHADE('\u2593'),
    WALL_UPPER('\u2580'),
    WALL_LOWER('\u2584'),
    WALL_LEFT('\u258C'),
    WALL_RIGHT('\u2590'),
    CORNER_UP_LEFT('\u259B'),
    CORNER_UP_RIGHT('\u259C'),
    CORNER_LOW_LEFT('\u2599'),
    CORNER_LOW_RIGHT('\u259F');


    public char asChar() {
        return asChar;
    }

    private final char asChar;

    DisplayObjects(char asChar) {
        this.asChar = asChar;
    }

    @Override
    public String toString() {
        return "" + asChar;
    }
}
