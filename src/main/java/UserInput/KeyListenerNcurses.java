package UserInput;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

class KeyListenerNcurses implements KeyListener{
    @Override
    public void close() throws Exception {
        StopFlag.set(false);
        INPUTREADER.join();
        NCURSES.endwin();
    }

    private final Ncurses NCURSES;
    private final CLibrary CLIBRARY;
    private final Thread INPUTREADER;
    private final AtomicBoolean StopFlag=new AtomicBoolean(true);

    KeyListenerNcurses(CharConsumer c, String libname){
       NCURSES= Native.load(libname,Ncurses.class);
       CLIBRARY=Native.load("c",CLibrary.class);
       CLIBRARY.setlocale(6,"");
        Pointer window=NCURSES.initscr();
        NCURSES.cbreak();
        NCURSES.noecho();
        INPUTREADER=new Thread(()->{
            try (var reader=new InputStreamReader(System.in, StandardCharsets.UTF_8)){
                var chars=new char[1];
                while (StopFlag.get()){
                    if(-1== reader.read(chars,0,1))return;
                    else c.accept(chars[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        INPUTREADER.start();
    }

    private interface Ncurses extends Library{
        Pointer initscr();
        int endwin();
        int cbreak();
        int noecho();

    }
    private interface CLibrary extends Library{
        String setlocale(int category,String locale);
    }


}
