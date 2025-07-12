import java.util.ArrayList;

public class MusicController {
    MusicPlayer musicPlayer;
    ArrayList<Music> musicList;
    private int currentIndex;
    private ActionController actionController;

    public void setActionController(ActionController actionController) {
        this.actionController = actionController;
    }
    public MusicController(MusicPlayer musicPlayer) {
        musicList = new ArrayList<>();
        this.musicPlayer = musicPlayer;
        currentIndex = 0;
    }

    public void addMusic(Music newMusic) {
        musicList.add(newMusic);
    }
    public Music getCurrentMusic() {
        if(musicList.isEmpty()) {
            System.out.println("Music list is empty");
            return null;
        }
        return musicList.get(currentIndex);
    }

    public boolean musicIsLast(){
        if(currentIndex == musicList.size() - 1)
            return true;
        return false;
    }
    public boolean musicIsFirst(){
        if(currentIndex == 0)
            return true;
        return false;
    }
    public void backMusic() {
        if (!musicIsFirst()) {
            currentIndex--;
            Music backMusic=musicList.get(currentIndex);
            String backMusicPath=backMusic.getFilePath();
            if(musicPlayer!= null) {
                musicPlayer.changeMusic(backMusicPath);
            }
        }
    }

    public void nextMusic() {
        if (!musicIsLast()) {
            currentIndex++;
            Music nextMusic=musicList.get(currentIndex);
            String nextMusicPath=nextMusic.getFilePath();
            if(musicPlayer!= null) {
                musicPlayer.changeMusic(nextMusicPath);
            }
            if (actionController != null) {
                actionController.updatePlayer(musicPlayer.getPlayer());
            }
        }
    }
}
