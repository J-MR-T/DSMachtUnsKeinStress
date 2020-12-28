package UserInput;

public abstract class KeyListenerAbstract implements AutoCloseable{

public static interface CharConsumer{
    void accept(char c);
    default CharConsumer andThen(CharConsumer other){
        return c->{
            accept(c);
            other.accept(c);
        };
    }

}

    public static KeyListenerAbstract getKeyListener(CharConsumer c) {
    String ncurses= checkNcurses();
    return ncurses==null? new KeyListenerWindows(c):null;
    }
    private static String checkNcurses(){

        for (String name :new String[]{"ncurses","ncursest","ncursesw","ncursestw"}
             ) {
            try {
                System.loadLibrary(name);
                return name;
            }catch (UnsatisfiedLinkError e){

            }

        }
        return null;
    }

}
