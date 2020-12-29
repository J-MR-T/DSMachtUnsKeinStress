package audioOutput;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Audio {


    /**
     * Plays the AudioClip
     * @param url
     */
    public static void play(URL url) {

        AudioClip clip = Applet.newAudioClip(url);
        new Thread() {
            public void run() {
                try {
                    clip.play();
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
