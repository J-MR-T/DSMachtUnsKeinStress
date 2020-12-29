package UserInput;

import com.sun.jna.Platform;

public interface KeyListener extends AutoCloseable{

    static KeyListener getKeyListener(CharConsumer c, boolean forceGui) {
        if(forceGui||!(Platform.isLinux()||Platform.isGNU()))return new KeyListenerGui(c);
    for (String name :new String[]{"ncurses","ncursest","ncursesw","ncursestw"}
        ) {
            try {
                return new KeyListenerNcurses(c,name);
            }catch (UnsatisfiedLinkError e){

            }

        }
    return new KeyListenerGui(c);
    }


}
