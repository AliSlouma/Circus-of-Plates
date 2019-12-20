package eg.edu.alexu.csd.oop.game.GUI;
// file using Clip Object
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SimpleAudioPlayer
{
    private Clip clip;
    private AudioInputStream audioInputStream;
    private static String filePath = "music/song.wav";

    // constructor to initialize streams and clip
    public SimpleAudioPlayer()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public  void startaudio()
    {
        try
        {
            SimpleAudioPlayer audioPlayer =
                    new SimpleAudioPlayer();

            audioPlayer.play();
        }

        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }
    }

    // Method to play the audio
    public void play() {
        //start the clip
        clip.start();

    }

    // Method to stop the audio
    public void stop() {
        clip.stop();
        clip.close();
    }

}
