package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;

public class MyTitleComparator extends MyTrackComparator{

    @Override
    public int compare(Track t1, Track t2) {
        //comparator does not handle null tracks
        if ((t1.getTitle() != null && !t1.getTitle().isEmpty() && t2.getTitle() != null && !t2.getTitle().isEmpty())){
           return t1.getTitle().compareTo(t2.getTitle());
        }
        return 0;
    }
}
