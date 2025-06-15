import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage stage){

        UI ui = new UI();
        MusicPlayer musicPlayer = new MusicPlayer("/musics/bensound-acousticbreeze.mp3");
        var player = musicPlayer.getPlayer();
        ActionController controller = new ActionController(
                player,
                ui.musicLine,
                ui.volumeLine,
                ui.volumeText,
                ui.durationText,
                ui.playButton,
                ui.pauseButton,
                ui.stopButton
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
