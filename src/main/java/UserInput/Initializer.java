package UserInput;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Initializer {
    private static boolean unicode;
    private static double spaceFactor;
    private static int boardWidth;
    private static int boardHeight;
    public static Random r = null;

    public static void initialize() throws IllegalStateException {
        Locale.setDefault(Locale.ENGLISH);
        Scanner s = new Scanner(System.in);
        System.out.println("Difficulty level: (enter number between 0.0 and 1.0," +
                " 1.0 being extremely difficult and 0.0 being an instant win)");
        spaceFactor = 1.0-s.nextDouble();
        if(spaceFactor>1.0||spaceFactor<0.0){
            throw new IllegalStateException();
        }
        System.out.println("Board Width: (>=2)");
        boardWidth = s.nextInt()+2;
        System.out.println("Board Height: (>=2)");
        boardHeight = s.nextInt()+2;
        if(boardWidth<2||boardHeight<2){
            throw new IllegalStateException();
        }
        System.out.println("Enter Seed, to play against your friends (-1 for a random Seed): ");
        int seed = s.nextInt();
        if (seed > 0) {
            r = new Random(seed);
        } else {
            r = new Random();
        }
        System.out.println("In the following, you will be prompted for instant inputs, you will not have to press" +
                " enter to confirm your choice. \n If you understand that please" +
                " answer with [y/n] (click on the cute little Penguin in you Taskbar if it does not work ;))");
    }

    public static boolean isUnicode() {
        return unicode;
    }

    public static void setUnicode(boolean unicode) {
        Initializer.unicode = unicode;
    }

    public static double getSpaceFactor() {
        return spaceFactor;
    }

    public static void setSpaceFactor(double spaceFactor) {
        Initializer.spaceFactor = spaceFactor;
    }

    public static int getBoardWidth() {
        return boardWidth;
    }

    public static void setBoardWidth(int boardWidth) {
        Initializer.boardWidth = boardWidth;
    }

    public static int getBoardHeight() {
        return boardHeight;
    }

    public static void setBoardHeight(int boardHeight) {
        Initializer.boardHeight = boardHeight;
    }

}
