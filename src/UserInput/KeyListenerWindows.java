package UserInput;

import rendering.DisplayObjects;
import rendering.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerWindows extends KeyListenerAbstract{

    private final JFrame frame;
    private final KeyListenerImpl listener;
   public  KeyListenerWindows(CharConsumer c){
       listener=new KeyListenerImpl(c);
       frame = new JFrame();
       frame.setBounds(-50,-50,0,0);
       frame.addKeyListener(listener);
   }

    private class KeyListenerImpl implements KeyListener{

        private  final CharConsumer consumer;

        public KeyListenerImpl(CharConsumer c){
            this.consumer=c;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            consumer.accept(e.getKeyChar());
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}

