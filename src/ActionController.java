import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;

public class ActionController {
    private boolean isDragging = false;
    private boolean isMuted = false;
    private double previousVolume = 0.5;
    private boolean isLoop = false;
    private MediaPlayer player;
    private MusicController musicController;

    private ListView<Music> musicListView;
    private Slider musicLine, volumeLine;
    private Label volumeText, durationText;
    private Button playButton, pauseButton, stopButton, muteButton, nextButton, backButton, loopButton;
    private UI ui;
    public ActionController(MediaPlayer mediaPlayer, Slider musicLine, Slider volumeLine,
                            Label volumeText, Label durationText,
                            Button playButton, Button pauseButton, Button stopButton,
                            Button muteButton, Button nextButton, Button backButton, Button loopButton,
                            ListView<Music> musicListView,
                            MusicController musicController,
                            UI ui) {
        this.player = mediaPlayer;
        this.musicLine = musicLine;
        this.volumeLine = volumeLine;
        this.volumeText = volumeText;
        this.durationText = durationText;
        this.playButton = playButton;
        this.pauseButton = pauseButton;
        this.stopButton = stopButton;
        this.muteButton = muteButton;
        this.nextButton = nextButton;
        this.backButton = backButton;
        this.loopButton = loopButton;
        this.musicListView = musicListView;
        this.musicController = musicController;
        this.previousVolume = volumeLine.getValue();
        this.ui = ui;
        initializeListViewStyling();

    }

    private void initializeListViewStyling() {
        musicListView.setCellFactory(param->new ListCell<>(){
            @Override
            protected void updateItem(Music music, boolean empty) {
                super.updateItem(music, empty);
                if (empty || music == null) {
                    setText(null);
                    setStyle("");}
                else {
                    setText(music.toString());
                    if(music.equals(musicController.getCurrentMusic()))
                        setStyle("-fx-background-color: linear-gradient(to right, #42A5F5, #4DB6AC); -fx-text-fill: white; -fx-font-weight: bold;");
                    else
                        setStyle("");}
            }
        });
    }
    private String formatTime(Duration duration) {
        if (duration == null) return "00:00";
        int totalSeconds = (int) duration.toSeconds();
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void setupPlayerListeners() {
        if (player == null) return;

        player.setOnReady(() -> {
            Duration mediaDuration = player.getMedia().getDuration();
            if (mediaDuration != null) {
                double duration = mediaDuration.toSeconds();
                musicLine.setMax(duration);
                musicLine.setValue(0);
                durationText.setText("00:00");
                player.play();
            }
        });

        player.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (!isDragging && newValue != null) {
                Platform.runLater(() -> {
                    musicLine.setValue(newValue.toSeconds());
                    durationText.setText(formatTime(newValue));
                });
            }
        });

        player.setOnEndOfMedia(() -> {
            if(!isLoop){
                if (!musicController.musicIsLast()) {
                    musicController.nextMusic();
                    musicListView.refresh();
                }
                else {
                    musicLine.setValue(0);
                    durationText.setText("00:00");
                    player.stop();
                 }
            }
            else {
                musicController.loopMusic();
                Music currentMusic = musicController.getCurrentMusic();
                ui.updateImage(currentMusic);
            }
        });
    }

    public void setUpActions() {
        volumeLine.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (player != null && !isMuted) {
                player.setVolume(newVal.doubleValue());
                volumeText.setText(String.format("%.2f", newVal.doubleValue()));
            }
        });

        musicLine.setOnMousePressed(event -> isDragging = true);

        musicLine.setOnMouseReleased(event -> {
            isDragging = false;
            if (player != null) {
                player.seek(Duration.seconds(musicLine.getValue()));
            }
        });


        playButton.setOnAction(event -> {
            if (player != null) {
                player.play();
                stopButton.setDisable(false);
            }
        });

        pauseButton.setOnAction(event -> {
            if (player != null) {
                player.pause();
            }
        });
        setupPlayerListeners();

        stopButton.setOnAction(event -> {
            if (player != null) {
                player.stop();
                stopButton.setDisable(true);
                musicLine.setValue(0);
                durationText.setText("00:00");
            }
        });

        backButton.setOnAction(event -> {
            if (musicController.musicIsFirst()) {
                System.out.println("You are now at first music on the list");
            } else {
                musicController.backMusic();
                Music currentMusic = musicController.getCurrentMusic();
                ui.updateImage(currentMusic);
                setupPlayerListeners();
                musicListView.refresh();
            }
        });

        nextButton.setOnAction(event -> {
            if (musicController.musicIsLast()) {
                System.out.println("Music list is over. You are now at last music on the list");
            } else {
                musicController.nextMusic();
                Music currentMusic = musicController.getCurrentMusic();
                ui.updateImage(currentMusic);
                setupPlayerListeners();
                musicListView.refresh();
            }
        });

        muteButton.setOnAction(event -> {
            if (player != null) {
                if (isMuted) {
                    player.setVolume(previousVolume);
                    volumeLine.setValue(previousVolume);
                    volumeText.setText(String.format("%.2f", previousVolume));
                    muteButton.setText("Mute");
                    isMuted = false;
                } else {
                    previousVolume = player.getVolume();
                    player.setVolume(0);
                    volumeLine.setValue(0);
                    volumeText.setText("0.00");
                    muteButton.setText("Unmute");
                    isMuted = true;
                }
            }
        });

        loopButton.setOnAction(event -> {
            isLoop=true;

        });
    }

    public void updatePlayer(MediaPlayer newPlayer) {
        this.player = newPlayer;
        setupPlayerListeners();
    }

    public void resetUI() {
        musicLine.setValue(0);
        durationText.setText("00:00");
        stopButton.setDisable(true);
    }
}