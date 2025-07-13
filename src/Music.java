
public class Music {
    private String title;
    private String artist;
    private String album;
    private String genre;
    private String imagePath;
    private String filePath;
    private int duration;
    public Music(String title, int duration, String artist, String album, String genre, String filePath,String imagePath) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.filePath = filePath;
        this.duration = duration;
        this.imagePath = imagePath;
    }
    public String gettitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public String getAlbum() {
        return album;
    }
    public String getGenre() {
        return genre;
    }
    public String getImagePath() {
        return imagePath;
    }
    public String getFilePath() {
        return filePath;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void settitle(String title) {
        this.title = title;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFormattedDuration() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
    @Override
    public String toString() {
        return String.format("%s - %s (%s) [%s]",
                title, artist, getFormattedDuration(), genre);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Music music = (Music) obj;
        return title.equals(music.title) && artist.equals(music.artist);
    }
}

