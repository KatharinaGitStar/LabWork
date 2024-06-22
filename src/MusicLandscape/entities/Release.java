package MusicLandscape.entities;

public abstract class Release {
    private Artist artist;
    private String title;
    private int year;

    public Release(){
        this.artist = new Artist();
        this.title = null;
        this.year = 1900; // default year is 1900
    }
    public Release(Release orig){
        this.artist = new Artist(orig.artist);
        this.title = orig.title;
        this.year = Math.max(orig.year, 1900);
    }
    public Release(String title, Artist artist, int year){
        setTitle(title);
        setArtist(artist);
        setYear(year);
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public Artist getArtist(){
        return artist;
    }
    public void setArtist(Artist artist){
        this.artist = artist;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        //if the argument is outside the allowed range it is ignored
        if (year == 0) {
            this.year = 0;
        } else {
            this.year = Math.max(year, 1900);
        }
    }
    public abstract int totalTime();

    @Override
    public String toString() {
        //string representation is: title-artist-year-total time; unknown field are represented with unknown
        String strtitle = title == null ? "unknown" : title;
        String stryear = year == 0 ? "unknown" : Integer.toString(year);
        String strtotalTime = totalTime() == 0 ? "0" : String.format("%02d", totalTime());
        return String.format("%s-%s-%s-%s", strtitle, artist, stryear, strtotalTime);
    }
}

