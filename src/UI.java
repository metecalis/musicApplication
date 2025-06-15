import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UI {
    public Pane pane;
    public Slider volumeLine,musicLine;
    public Label volumeText,durationText;
    public Button backButton,nextButton,playButton,pauseButton,stopButton,loopButton,muteButton;
    public Scene scene;
    public UI() {
        pane = new Pane();
        Image img = new Image(getClass().getResourceAsStream("/images/1.png"));
        ImageView songImage = new ImageView(img);
        songImage.setFitWidth(350);
        songImage.setFitHeight(200);
        songImage.setLayoutX(100);
        songImage.setLayoutY(0);

        musicLine = new Slider();
        musicLine.setLayoutX(100);
        musicLine.setLayoutY(200);
        musicLine.setPrefWidth(300);

        volumeLine = new Slider(0, 1, 0.1);
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

        playButton = new Button();
        pauseButton = new Button();
        stopButton = new Button();
        loopButton = new Button();
        nextButton=new Button();
        backButton=new Button();
        muteButton=new Button();

        playButton.setText("Play");
        pauseButton.setText("Pause");
        stopButton.setText("Stop");
        loopButton.setText("Loop");
        backButton.setText("Back");
        nextButton.setText("Next");
        muteButton.setText("Mute");

        muteButton.setLayoutX(215);
        muteButton.setLayoutY(220);
        nextButton.setLayoutX(440);
        nextButton.setLayoutY(200);
        backButton.setLayoutX(60);
        backButton.setLayoutY(200);
        loopButton.setLayoutX(20);
        loopButton.setLayoutY(110);
        playButton.setLayoutX(20);
        playButton.setLayoutY(20);
        pauseButton.setLayoutX(20);
        pauseButton.setLayoutY(50);
        stopButton.setLayoutX(20);
        stopButton.setLayoutY(80);

        pane.getChildren().addAll(leftCornerRect,muteButton, playButton, pauseButton, stopButton,loopButton,backButton,nextButton, musicLine, volumeLine, volumeText, durationText, songImage);
    }
}
