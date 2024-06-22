package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;
import java.util.Comparator;

public class WriterComparator extends Object implements Comparator<Track> {

    public WriterComparator(){}

    //compare 2 tracks by writer; the natural ordering of the artists is used
    @Override
    public int compare(Track o1, Track o2) {
        return o1.getWriter().compareTo(o2.getWriter());
    }

    @Override
    public String toString() {
        return "by writer";
    }
}