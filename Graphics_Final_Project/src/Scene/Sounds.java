/******
 * Student name: Doreen Vaserman
 * Student ID: 308223627
 * Student name: Nadav Spitzer
 * Student ID: 302228275
 */
package Scene;

import Utils.Vector;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Sounds {
    private static ArrayList<AudioClip> clipsArray= new ArrayList<AudioClip>();

    /***
     * plays a clip.
     * @param path the clip path.
     */
    public static void makeSound(String path) {
        AudioClip clip = play(path);
        clip.play();
    }

    /****
     * play a clip in loops.
     * @param path the path to the clip.
     */
    public static void makeLoopSound(String path) {
        AudioClip clip = play(path);
        clipsArray.add(clip);
        clip.loop();
    }

    /***
     * clear all clips from the sounds lists.
     */
    public static void emptySounds(){
        for (int i = 0; i < clipsArray.size(); i++) {
            clipsArray.get(i).stop();
        }
        clipsArray.clear();
    }

    /****
     * plays the clip given audio path.
     * @param path the path to the sound clip.
     * @return an audio clip.
     */
    private static AudioClip play(String path) {
        File file = new File(path);
        URL url = null;
        if (file.canRead()) {
            try {
                url = file.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        AudioClip clip = Applet.newAudioClip(url);
        return clip;
    }
}
