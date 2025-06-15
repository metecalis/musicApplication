import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer {
    private final MediaPlayer player;
    public MusicPlayer(String filePath) {
        Media media = new Media(new File(filePath).toURI().toString());
        player = new MediaPlayer(media);
    }
    public MediaPlayer getPlayer() {
        return player;
    }
}
