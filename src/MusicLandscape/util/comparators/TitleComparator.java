package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;
import java.util.Comparator;

public class TitleComparator extends Object implements Comparator<Track> {

    public TitleComparator() {}

    //compare lexicogr. the titles of 2 tracks
    @Override
    public int compare(Track o1, Track o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }

    @Override
    public String toString() {
        return "by title";
    }
}
