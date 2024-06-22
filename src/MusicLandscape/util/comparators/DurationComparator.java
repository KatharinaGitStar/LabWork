package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;

import java.util.Comparator;

public class DurationComparator implements Comparator<Track> {

    public DurationComparator() {}

    @Override
    public int compare(Track arg0, Track arg1) {
        return Integer.compare(arg0.getDuration(), arg1.getDuration());
    }

    @Override
    public String toString(){
        return "by duration";
    }
}
