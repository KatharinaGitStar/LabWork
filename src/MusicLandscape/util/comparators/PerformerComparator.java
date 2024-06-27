package MusicLandscape.util.comparators;

import java.util.Comparator;
import MusicLandscape.entities.Track;

/**
 * Compares two tracks by performer.
 *
 * @version 232
 * @Author TeM
 * @Stage LW
 */
public class PerformerComparator implements Comparator<Track> {

    /**
     * Default constructor.
     */
    public PerformerComparator() {}

    /**
     * Compares two tracks by performer.
     *
     * @param o1 the first track
     * @param o2 the second track
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Track o1, Track o2) {
        return o1.getPerformer().compareTo(o2.getPerformer());
    }

    /**
     * Returns a string representation of the comparator.
     *
     * @return a string representation of the comparator
     */
    @Override
    public String toString() {
        return "PerformerComparator";
    }
}
