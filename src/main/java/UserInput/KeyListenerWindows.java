package UserInput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

class KeyListenerWindows implements KeyListener {

    private final JFrame frame;
    private final KeyListenerImpl listener;
    KeyListenerWindows(CharConsumer c){
       listener=new KeyListenerImpl(c);
       frame = new JFrame();
       frame.setBounds(-50,-50,0,0);
       frame.addKeyListener(listener);
       try {
           frame.setIconImage(ImageIO.read(getClass().getResourceAsStream("SantaPingu.png")));
       } catch (IOException e) {
           e.printStackTrace();
       }
       frame.requestFocus();
   }

    @Override
    public void close() throws Exception {
        frame.dispose();
    }

    private class KeyListenerImpl implements java.awt.event.KeyListener {

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

