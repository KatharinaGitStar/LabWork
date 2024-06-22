package MusicLandscape.entities;

public class MusicVideo extends Release {
    private Track track;

    public MusicVideo() {
    }

    public Track getTrack(){
        return track;
    }
    public void setTrack(Track track){
        this.track = track;
    }
    public int totalTime(){
        return (track == null) ? 0 : track.getDuration();
    }
    @Override
    public String toString(){
        //adds -Video to the string representation of a general release
        return (super.toString() + "-(Video)");
    }
}
