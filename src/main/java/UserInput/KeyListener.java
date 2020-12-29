package UserInput;

import com.sun.jna.Platform;

public interface KeyListener extends AutoCloseable{

    static KeyListener getKeyListener(CharConsumer c) {
        if(!Platform.isLinux()||Platform.isGNU())return new KeyListenerWindows(c);
    for (String name :new String[]{"ncurses","ncursest","ncursesw","ncursestw"}
        ) {
            try {
                return new KeyListenerNcurses(c,name);
            }catch (UnsatisfiedLinkError e){

            }

        }
    return new KeyListenerWindows(c);
    }


}
