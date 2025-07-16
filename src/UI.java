import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class UI {
    public Pane pane;
    public Slider volumeLine, musicLine;
    public Label volumeText, durationText;
    public Button backButton, nextButton, playButton, pauseButton, stopButton, loopButton, muteButton, fastForwardButton;
    public Scene scene;
    public VBox vBox;
    public Image image;
    public Music music;
    public ImageView songImage;
    public ListView<Music> musicListView;
    public MusicController musicController;

    public UI(Music newMusic, MusicController musicController) {
        this.music = newMusic;
        this.musicController = musicController;
        pane = new Pane();

        Image img = new Image(getClass().getResource(newMusic.getImagePath()).toExternalForm());
        songImage = new ImageView(img);
        songImage.setFitWidth(300);
        songImage.setFitHeight(180);
        songImage.setLayoutX(140);
        songImage.setLayoutY(30);

        musicListView = new ListView<>();
        musicListView.getStyleClass().add("musicListView");
        musicListView.setItems(FXCollections.observableArrayList(musicController.musicList));
        musicListView.setLayoutX(480);
        musicListView.setLayoutY(20);
        musicListView.setPrefWidth(280);
        musicListView.setPrefHeight(300);

        musicLine = new Slider();
        musicLine.setLayoutX(140);
        musicLine.setLayoutY(230);
        musicLine.setPrefWidth(300);

        volumeLine = new Slider(0, 1, 0.5);
        volumeLine.setLayoutX(140);
        volumeLine.setLayoutY(260);
        volumeLine.setPrefWidth(100);

        volumeText = new Label("Volume");
        volumeText.setLayoutX(250);
        volumeText.setLayoutY(260);

        durationText = new Label("00:00");
        durationText.setLayoutX(445);
        durationText.setLayoutY(230);

        Rectangle leftCornerRect = new Rectangle(120, 360);
        leftCornerRect.setFill(Color.web("#FF7043")); // Accent color
        leftCornerRect.setLayoutX(0);
        leftCornerRect.setLayoutY(0);

        playButton = new Button("▶");
        pauseButton = new Button("⏸");
        stopButton = new Button("⏹");
        loopButton = new Button("🔁");
        backButton = new Button("⏮");
        nextButton = new Button("⏭");
        muteButton = new Button("🔇");
        fastForwardButton = new Button("⏩");

        playButton.setPrefWidth(80);
        pauseButton.setPrefWidth(80);
        stopButton.setPrefWidth(80);
        loopButton.setPrefWidth(80);

        vBox = new VBox(10, playButton, pauseButton, stopButton, loopButton);
        vBox.setLayoutX(20);
        vBox.setLayoutY(30);

        backButton.setLayoutX(140);
        backButton.setLayoutY(290);

        nextButton.setLayoutX(200);
        nextButton.setLayoutY(290);

        muteButton.setLayoutX(260);
        muteButton.setLayoutY(290);

        fastForwardButton.setLayoutX(320);
        fastForwardButton.setLayoutY(290);
        fastForwardButton.setPrefWidth(120);

        pane.getChildren().addAll(
                leftCornerRect,
                songImage,
                musicListView,
                musicLine,
                volumeLine,
                volumeText,
                durationText,
                vBox,
                backButton, nextButton, muteButton, fastForwardButton
        );
    }

    public void updateImage(Music newMusic) {
        Image img = new Image(getClass().getResource(newMusic.getImagePath()).toExternalForm());
        songImage.setImage(img);
    }
}
