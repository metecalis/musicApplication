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
    public Slider volumeLine,musicLine;
    public Label volumeText,durationText;
    public Button backButton,nextButton,playButton,pauseButton,stopButton,loopButton,muteButton,fastForwardButton;
    public Scene scene;
    public VBox vBox;
    public Image image;
    public Music music;
    public ImageView songImage;
    public ListView<Music> musicListView;
    public MusicController musicController;
    public UI(Music newMusic,MusicController musicController) {
        this.music = newMusic;
        this.musicController = musicController;
        pane = new Pane();
        Image img = new Image(getClass().getResource(newMusic.getImagePath()).toExternalForm());
        songImage = new ImageView(img);
        songImage.setFitWidth(350);
        songImage.setFitHeight(200);
        songImage.setLayoutX(100);
        songImage.setLayoutY(0);

        musicListView=new ListView<>();
        musicListView.getStyleClass().add("musicListView");
        musicListView.setItems(FXCollections.observableArrayList(musicController.musicList));
        musicListView.setLayoutX(480);
        musicListView.setLayoutY(0);

        musicLine = new Slider();
        musicLine.setLayoutX(100);
        musicLine.setLayoutY(200);
        musicLine.setPrefWidth(300);

        volumeLine = new Slider(0, 1, 0.5);
        volumeLine.setLayoutX(110);
        volumeLine.setLayoutY(220);
        volumeLine.setPrefWidth(75);

        volumeText = new Label("Volume");
        volumeText.setLayoutX(185);
        volumeText.setLayoutY(220);
        volumeText.setPrefWidth(40);

        durationText = new Label("Duration");
        durationText.setLayoutX(400);
        durationText.setLayoutY(200);
        durationText.setPrefWidth(40);

        Rectangle leftCornerRect = new Rectangle(100, 150);
        leftCornerRect.setFill(Color.GREEN);
        leftCornerRect.setLayoutX(0);
        leftCornerRect.setLayoutY(0);

        vBox=new VBox(5);
        playButton = new Button();
        pauseButton = new Button();
        stopButton = new Button();
        loopButton = new Button();
        nextButton=new Button();
        backButton=new Button();
        muteButton=new Button();
        fastForwardButton=new Button();
        fastForwardButton.setText("Fast Forward");
        fastForwardButton.setLayoutX(0);
        fastForwardButton.setLayoutY(0);

        playButton.setText("Play");
        pauseButton.setText("Pause");
        stopButton.setText("Stop");
        loopButton.setText("Loop");
        backButton.setText("Back");
        nextButton.setText("Next");
        muteButton.setText("Mute");

        vBox.setLayoutX(20);
        vBox.setLayoutY(20);
        vBox.getChildren().addAll(playButton,pauseButton,stopButton,loopButton);
        muteButton.setLayoutX(215);
        muteButton.setLayoutY(220);
        nextButton.setLayoutX(440);
        nextButton.setLayoutY(200);
        backButton.setLayoutX(60);
        backButton.setLayoutY(200);


        pane.getChildren().addAll(leftCornerRect,vBox,muteButton,backButton,nextButton, musicLine, volumeLine, volumeText, durationText, songImage,musicListView);
    }


    public void updateImage(Music newMusic) {
        System.out.println("Yeni image path: " + newMusic.getImagePath());
        Image img = new Image(getClass().getResource(newMusic.getImagePath()).toExternalForm());
        songImage.setImage(img);
    }

}
