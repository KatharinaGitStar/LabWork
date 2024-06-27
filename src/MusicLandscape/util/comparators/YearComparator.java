package MusicLandscape.util.comparators;

import java.util.Comparator;
import MusicLandscape.entities.Track;

public class YearComparator implements Comparator<Track> {

    /**
     * Default constructor.
     */
    public YearComparator() {}

    /**
     * Compares two tracks by their year.
     *
     * @param arg0 the first track
     * @param arg1 the second track
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Track arg0, Track arg1) {
        return Integer.compare(arg0.getYear(), arg1.getYear());
    }

    /**
     * Returns a string representation of the comparator.
     *
     * @return a string representation of the comparator
     */
    @Override
    public String toString() {
        return "YearComparator";
    }
}
