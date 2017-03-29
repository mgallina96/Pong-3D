package utility.sound;

import application.Main;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Sound player.
 */
public class SoundPlayer {
    private String fileLocation;
    private SourceDataLine line;
    private boolean playing;

    private ScheduledService<Object> musicLooper;
    private Service<Object> soundPlayer;

    /**
     * Starts a loop of the specified sound.
     *
     * @param fileName The sound to loop.
     */
    public void playLoop(String fileName) {
        fileLocation = fileName;
        musicLooper = new ScheduledService<Object>() {
            @Override
            protected Task<Object> createTask() {
                return new sound();
            }
        };

        Main.getStage().setOnHiding(e -> {
            this.stopLoop();
            musicLooper.cancel();
        });
        musicLooper.start();
    }

    /**
     * Plays the specified sound (Only once).
     *
     * @param fileName The sound to play.
     */
    public void playSound(String fileName) {
        fileLocation = fileName;
        soundPlayer = new Service<Object>() {
            @Override
            protected Task<Object> createTask() {
                return new sound();
            }
        };

        Main.getStage().setOnHiding(e -> {
            this.stopSound();
            soundPlayer.cancel();
        });
        soundPlayer.start();
    }

    // Plays the sound.
    private void play(String fileName) {
        File soundFile = new File(fileName);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AudioFormat audioFormat = audioInputStream.getFormat();
        line = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        playing = true;
        line.start();

        int nBytesRead = 0;
        byte[] abData = new byte[128000];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                int nBytesWritten = line.write(abData, 0, nBytesRead);
            }
        }
        line.drain();
        line.close();
        playing = false;
    }

    /** Stops the loop of the sound. */
    public void stopLoop() {
        line.stop();
        musicLooper.cancel();
        playing = false;
    }

    /** Stops the sound. */
    public void stopSound() {
        line.stop();
        soundPlayer.cancel();
        playing = false;
    }

    /** @return playing True if the sound is playing, false otherwise. */
    public boolean isPlaying() {
        return playing;
    }

    /** If the sound is playing it stops, otherwise it starts. */
    public void switchMusic() {
        if (isPlaying())
            stopLoop();
        else
            playLoop(fileLocation);
    }

    /* Task that plays the sound one time. */
    private class sound extends Task<Object> {
        @Override
        protected Object call() throws Exception {
            play(fileLocation);
            return null;
        }
    }
}
