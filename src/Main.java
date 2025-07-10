import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        UI ui = new UI();

        MusicPlayer musicPlayer = new MusicPlayer();

        MusicController musicController = new MusicController(musicPlayer);

        musicController.addMusic(new Music("Adventure", 179, "Benjamin Tissot", "Bensound", "Cinematic",
                "src/resources/musics/adventure.mp3"));
        musicController.addMusic(new Music("Acoustic Breeze", 157, "Benjamin Tissot", "Bensound", "Acoustic",
                "src/resources/musics/bensound-acousticbreeze.mp3"));
        musicController.addMusic(new Music("Beyond the Line", 192, "Unknown Artist", "Unknown Album", "Electronic",
                "src/resources/musics/beyondtheline.mp3"));
        musicController.addMusic(new Music("Energy", 163, "Benjamin Tissot", "Bensound", "Rock",
                "src/resources/musics/energy.mp3"));
        musicController.addMusic(new Music("Hip Jazz", 168, "Benjamin Tissot", "Bensound", "Jazz",
                "src/resources/musics/hipjazz.mp3"));
        musicController.addMusic(new Music("On Repeat", 155, "Unknown Artist", "Unknown Album", "Chill",
                "src/resources/musics/onrepeat.mp3"));
        musicController.addMusic(new Music("Perception", 162, "Benjamin Tissot", "Bensound", "Corporate",
                "src/resources/musics/perception.mp3"));
        musicController.addMusic(new Music("Rainy Day", 212, "Unknown Artist", "Unknown Album", "Ambient",
                "src/resources/musics/rainyday.mp3"));
        musicController.addMusic(new Music("Sleepless", 225, "Unknown Artist", "Unknown Album", "Lo-fi",
                "src/resources/musics/sleepless.mp3"));
        musicController.addMusic(new Music("Sunny", 140, "Benjamin Tissot", "Bensound", "Acoustic",
                "src/resources/musics/sunny.mp3"));
        musicController.addMusic(new Music("Tenderness", 123, "Benjamin Tissot", "Bensound", "Soft",
                "src/resources/musics/tenderness.mp3"));

        Music currentMusic = musicController.getCurrentMusic();
        musicPlayer.changeMusic(currentMusic.getFilePath());

        var player = musicPlayer.getPlayer();

        ActionController controller = new ActionController(
                player,
                ui.musicLine,
                ui.volumeLine,
                ui.volumeText,
                ui.durationText,
                ui.playButton,
                ui.pauseButton,
                ui.stopButton,
                ui.muteButton,
                ui.nextButton,
                ui.backButton,
                ui.loopButton,
                musicController
        );

        controller.setUpActions();

        Scene scene = new Scene(ui.pane, 500, 250);
        stage.setTitle("Fmcify");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
