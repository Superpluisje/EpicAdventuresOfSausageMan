package com.pascalrouw.sausageman;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import java.util.HashMap;

public class MusicPlayer {
    private HashMap<String,Clip> clips;
    
    private boolean loop;
    private float volume;
    private boolean play;
    
    public MusicPlayer() {
        clips = new HashMap<String,Clip>();
    }
    
    public void createSong(String fileName, boolean loop, float volume, boolean play) {
        try {
            if (!clips.containsKey(fileName)) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.getClass().getResource("Resources/Music/" + fileName));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume);
                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                
                if (play) {
                    clip.start();
                }
                clips.put(fileName, clip);
            }
        } 
        catch (Exception f) {
            f.printStackTrace();
        }
        
    }
    
    public void playSong(String songName) {
        clips.get(songName).start();
    }
    
    public Clip getClip(String clipName) {
        return clips.get(clipName);
    }
    
    public void stopSong(String songName) {
        clips.get(songName).stop();
    }
}
