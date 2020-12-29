package UserInput;

public interface CharConsumer {
    void accept(char c);

    default CharConsumer andThen(CharConsumer other) {
        return c -> {
            accept(c);
            other.accept(c);
        };
    }

}
