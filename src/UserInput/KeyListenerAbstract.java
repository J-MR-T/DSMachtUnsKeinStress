package UserInput;

public class KeyListenerAbstract {

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
    return new KeyListenerWindows(c);
    }

}
