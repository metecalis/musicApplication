import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;

public class ActionController {
    private String formatTime(Duration duration) {
        double totalSeconds = duration.toSeconds();
        int minutes = (int) totalSeconds / 60;
        double seconds = totalSeconds % 60;
        return String.format("%02d:%05.2f", minutes, seconds);
    }
    private final MediaPlayer player;
    private final Slider musicLine,volumeLine;
    private final Label volumeText,durationText;
    private final Button playButton,pauseButton,stopButton;
    public ActionController(MediaPlayer mediaPlayer,Slider musicLine,Slider volumeLine,Label volumeText,Label durationText,Button playButton,Button pauseButton,Button stopButton) {
        this.pauseButton = pauseButton;
        this.playButton = playButton;
        this.stopButton = stopButton;
        this.player = mediaPlayer;
        this.musicLine = musicLine;
        this.volumeLine = volumeLine;
        this.volumeText = volumeText;
        this.durationText = durationText;
    }
    public void setUpActions() {
        volumeLine.valueChangingProperty().addListener((obs,oldTime,newTime)->{
            player.setVolume(volumeLine.getValue());
            volumeText.setText(String.format("%.2f",volumeLine.getValue()));

        });
        player.setOnReady(() -> {
            double duration = player.getMedia().getDuration().toSeconds();
            musicLine.setMax(duration);
            player.play();
        });
        musicLine.valueChangingProperty().addListener((obs,oldTime,newTime)->{
            player.setVolume(musicLine.getValue());
        });
        player.setOnEndOfMedia(() -> {
            System.out.println("Music is over");
        });
        player.currentTimeProperty().addListener((obs,oldTime,newTime)->{
            musicLine.setValue(newTime.toSeconds());
            durationText.setText(formatTime(player.getCurrentTime()));
        });
        musicLine.valueChangingProperty().addListener((obs,wasChanging,isChanging)->{
            if (!isChanging) {
                player.seek(Duration.seconds(musicLine.getValue()));
            }
        });
        pauseButton.setOnAction(event -> {
            player.pause();
        });
        playButton.setOnAction(event -> {
            player.play();
        });
        stopButton.setOnAction(event -> {
            player.stop();
        });

    }


}
