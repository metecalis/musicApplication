import java.util.ArrayList;

public class MusicController {
    MusicPlayer musicPlayer;
    ArrayList<Music> musicList;
    private int currentIndex;
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

    public void nextMusic() {
        if (!musicIsLast()) {
            currentIndex++;
            Music nextMusic=musicList.get(currentIndex);
            String nextMusicPath=nextMusic.getFilePath();
            if(musicPlayer!= null) {
                musicPlayer.changeMusic(nextMusicPath);
            }
        }
    }
}
