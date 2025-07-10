import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer {
    private  MediaPlayer player;
    ActionController actionController;

    public MusicPlayer() {
    }
    public void changeMusic(String path) {
        if (player != null) {
            player.stop();
            player.dispose();
        }
        Media media = new Media(new File(path).toURI().toString());
        player = new MediaPlayer(media);
        player.play();
    }
    public MediaPlayer getPlayer() {
        return player;
    }
}
